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

import com.rubynaxela.teaeditor.handlers.ButtonsHandler;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;
import com.rubynaxela.teaeditor.util.Reference;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * @since alpha 1.4
 */
public final class ShelvesPanel extends AbstractTablePanel {

    public ShelvesPanel() {
        titleLabel.setText(getString("label.shelves"));
        addButton.setText(getString("button.shelf.add"));
        removeButton.setText(getString("button.shelf.remove"));
        editButton.setText(getString("button.shelf.edit"));

        dataTable.setModel(ListsManager.shelvesTableModel);
        dataTable.getSelectionModel().addListSelectionListener(e -> WindowUpdatesManager.updateOnShelfSelection());

        addButton.addActionListener(ButtonsHandler.addShelf);
        removeButton.addActionListener(ButtonsHandler.removeShelf);
        editButton.addActionListener(ButtonsHandler.editShelf);

        updateButtons();
    }

    @Override
    public void updateButtons() {
        addButton.setEnabled(FileIOHandler.currentFile != null);
        removeButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedShelf() != null);
        editButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedShelf() != null);
        try {
            Reference.window.teaBoxesPanel.updateButtons();
        } catch (NullPointerException ignored) {
        }
    }
}
