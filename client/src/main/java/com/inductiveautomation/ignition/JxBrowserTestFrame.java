package com.inductiveautomation.ignition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.browser.event.ConsoleMessageReceived;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.js.ConsoleMessageLevel;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import net.miginfocom.swing.MigLayout;

class JxBrowserTestFrame extends JFrame implements ActionListener {

    private final Engine engine;
    private JPanel mainPanel;
    private JPanel browserPanel;
    private JPanel headerPanel;

    DroppableButton dragDropButton = new DroppableButton("DragNDrop Me");
    private BrowserView view;
    private Browser browser;
    private JTextField urlField;

    JxBrowserTestFrame(Engine engine) {
        this.engine = engine;
        init();
    }

    private void init() {
        mainPanel = new JPanel(new MigLayout("fill"));
        browserPanel = new JPanel(new MigLayout("fill"));
        headerPanel = new JPanel(new MigLayout());

        urlField = new JTextField(120);
        urlField.setText("http://localhost:8099/test.html");

        JButton go = new JButton("Go");
        go.addActionListener(this);
        urlField.addActionListener(this);

        headerPanel.add(go);
        headerPanel.add(urlField);
        headerPanel.add(dragDropButton);

        view = browserView();

        browserPanel.add(view, "push, grow");
        mainPanel.add(headerPanel, "dock north");
        mainPanel.add(browserPanel, "grow");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 780);
        setContentPane(mainPanel);
        setVisible(true);
        addWindowListener(new ShutdownWindowListener());

        navigate();
    }


    private BrowserView browserView() {
        browser = engine.newBrowser();

        browser.on(ConsoleMessageReceived.class, event -> {
           String msg = event.consoleMessage().message();
            ConsoleMessageLevel lvl = event.consoleMessage().level();
            Integer line = event.consoleMessage().lineNumber();
            System.out.println("BrowserConsole[" + line + "]:" + msg);
        });

        return BrowserView.newInstance(browser);
    }

    private void navigate() {
        System.out.println("Navigating to " + urlField.getText());
        view.getBrowser().navigation().loadUrl(urlField.getText());
        view.getBrowser().devTools().remoteDebuggingUrl().ifPresent(url -> System.out.println("Debug url: " + url));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        navigate();
    }

    class ShutdownWindowListener implements WindowListener {

        ShutdownWindowListener() {

        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("WindowClosing event detected.");
            if (!engine.isClosed()) {
                System.out.println("Engine is being closed!");
                engine.close();
            } else {
                System.out.println("Engine was closed!");
            }
        }

        @Override
        public void windowClosed(WindowEvent e) {
            //noop
        }

        @Override
        public void windowIconified(WindowEvent e) {
            //noop
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            //noop
        }

        @Override
        public void windowActivated(WindowEvent e) {
            //noop
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            //noop
        }
    }
}
