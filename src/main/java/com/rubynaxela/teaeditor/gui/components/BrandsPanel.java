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

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * @since alpha 1.2
 */
public final class BrandsPanel extends AbstractTablePanel {

    public BrandsPanel() {
        titleLabel.setText(getString("label.brands"));
        addButton.setText(getString("button.brand.add"));
        removeButton.setText(getString("button.brand.remove"));
        editButton.setText(getString("button.brand.edit"));

        dataTable.setModel(ListsManager.brandsTableModel);
        dataTable.getSelectionModel().addListSelectionListener(e -> WindowUpdatesManager.updateOnBrandSelection());

        addButton.addActionListener(ButtonsHandler.addBrand);
        removeButton.addActionListener(ButtonsHandler.removeBrand);
        editButton.addActionListener(ButtonsHandler.editBrand);

        updateButtons();
    }

    @Override
    public void updateButtons() {
        addButton.setEnabled(FileIOHandler.currentFile != null);
        removeButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedBrand() != null);
        editButton.setEnabled(FileIOHandler.currentFile != null && ListsManager.getSelectedBrand() != null);
    }
}
