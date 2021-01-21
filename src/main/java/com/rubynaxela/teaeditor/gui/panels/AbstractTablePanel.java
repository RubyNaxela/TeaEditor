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

package com.rubynaxela.teaeditor.gui.panels;

import com.rubynaxela.teaeditor.gui.components.DefaultJScrollPane;
import com.rubynaxela.teaeditor.gui.components.StaticTable;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code AbstractTablePanel} class is the base class for the three table panels
 * of the main window. Consists of a centered title, a table and three buttons
 *
 * @author Jacek Pawelski
 */
public abstract class AbstractTablePanel extends JPanel {

    protected static int PANEL_WIDTH = 460, PANEL_HEIGHT = 320;
    protected final JLabel titleLabel;
    protected final JButton addButton, removeButton, editButton;
    public StaticTable dataTable;

    AbstractTablePanel() {
        titleLabel = new JLabel();
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        dataTable = new StaticTable();
        addButton = new JButton();
        removeButton = new JButton();
        editButton = new JButton();

        this.add(titleLabel);
        addScrollPane();
        this.add(addButton);
        this.add(removeButton);
        this.add(editButton);
    }

    protected void addScrollPane() {
        this.add(new DefaultJScrollPane(dataTable, PANEL_WIDTH - 10, 250));
    }

    /**
     * Sets a new title that is displayed above the table
     *
     * @param newTitle text of the new title displayed above the table
     */
    public void changeTitle(String newTitle) {
        titleLabel.setText(newTitle);
    }

    /**
     * Updates the buttons status depending on the currently selected item. Must be implemented for specific cases
     */
    public abstract void updateButtons();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
