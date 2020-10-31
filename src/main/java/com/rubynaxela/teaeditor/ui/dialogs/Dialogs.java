/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.ui.dialogs;

import com.rubynaxela.teaeditor.datatypes.IdNameColorTriplet;
import com.rubynaxela.teaeditor.datatypes.database.AbstractPrimaryElement;
import com.rubynaxela.teaeditor.ui.components.ColorPreviewBox;
import com.rubynaxela.teaeditor.ui.components.RGBColorChooser;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Arrays;

import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags.BRAND;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags.NEW;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getIcon;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;
import static com.rubynaxela.teaeditor.util.Utils.getOptionPane;

@SuppressWarnings("unused")
public final class Dialogs {

    public static void showInfo(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                getIcon("dialog.info"), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showWarning(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.warning"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                getIcon("dialog.warning"), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showError(String message, boolean exitOnClose) {
        String title = getString("dialog.title.error");
        String okButtonText = getString("button.ok");
        Object okButton = !okButtonText.equals("button.ok") ? okButtonText : "OK";
        if (exitOnClose) {
            okButton = new JButton((String) okButton);
            ((JButton) okButton).addActionListener(e -> System.exit(0));
        }
        JOptionPane.showOptionDialog(Reference.window, message, !title.equals("dialog.title.error") ? title : "Error",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                getIcon("dialog.error"), new Object[]{okButton}, okButton);
    }

    public static void showError(String message) {
        showError(message, false);
    }

    public static boolean askYesNoQuestion(String message, boolean defaultAnswer, Icon icon) {
        return JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                icon, new String[]{getString("button.yes"), getString("button.no")},
                defaultAnswer ? getString("button.yes") : getString("button.no")) == 0;
    }

    public static IdNameColorTriplet getIdNameColorData(AbstractPrimaryElement editedElement, DataDialogFlags... flags) {
        JPanel dialogPanel = new JPanel();
        JLabel idLabel = new JLabel(getString("dialog.input.id")),
                idErrorLabel = new JLabel(getString("dialog.id_invalid")),
                nameLabel = new JLabel(getString("dialog.input.name")),
                colorLabel = new JLabel(getString("dialog.input.color"));
        JTextField idInput = new JTextField(editedElement != null ? editedElement.getId() : ""),
                nameInput = new JTextField(editedElement != null ? editedElement.getName() : "");
        RGBColorChooser colorInput = new RGBColorChooser(
                editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE);
        ColorPreviewBox previewBox = new ColorPreviewBox();
        JButton okButton = new JButton(getString("button.ok"));

        DocumentListener textFieldListener = new AbstractValidInputListener(okButton) {
            @Override
            protected boolean dataValid() {
                boolean idValidChars = idInput.getText().matches("^[a-z]*$");
                if (idValidChars) {
                    idInput.setBorder(null);
                    idErrorLabel.setVisible(false);
                } else {
                    idInput.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    idErrorLabel.setVisible(true);
                }
                return !idInput.getText().equals("") && !nameInput.getText().equals("") && idValidChars;
            }
        };
        idInput.getDocument().addDocumentListener(textFieldListener);
        idErrorLabel.setVisible(false);
        nameInput.getDocument().addDocumentListener(textFieldListener);

        previewBox.setBackground(editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE);
        previewBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        colorInput.getSelectionModel().addChangeListener(e -> previewBox.setBackground(colorInput.getColor()));

        dialogPanel.setLayout(new GridBagLayout());
        dialogPanel.add(idLabel, dialogElementPosition(0, 0, false));
        dialogPanel.add(idInput, dialogElementPosition(1, 0, false));
        dialogPanel.add(idErrorLabel, dialogElementPosition(0, 1, false));
        dialogPanel.add(nameLabel, dialogElementPosition(0, 2, false));
        dialogPanel.add(nameInput, dialogElementPosition(1, 2, false));
        dialogPanel.add(colorLabel, dialogElementPosition(0, 3, true));
        dialogPanel.add(colorInput, dialogElementPosition(0, 4, true));
        dialogPanel.add(previewBox, dialogElementPosition(0, 5, true));

        okButton.setEnabled(editedElement != null);
        okButton.addActionListener(e -> getOptionPane((JComponent) e.getSource()).setValue(okButton));

        String title;
        if (Arrays.asList(flags).contains(NEW))
            if (Arrays.asList(flags).contains(BRAND))
                title = getString("dialog.title.new_brand");
            else
                title = getString("dialog.title.new_shelf");
        else if (Arrays.asList(flags).contains(BRAND))
            title = getString("dialog.title.edit_brand");
        else
            title = getString("dialog.title.edit_shelf");

        return (JOptionPane.showOptionDialog(Reference.window,
                dialogPanel, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                getIcon("dialog.list"), new Object[]{okButton, getString("button.cancel")}, okButton) == 0) ?
                new IdNameColorTriplet(idInput.getText(), nameInput.getText(), colorInput.getColor()) : null;
    }

}
