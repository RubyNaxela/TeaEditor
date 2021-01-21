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

import javax.annotation.Nullable;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.DataFormat.isValidId;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;
import static com.rubynaxela.teaeditor.util.Utils.getOptionPane;

/**
 * The {@code INCDataDialogPanel} class is a custom dialog window that takes ID,
 * name and color from the user, which are parameters of tea brands and shelves.
 * Consists of two text fields and a {@link RGBColorChooser}
 *
 * @author Jacek Pawelski
 * @see AbstractPrimaryElement
 * @see RGBColorChooser
 */
public final class INCDataDialogPanel extends JPanel {

    public final JLabel idLabel, nameLabel, colorLabel;
    public final JTextField idInput, nameInput;
    public final Color initialColor;
    public final RGBColorChooser colorInput;
    public final ColorPreviewBox previewBox;
    public final JButton okButton;

    /**
     * @param editedElement parameters of the edited element will be initially displayed in the dialog. Passing {@code null}
     *                      will result the text boxes to be empty and the color chooser to have white color selected
     */
    public INCDataDialogPanel(@Nullable AbstractPrimaryElement editedElement) {
        idLabel = new JLabel(getString("dialog.label.id"));
        nameLabel = new JLabel(getString("dialog.label.name"));
        colorLabel = new JLabel(getString("dialog.label.color"));

        idInput = new JTextField();
        nameInput = new JTextField();
        initialColor = editedElement != null ? Color.decode(editedElement.getColor()) : Color.WHITE;
        colorInput = new RGBColorChooser(initialColor);
        previewBox = ColorPreviewBox.init(initialColor);
        okButton = new JButton(getString("button.ok"));

        initLayout();
        setup(editedElement);
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(idLabel, dialogElementPosition(0, 0));
        this.add(idInput, dialogElementPosition(0, 1));
        this.add(nameLabel, dialogElementPosition(1, 0));
        this.add(nameInput, dialogElementPosition(1, 1));
        this.add(colorLabel, dialogElementPosition(2, 0, 2));
        this.add(colorInput, dialogElementPosition(3, 0, 2));
        this.add(previewBox, dialogElementPosition(4, 0, 2));
    }

    private void setup(AbstractPrimaryElement editedElement) {
        idInput.setText(editedElement != null ? editedElement.getId() : "");
        nameInput.setText(editedElement != null ? editedElement.getName() : "");

        final DocumentListener textFieldListener = createInputValidator();
        idInput.getDocument().addDocumentListener(textFieldListener);
        nameInput.getDocument().addDocumentListener(textFieldListener);

        colorInput.getSelectionModel().addChangeListener(e -> previewBox.setPreviewColor(colorInput.getColor()));

        okButton.setEnabled(editedElement != null);
        okButton.addActionListener(e -> getOptionPane((JComponent) e.getSource()).setValue(okButton));
    }

    private AbstractValidInputListener createInputValidator() {
        return new AbstractValidInputListener(okButton) {
            @Override
            protected boolean dataValid() {
                boolean idValid = isValidId(idInput.getText());

                if (!idValid) displayError(idInput, getString("dialog.label.invalid.id"));
                else cancelError(idInput);

                return !idInput.getText().equals("") && !nameInput.getText().equals("") && idValid;
            }
        };
    }
}
