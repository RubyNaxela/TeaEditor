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

import com.rubynaxela.teaeditor.datatypes.database.BrewingInstruction;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.Reference.Resources.BLANK_STRING;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.*;

public final class TeaBoxDialogPanel extends JPanel {

    public final JLabel idLabel, idErrorLabel, nameLabel, brandIdLabel, descriptionLabel, amountLabel, starsLabel, brewingLabel,
            brewingTempLabel, brewingTimeLabel, brewingReusesLabel, brewingGramsLabel;
    public final JTextField idInput, nameInput, brandIdInput, descriptionInput, amountInput, starsInput,
            brewingTempInput, brewingTimeInput, brewingReusesInput, brewingGramsInput;
    public final JPanel brewingPanel;
    public final JButton okButton;

    public TeaBoxDialogPanel(TeaBox editedElement) {
        BrewingInstruction instruction = editedElement != null ? editedElement.getBrewing() : null;

        idLabel = new JLabel(getString("dialog.input.id"));
        idErrorLabel = new JLabel(BLANK_STRING);
        nameLabel = new JLabel(getString("dialog.input.name"));
        brandIdLabel = new JLabel(getString("dialog.input.brand"));
        descriptionLabel = new JLabel(getString("dialog.input.description"));
        amountLabel = new JLabel(getString("dialog.input.amount"));
        starsLabel = new JLabel(getString("dialog.input.stars"));
        brewingLabel = new JLabel(getString("dialog.input.brewing"));

        brewingTempLabel = new JLabel(getString("dialog.input.brewing.temperature"));
        brewingTimeLabel = new JLabel(getString("dialog.input.brewing.time"));
        brewingReusesLabel = new JLabel(getString("dialog.input.brewing.reuses"));
        brewingGramsLabel = new JLabel(getString("dialog.input.brewing.grams"));

        idInput = new JTextField(editedElement != null ? editedElement.getId() : "");
        nameInput = new JTextField(editedElement != null ? editedElement.getName() : "");
        brandIdInput = new JTextField(editedElement != null ? editedElement.getBrand_id() : "");
        descriptionInput = new JTextField(editedElement != null ? editedElement.getDescription() : "");
        amountInput = new JTextField(editedElement != null ? formatNumber(editedElement.getAmount()) : "");
        starsInput = new JTextField(editedElement != null ? formatNumber(editedElement.getStars()) : "");

        brewingTempInput = new JTextField(instruction != null ? formatNumber(instruction.getTemperature()) : "");
        brewingTimeInput = new JTextField(instruction != null ? formatNumber(instruction.getTime()) : "");
        brewingReusesInput = new JTextField(instruction != null ? instruction.getReuses() : "");
        brewingGramsInput = new JTextField(instruction != null ? instruction.getGrams() : "");

        brewingPanel = new JPanel();
        okButton = new JButton(getString("button.ok"));

        brewingPanel.setLayout(new GridBagLayout());
        brewingPanel.add(brewingTempLabel, dialogElementPosition(0, 0, false));
        brewingPanel.add(brewingTempInput, dialogElementPosition(0, 1, false));
        brewingPanel.add(brewingTimeLabel, dialogElementPosition(1, 0, false));
        brewingPanel.add(brewingTimeInput, dialogElementPosition(1, 1, false));
        brewingPanel.add(brewingReusesLabel, dialogElementPosition(2, 0, false));
        brewingPanel.add(brewingReusesInput, dialogElementPosition(2, 1, false));
        brewingPanel.add(brewingGramsLabel, dialogElementPosition(3, 0, false));
        brewingPanel.add(brewingGramsInput, dialogElementPosition(3, 1, false));

        this.setLayout(new GridBagLayout());
        this.add(idLabel, dialogElementPosition(0, 0, false));
        this.add(idInput, dialogElementPosition(0, 1, false));
        this.add(nameLabel, dialogElementPosition(1, 0, false));
        this.add(nameInput, dialogElementPosition(1, 1, false));
        this.add(brandIdLabel, dialogElementPosition(2, 0, false));
        this.add(brandIdInput, dialogElementPosition(2, 1, false));
        this.add(descriptionLabel, dialogElementPosition(3, 0, false));
        this.add(descriptionInput, dialogElementPosition(3, 1, false));
        this.add(amountLabel, dialogElementPosition(4, 0, false));
        this.add(amountInput, dialogElementPosition(4, 1, false));
        this.add(starsLabel, dialogElementPosition(5, 0, false));
        this.add(starsInput, dialogElementPosition(5, 1, false));
        this.add(brewingLabel, dialogElementPosition(6, 0, true));
        this.add(brewingPanel, dialogElementPosition(7, 0, true));
        this.add(idErrorLabel, dialogElementPosition(8, 0, true));

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

        okButton.setEnabled(editedElement != null);
        okButton.addActionListener(e -> getOptionPane((JComponent) e.getSource()).setValue(okButton));
    }
}
