package com.rubynaxela.teaeditor.ui.dialogs;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal", "unused"})
abstract class AbstractValidInputListener implements DocumentListener {

    private final JButton okButton;
    private final JLabel infoLabel;
    private final Component[] components;

    protected AbstractValidInputListener(JButton okButton, JLabel infoLabel, Component... components) {
        this.okButton = okButton;
        this.infoLabel = infoLabel;
        this.components = components;
    }

    protected AbstractValidInputListener(JButton okButton) {
        this(okButton, null);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }

    protected abstract boolean dataValid();
}
