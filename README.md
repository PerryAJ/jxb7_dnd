# Testing JxBrowser 7 Drag and Drop

## Info

This is a small simple project that builds and runs a small server that serves up a single test.html file at: http://localhost:8099/test.html 

This file is visible at `/server/static/test.html`

It contains a simple react component that implements an `onDrop` callback.  In JxBrowser 6 Heavyweight Mode, this handler will fire on MacOs when a desktop file (or swing transferable element) is dragged over the frame and dropped.  

In JxBrowser 7 on both Mac and Windows, no such event fires in "HARDWARE_ACCELERATED" configuration mode. 

Similarly, if the "DragNDrop" button is dragged and dropped, we get swing events firing to create the transferable, but not the browser event that we were accustomed to firing in JxBrowser 6.

Desired Outcome:  Dropping from desktop or Swing transferable bypasses Java/Swing's event handling and allows pass-through to the underlying heavyweight browser as was the case in Jx Browser 6.

# Quick Start


The two examples can be built and run via gradle wrapper.  JDK 11 should be on the system path.

First: add appropriate license key to `/client/src/main/resources/jxblicense.properties`

Then run:

`./gradlew :server:run` - will build and start the localhost server at port 8099 that serves a simple test file at _http://localhost:8099/test.html_

`./gradlew :client:run` - will build and start a simple jxbrowser-containing window.


# Notes

JxBrowser7Test class holds the 'main' method and EngineOptions

JxBrowser7TestFrame is the JFrame holding the ui




