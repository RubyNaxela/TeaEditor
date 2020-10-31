/*
 * Copyright (c) 2020 Jacek Pawelski a.k.a. RubyNaxela
 *
 * Licensed under the GNU General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * The license is included in file 'LICENSE.txt', which is part of this
 * source code package. You may also obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rubynaxela.teaeditor.ui.dialogs;

import com.formdev.flatlaf.icons.*;
import com.rubynaxela.teaeditor.datatypes.IdNameColorTriplet;
import com.rubynaxela.teaeditor.datatypes.Trilean;
import com.rubynaxela.teaeditor.datatypes.database.AbstractPrimaryElement;
import com.rubynaxela.teaeditor.ui.components.ColorPreviewBox;
import com.rubynaxela.teaeditor.ui.components.RGBColorChooser;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags.BRAND;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlags.NEW;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;
import static com.rubynaxela.teaeditor.util.Utils.getOptionPane;

public final class Dialogs {

    public static void showInfo(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new FlatOptionPaneInformationIcon(), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showWarning(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.warning"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                new FlatOptionPaneWarningIcon(), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showError(String message, boolean exitOnClose) {
        final String title = getString("dialog.title.error");
        final String okButtonText = getString("button.ok", "OK");
        Object okButton;
        if (exitOnClose) {
            okButton = new JButton(okButtonText);
            ((JButton) okButton).addActionListener(e -> System.exit(0));
        } else
            okButton = okButtonText;
        JOptionPane.showOptionDialog(Reference.window, message, !title.equals("dialog.title.error") ? title : "Error",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                new FlatOptionPaneErrorIcon(), new Object[]{okButton}, okButton);
    }

    public static void showError(String message) {
        showError(message, false);
    }

    public static boolean askYesNoQuestion(String message, boolean defaultAnswer) {
        return JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                new FlatOptionPaneQuestionIcon(), new String[]{getString("button.yes"), getString("button.no")},
                defaultAnswer ? getString("button.yes") : getString("button.no")) == 0;
    }

    public static Trilean askYesNoCancelQuestion(String message) {
        int answer = JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                new FlatOptionPaneQuestionIcon(),
                new String[]{getString("button.yes"), getString("button.no"), getString("button.cancel")},
                getString("button.cancel"));
        return Trilean.from012Model(answer);
    }

    public static IdNameColorTriplet getIdNameColorData(AbstractPrimaryElement editedElement, DataDialogFlags... flags) {
        final JPanel dialogPanel = new JPanel();
        final JLabel idLabel = new JLabel(getString("dialog.input.id")),
                idErrorLabel = new JLabel(" "),
                nameLabel = new JLabel(getString("dialog.input.name")),
                colorLabel = new JLabel(getString("dialog.input.color"));
        final JTextField idInput = new JTextField(editedElement != null ? editedElement.getId() : ""),
                nameInput = new JTextField(editedElement != null ? editedElement.getName() : "");
        final RGBColorChooser colorInput = new RGBColorChooser(
                editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE);
        final ColorPreviewBox previewBox = new ColorPreviewBox();
        final JButton okButton = new JButton(getString("button.ok"));

        final Border defaultBorder = idInput.getBorder();
        DocumentListener textFieldListener = new AbstractValidInputListener(okButton) {
            @Override
            protected boolean dataValid() {
                boolean idValidChars = idInput.getText().matches("^[a-z]*$");
                if (idValidChars) {
                    idInput.setBorder(defaultBorder);
                    idErrorLabel.setText(" ");
                } else {
                    idInput.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(0x834141), 3, true),
                            BorderFactory.createEmptyBorder(2, 6, 2, 6)));
                    idErrorLabel.setText(getString("dialog.input.id_invalid"));
                }
                return !idInput.getText().equals("") && !nameInput.getText().equals("") && idValidChars;
            }
        };
        idInput.getDocument().addDocumentListener(textFieldListener);
        nameInput.getDocument().addDocumentListener(textFieldListener);

        previewBox.setBackground(editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE);
        previewBox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        colorInput.getSelectionModel().addChangeListener(e -> previewBox.setBackground(colorInput.getColor()));

        dialogPanel.setLayout(new GridBagLayout());
        dialogPanel.add(idLabel, dialogElementPosition(0, 0, false));
        dialogPanel.add(idInput, dialogElementPosition(0, 1, false));
        dialogPanel.add(nameLabel, dialogElementPosition(1, 0, false));
        dialogPanel.add(nameInput, dialogElementPosition(1, 1, false));
        dialogPanel.add(colorLabel, dialogElementPosition(2, 0, true));
        dialogPanel.add(colorInput, dialogElementPosition(3, 0, true));
        dialogPanel.add(previewBox, dialogElementPosition(4, 0, true));
        dialogPanel.add(idErrorLabel, dialogElementPosition(5, 0, true));

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
                new FlatOptionPaneAbstractIcon("OptionPane.icon.questionColor", "Actions.Blue") {
                    @Override
                    protected Shape createOutside() {
                        return new Ellipse2D.Float( 2, 2, 28, 28 );
                    }

                    @Override
                    protected Shape createInside() {
                        Path2D q = new Path2D.Float();
                        q.moveTo( 14, 20 );
                        q.lineTo( 18, 20 );
                        q.curveTo( 18, 16, 23, 16, 23, 12 );
                        q.curveTo( 23, 8, 20, 6, 16, 6 );
                        q.curveTo( 12, 6, 9, 8, 9, 12 );
                        q.curveTo( 9, 12, 13, 12, 13, 12 );
                        q.curveTo( 13, 10, 14, 9, 16, 9 );
                        q.curveTo( 18, 9, 19, 10, 19, 12 );
                        q.curveTo( 19, 15, 14, 15, 14, 20 );
                        q.closePath();

                        Path2D inside = new Path2D.Float( Path2D.WIND_EVEN_ODD );
                        inside.append( new Rectangle2D.Float( 14, 22, 4, 4 ), false );
                        inside.append( q, false );
                        return inside;
                    }
                }, new Object[]{okButton, getString("button.cancel")}, okButton) == 0) ?
                new IdNameColorTriplet(idInput.getText(), nameInput.getText(), colorInput.getColor()) : null;
    }

}
