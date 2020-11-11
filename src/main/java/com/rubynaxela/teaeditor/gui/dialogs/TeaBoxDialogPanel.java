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

import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.gui.components.BrewingInstructionPanel;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.util.DataFormat;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Objects;

import static com.rubynaxela.teaeditor.util.DataFormat.*;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.*;

public final class TeaBoxDialogPanel extends JPanel {

    public final JLabel idLabel, nameLabel, brandLabel, shelfLabel, amountLabel, amountUnitLabel, starsLabel, descriptionLabel,
            starsUnitLabel;
    public final JTextField idInput, nameInput, amountInput, starsInput;
    public final JComboBox<String> brandInput, shelfInput;
    public final JTextArea descriptionInput;
    public final JScrollPane descriptionPane;
    public final BrewingInstructionPanel brewingPanel;
    public final JButton okButton;

    public TeaBoxDialogPanel(TeaBox editedElement) {
        idLabel = new JLabel(getString("dialog.label.id"));
        nameLabel = new JLabel(getString("dialog.label.name"));
        brandLabel = new JLabel(getString("dialog.label.brand"));
        shelfLabel = new JLabel(getString("dialog.label.shelf"));
        amountLabel = new JLabel(getString("dialog.label.amount"));
        amountUnitLabel = new JLabel(getString("units.gram"));
        starsLabel = new JLabel(getString("dialog.label.stars"));
        starsUnitLabel = new JLabel(getString("units.per_five"));
        descriptionLabel = new JLabel(getString("dialog.label.description"));

        idInput = new JTextField(20);
        nameInput = new JTextField();
        brandInput = new JComboBox<>(DataManager.getBrandsNamesVector());
        shelfInput = new JComboBox<>(DataManager.getShelvesNamesVector());
        amountInput = new JTextField(2);
        starsInput = new JTextField();
        descriptionInput = new JTextArea(10, 30);
        descriptionPane = new JScrollPane(descriptionInput,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        brewingPanel = new BrewingInstructionPanel(editedElement != null ? editedElement.getBrewing() : null);
        okButton = new JButton(getString("button.ok"));

        initLayout();
        setup(editedElement);
    }

    private void initLayout() {
        this.setLayout(new GridBagLayout());
        this.add(idLabel, dialogElementPosition(0, 0));
        this.add(idInput, dialogElementPosition(0, 1, 2));
        this.add(nameLabel, dialogElementPosition(1, 0));
        this.add(nameInput, dialogElementPosition(1, 1, 2));
        this.add(brandLabel, dialogElementPosition(2, 0));
        this.add(brandInput, dialogElementPosition(2, 1, 2));
        this.add(shelfLabel, dialogElementPosition(3, 0));
        this.add(shelfInput, dialogElementPosition(3, 1, 2));
        this.add(amountLabel, dialogElementPosition(4, 0));
        this.add(amountInput, dialogElementPosition(4, 1));
        this.add(amountUnitLabel, dialogElementPosition(4, 2));
        this.add(starsLabel, dialogElementPosition(5, 0));
        this.add(starsInput, dialogElementPosition(5, 1));
        this.add(starsUnitLabel, dialogElementPosition(5, 2));
        this.add(descriptionLabel, dialogElementPosition(6, 0, 3));
        this.add(descriptionPane, dialogElementPosition(7, 0, 3));
        this.add(brewingPanel, dialogElementPosition(8, 0, 3));
    }

    private void setup(TeaBox editedElement) {
        idInput.setText(editedElement != null ? editedElement.getId() : "");
        nameInput.setText(editedElement != null ? editedElement.getName() : "");
        brandInput.setSelectedIndex(editedElement != null ? findIndexInArray(editedElement.getBrand_id(),
                DataManager.getCurrentData().getBrands()) : 0);
        shelfInput.setSelectedIndex(editedElement != null ? findIndexInArray(Objects.requireNonNull(
                DataManager.getTeaBoxShelf(editedElement)).getId(), DataManager.getCurrentData().getShelves())
                : ListsManager.getSelectedShelfIndex());
        amountInput.setText(editedElement != null ? DataFormat.formatNumber(editedElement.getAmount()) : "");
        amountInput.setHorizontalAlignment(SwingConstants.RIGHT);
        starsInput.setText(editedElement != null ?
                DataFormat.formatNumber(editedElement.getStars()).replace("0", "") : "");
        starsInput.setHorizontalAlignment(SwingConstants.RIGHT);
        descriptionInput.setLineWrap(true);
        descriptionInput.setWrapStyleWord(true);
        descriptionInput.setText(editedElement != null ?
                editedElement.getDescription().replace("<br>", "\n") : "");

        final DocumentListener textFieldListener = createInputValidator();
        idInput.getDocument().addDocumentListener(textFieldListener);
        nameInput.getDocument().addDocumentListener(textFieldListener);
        amountInput.getDocument().addDocumentListener(textFieldListener);
        starsInput.getDocument().addDocumentListener(textFieldListener);
        brewingPanel.brewingTempInput.getDocument().addDocumentListener(textFieldListener);
        brewingPanel.brewingTimeInput.getDocument().addDocumentListener(textFieldListener);
        brewingPanel.brewingReusesInput.getDocument().addDocumentListener(textFieldListener);
        brewingPanel.brewingGramsInput.getDocument().addDocumentListener(textFieldListener);

        shelfInput.setEnabled(editedElement != null);
        okButton.setEnabled(editedElement != null);
        okButton.addActionListener(e -> getOptionPane((JComponent) e.getSource()).setValue(okButton));
    }

    private AbstractValidInputListener createInputValidator() {
        return new AbstractValidInputListener(okButton) {
            @Override
            protected boolean dataValid() {
                if (amountInput.getText().contains(",")) new Thread(() ->
                        amountInput.setText(amountInput.getText().replace(",", "."))).start();
                boolean idValid = isValidId(idInput.getText()),
                        amountValid = isValidNumber(amountInput.getText()),
                        starsValid = isValidRating(starsInput.getText()),
                        tempValid = isValidInteger(brewingPanel.brewingTempInput.getText()),
                        timeValid = isValidInteger(brewingPanel.brewingTimeInput.getText()),
                        reusesValid = isValidInteger(brewingPanel.brewingReusesInput.getText()),
                        gramsValid = isValidGramsData(brewingPanel.brewingGramsInput.getText());

                if (!idValid) displayError(idInput, getString("dialog.label.invalid.id"));
                else cancelError(idInput);
                if (!amountValid) displayError(amountInput, getString("dialog.label.invalid.number"));
                else cancelError(amountInput);
                if (!starsValid) displayError(starsInput, getString("dialog.label.invalid.stars"));
                else cancelError(starsInput);
                if (!tempValid) displayError(brewingPanel.brewingTempInput,
                        getString("dialog.label.invalid.integer"));
                else cancelError(brewingPanel.brewingTempInput);
                if (!timeValid) displayError(brewingPanel.brewingTimeInput,
                        getString("dialog.label.invalid.integer"));
                else cancelError(brewingPanel.brewingTimeInput);
                if (!reusesValid) displayError(brewingPanel.brewingReusesInput,
                        getString("dialog.label.invalid.integer"));
                else cancelError(brewingPanel.brewingReusesInput);
                if (!gramsValid) displayError(brewingPanel.brewingGramsPane,
                        getString("dialog.label.invalid.grams_data"));
                else cancelError(brewingPanel.brewingGramsPane);

                return !idInput.getText().equals("")
                        && !nameInput.getText().equals("")
                        && !amountInput.getText().equals("")
                        && !brewingPanel.brewingTempInput.getText().equals("")
                        && !brewingPanel.brewingGramsInput.getText().equals("")
                        && idValid && amountValid && starsValid && tempValid && timeValid && reusesValid && gramsValid;
            }
        };
    }
}
