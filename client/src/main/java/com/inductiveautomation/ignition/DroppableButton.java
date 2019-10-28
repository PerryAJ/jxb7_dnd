package com.inductiveautomation.ignition;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * Button that provides some drag and drop functionality
  */
class DroppableButton extends JButton {
    TransferHandler handler;
    DragListener listener;

    DroppableButton(String label) {
        super(label);
        handler = new ItemTransferHandler();
        listener = new DragListener(handler);
        setTransferHandler(handler);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    static class DragListener extends MouseAdapter {
        Component comp;
        TransferHandler handler;
        MouseEvent firstEvent;

        DragListener(TransferHandler handler) {
            this.handler = handler;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            firstEvent = e;
            comp = e.getComponent();
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            if (firstEvent != null && comp == e.getSource()) {
                e.consume();

                int dx = Math.abs(e.getX() - firstEvent.getX());
                int dy = Math.abs(e.getY() - firstEvent.getY());

                // don't trigger drag if less than 5 px move
                if (dx > 5 || dy > 5) {
                    TransferHandler handler = ((JComponent)comp).getTransferHandler();
                    // Tell the transfer handler to initiate the drag.
                    handler.exportAsDrag((JComponent) comp, firstEvent, TransferHandler.COPY);
                    firstEvent = null;
                }
            }
        }
    }
}
