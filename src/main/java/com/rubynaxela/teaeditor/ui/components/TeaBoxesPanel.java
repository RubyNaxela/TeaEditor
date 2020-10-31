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

package com.rubynaxela.teaeditor.ui.components;

import com.rubynaxela.teaeditor.handlers.ButtonsHandler;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;

import java.awt.*;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class TeaBoxesPanel extends AbstractTablePanel {

    public TeaBoxesPanel() {
        titleLabel.setText(getString("label.tea_boxes"));
        addButton.setText(getString("button.tea_box.add"));
        removeButton.setText(getString("button.tea_box.remove"));
        editButton.setText(getString("button.tea_box.edit"));

        dataTable.setModel(ListsManager.teaBoxesTableModel);
        dataTable.getSelectionModel().addListSelectionListener(e -> WindowUpdatesManager.updateOnTeaBoxSelection());

        addButton.addActionListener(ButtonsHandler.addTeaBox);
        removeButton.addActionListener(ButtonsHandler.removeTeaBox);
        editButton.addActionListener(ButtonsHandler.editTeaBox);

        updateButtons();
    }

    @Override
    public void updateButtons() {
        addButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedShelf() != null);
        removeButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedTeaBox() != null);
        editButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedTeaBox() != null);
    }

    @Override
    protected void addScrollPane() {
        this.add(new DefaultJScrollPane(dataTable, PANEL_WIDTH * 2 - 10, 250));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH * 2, PANEL_HEIGHT);
    }
}
