package com.inductiveautomation.ignition;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;


import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.Language;
import com.teamdev.jxbrowser.engine.RenderingMode;

public class JxBrowser7Test {

    public static void main(String[] args) {

        URL licenseFile = JxBrowser7Test.class.getResource("/jxblicense.properties");

        Properties licenseProps = new Properties();
        try {
            licenseProps.load(licenseFile.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String license = licenseProps.getProperty("jxblicense");
        String chromiumDirectory = System.getProperty("user.home") + File.separator + ".jxbrowser";

        EngineOptions options = EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
            .remoteDebuggingPort(9333)
            .language(Language.ENGLISH_US)
            .licenseKey(license)
            .chromiumDir(Paths.get(chromiumDirectory))
            .build();


        Engine engine = Engine.newInstance(options);

        JxBrowserTestFrame frame = new JxBrowserTestFrame(engine);
    }
}


