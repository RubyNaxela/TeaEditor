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

package com.rubynaxela.teaeditor.gui.dialogs;

import com.rubynaxela.teaeditor.datatypes.database.AbstractPrimaryElement;
import com.rubynaxela.teaeditor.gui.components.ColorPreviewBox;
import com.rubynaxela.teaeditor.gui.components.RGBColorChooser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.Reference.Resources.BLANK_STRING;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;
import static com.rubynaxela.teaeditor.util.Utils.getOptionPane;

/**
 * @since beta 1.3.1
 */
public final class INCDataDialogPanel extends JPanel {

    public final JLabel idLabel, idErrorLabel, nameLabel, colorLabel;
    public final JTextField idInput, nameInput;
    public final Color initialColor;
    public final RGBColorChooser colorInput;
    public final ColorPreviewBox previewBox;
    public final JButton okButton;

    public INCDataDialogPanel(AbstractPrimaryElement editedElement) {
        idLabel = new JLabel(getString("dialog.input.id"));
        idErrorLabel = new JLabel(BLANK_STRING);
        nameLabel = new JLabel(getString("dialog.input.name"));
        colorLabel = new JLabel(getString("dialog.input.color"));
        idInput = new JTextField(editedElement != null ? editedElement.getId() : "");
        nameInput = new JTextField(editedElement != null ? editedElement.getName() : "");
        initialColor = editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE;
        colorInput = new RGBColorChooser(initialColor);
        previewBox = ColorPreviewBox.init(initialColor);
        okButton = new JButton(getString("button.ok"));

        this.setLayout(new GridBagLayout());
        this.add(idLabel, dialogElementPosition(0, 0, false));
        this.add(idInput, dialogElementPosition(0, 1, false));
        this.add(nameLabel, dialogElementPosition(1, 0, false));
        this.add(nameInput, dialogElementPosition(1, 1, false));
        this.add(colorLabel, dialogElementPosition(2, 0, true));
        this.add(colorInput, dialogElementPosition(3, 0, true));
        this.add(previewBox, dialogElementPosition(4, 0, true));
        this.add(idErrorLabel, dialogElementPosition(5, 0, true));

        final Border defaultBorder = idInput.getBorder();
        DocumentListener textFieldListener = new AbstractValidInputListener(okButton) {
            @Override
            protected boolean dataValid() {
                boolean idValidChars = idInput.getText().matches("^[a-z]*$");
                if (idValidChars) {
                    idInput.setBorder(defaultBorder);
                    idErrorLabel.setText(BLANK_STRING);
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

        colorInput.getSelectionModel().addChangeListener(e -> previewBox.setPreviewColor(colorInput.getColor()));

        okButton.setEnabled(editedElement != null);
        okButton.addActionListener(e -> getOptionPane((JComponent) e.getSource()).setValue(okButton));
    }
}
