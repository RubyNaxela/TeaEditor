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

package com.rubynaxela.teaeditor.gui.components;

import com.rubynaxela.teaeditor.datatypes.database.BrewingInstruction;
import com.rubynaxela.teaeditor.util.DataFormat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class BrewingInstructionPanel extends JPanel {

    public final JLabel brewingTempLabel, tempUnitLabel, brewingTimeLabel,
            timeUnitLabel, brewingReusesLabel, reusesUnitLabel, brewingGramsLabel;
    public final JTextField brewingTempInput, brewingTimeInput, brewingReusesInput;
    public final JCheckBox addMinuteCheckbox;
    public final JTextArea brewingGramsInput;
    public final JScrollPane brewingGramsPane;

    public BrewingInstructionPanel(BrewingInstruction instruction) {
        brewingTempLabel = new JLabel(getString("dialog.label.brewing.temperature"));
        tempUnitLabel = new JLabel(getString("units.degree"));
        brewingTimeLabel = new JLabel(getString("dialog.label.brewing.time"));
        timeUnitLabel = new JLabel(getString("units.minute"));
        brewingReusesLabel = new JLabel(getString("dialog.label.brewing.reuses"));
        reusesUnitLabel = new JLabel(getString("units.times"));
        addMinuteCheckbox = new JCheckBox(getString("dialog.label.additional_minute"));
        brewingGramsLabel = new JLabel(getString("dialog.label.brewing.grams"));

        brewingTempInput = new JTextField(2);
        brewingTimeInput = new JTextField();
        brewingReusesInput = new JTextField();
        brewingGramsInput = new JTextArea(3, 10);
        brewingGramsPane = new JScrollPane(brewingGramsInput,
                VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLayout(new GridBagLayout());
        this.add(brewingTempLabel, dialogElementPosition(0, 0));
        this.add(brewingTempInput, dialogElementPosition(0, 1));
        this.add(tempUnitLabel, dialogElementPosition(0, 2));
        this.add(brewingTimeLabel, dialogElementPosition(1, 0));
        this.add(brewingTimeInput, dialogElementPosition(1, 1));
        this.add(timeUnitLabel, dialogElementPosition(1, 2));
        this.add(brewingReusesLabel, dialogElementPosition(2, 0));
        this.add(brewingReusesInput, dialogElementPosition(2, 1));
        this.add(reusesUnitLabel, dialogElementPosition(2, 2));
        this.add(addMinuteCheckbox, dialogElementPosition(2, 3));
        this.add(brewingGramsLabel, dialogElementPosition(3, 0, 4));
        this.add(brewingGramsPane, dialogElementPosition(4, 0));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                getString("dialog.label.brewing"), TitledBorder.LEFT, TitledBorder.TOP));

        brewingTempInput.setText(instruction != null ? DataFormat.formatNumber(instruction.getTemperature()) : "");
        brewingTempInput.setHorizontalAlignment(SwingConstants.RIGHT);
        brewingTimeInput.setText(instruction != null ? DataFormat.formatNumber(instruction.getTime()) : "");
        brewingTimeInput.setHorizontalAlignment(SwingConstants.RIGHT);
        brewingReusesInput.setText(instruction != null && instruction.getReuses() != null ?
                instruction.getReuses().replace("/1", "") : "");
        brewingReusesInput.setHorizontalAlignment(SwingConstants.RIGHT);
        addMinuteCheckbox.setSelected(instruction != null && instruction.getReuses() != null
                && instruction.getReuses().contains("/1"));
        brewingGramsInput.setText(instruction != null ? instruction.getGrams().replace("/", "\n") : "");
    }
}
