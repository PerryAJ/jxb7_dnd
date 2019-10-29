package com.inductiveautomation.ignition;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

class ItemTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        return false;
    }

    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
        return false;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        // just some simple example string contents
        String fakeJson = "{ \"type\": \"someType\", \"payload\": 1234 }";
        return new StringSelection(fakeJson);
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        System.out.println("Drop exported from TransferHandler...");
    }
}
