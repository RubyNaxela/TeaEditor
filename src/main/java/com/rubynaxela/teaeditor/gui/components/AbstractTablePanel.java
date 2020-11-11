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

import javax.swing.*;
import java.awt.*;

public abstract class AbstractTablePanel extends JPanel {

    static int PANEL_WIDTH = 460, PANEL_HEIGHT = 320;
    public StaticJTable dataTable;
    JLabel titleLabel;
    JButton addButton, removeButton, editButton;

    AbstractTablePanel() {
        titleLabel = new JLabel();
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        dataTable = new StaticJTable();
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

    public void changeTitle(String newTitle) {
        titleLabel.setText(newTitle);
    }

    public abstract void updateButtons();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
