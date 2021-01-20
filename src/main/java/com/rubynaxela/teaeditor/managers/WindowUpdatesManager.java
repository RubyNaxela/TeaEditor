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

package com.rubynaxela.teaeditor.managers;

import com.rubynaxela.teaeditor.gui.html.BrandPreview;
import com.rubynaxela.teaeditor.gui.html.ShelfPreview;
import com.rubynaxela.teaeditor.gui.html.StartupPreview;
import com.rubynaxela.teaeditor.gui.html.TeaBoxPreview;
import com.rubynaxela.teaeditor.util.Reference;

import java.util.Objects;

import static com.rubynaxela.teaeditor.handlers.DialogsHandler.DataDialogFlag;
import static com.rubynaxela.teaeditor.handlers.DialogsHandler.DataDialogFlag.SHELF;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class WindowUpdatesManager {

    public static void masterUpdate() {
        Reference.window.menuBar.updateMenuBarButtons();
        Reference.window.brandsPanel.updateButtons();
        Reference.window.shelvesPanel.updateButtons();
        Reference.window.teaBoxesPanel.updateButtons();
        updateLists();
    }

    public static void updateOnBrandSelection() {
        Reference.window.brandsPanel.updateButtons();
        if (ListsManager.getSelectedBrand() != null) Reference.window.shelvesPanel.dataTable.clearSelection();
        Reference.window.previewPanel.setContent(new BrandPreview(ListsManager.getSelectedBrand()));
    }

    public static void updateOnShelfSelection() {
        Reference.window.shelvesPanel.updateButtons();
        if (ListsManager.getSelectedShelf() != null) Reference.window.brandsPanel.dataTable.clearSelection();
        updateTeaBoxesList();
        Reference.window.previewPanel.setContent(new ShelfPreview(ListsManager.getSelectedShelf()));
    }

    public static void updateOnTeaBoxSelection() {
        Reference.window.teaBoxesPanel.updateButtons();
        Reference.window.previewPanel.setContent(new TeaBoxPreview(ListsManager.getSelectedTeaBox()));
    }

    public static void updateLists() {
        ListsManager.brandsTableModel.setDataVector(
                DataManager.getBrandsDataVector(), ListsManager.brandsAndShelvesTableHeaders);
        ListsManager.shelvesTableModel.setDataVector(
                DataManager.getShelvesDataVector(), ListsManager.brandsAndShelvesTableHeaders);
        updateTeaBoxesList();
    }

    public static void updateLists(DataDialogFlag preserveSelection) {
        if (preserveSelection == SHELF) {
            int selectedIndex = ListsManager.getSelectedShelfIndex();
            updateLists();
            ListsManager.selectShelf(selectedIndex);
        }
    }

    private static void updateTeaBoxesList() {
        Reference.window.teaBoxesPanel.changeTitle(ListsManager.getSelectedShelf() != null ?
                Objects.requireNonNull(ListsManager.getSelectedShelf()).getName() : getString("label.tea_boxes"));
        ListsManager.teaBoxesTableModel.setDataVector(
                DataManager.getTeaBoxesDataVector(ListsManager.getSelectedShelf()), ListsManager.teaBoxesTableHeaders);
    }

    public static void showWelcomeScreen() {
        Reference.window.previewPanel.setContent(new StartupPreview());
    }
}
