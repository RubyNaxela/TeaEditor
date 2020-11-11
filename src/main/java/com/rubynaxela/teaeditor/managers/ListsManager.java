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

import com.rubynaxela.teaeditor.datatypes.database.Brand;
import com.rubynaxela.teaeditor.datatypes.database.Shelf;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class ListsManager {

    public static DefaultTableModel
            brandsTableModel = new DefaultTableModel(),
            shelvesTableModel = new DefaultTableModel(),
            teaBoxesTableModel = new DefaultTableModel();
    public static Vector<String> brandsAndShelvesTableHeaders = new Vector<>(Arrays.asList(
            getString("table.header.id"), getString("table.header.name"), getString("table.header.color")));
    public static Vector<String> teaBoxesTableHeaders = new Vector<>(Arrays.asList(
            getString("table.header.id"), getString("table.header.name"), getString("table.header.brand"),
            getString("table.header.description"), getString("table.header.amount"),
            getString("table.header.stars")));

    public static int getSelectedBrandIndex() {
        return Reference.window.brandsPanel.dataTable.getSelectedRow();
    }

    public static Brand getSelectedBrand() {
        int rowIndex = getSelectedBrandIndex();
        return rowIndex >= 0 && DataManager.getCurrentData() != null ?
                DataManager.getCurrentData().getBrands()[rowIndex] : null;
    }

    public static int getSelectedShelfIndex() {
        return Reference.window.shelvesPanel.dataTable.getSelectedRow();
    }

    public static Shelf getSelectedShelf() {
        int rowIndex = getSelectedShelfIndex();
        return rowIndex >= 0 && DataManager.getCurrentData() != null ?
                DataManager.getCurrentData().getShelves()[rowIndex] : null;
    }

    public static void selectShelf(int rowIndex) {
        Reference.window.shelvesPanel.dataTable.changeSelection(rowIndex, -1, false, false);
    }

    public static int getSelectedTeaBoxIndex() {
        return Reference.window.teaBoxesPanel.dataTable.getSelectedRow();
    }

    public static TeaBox getSelectedTeaBox() {
        int rowIndex = getSelectedTeaBoxIndex();
        return rowIndex >= 0 && DataManager.getCurrentData() != null && getSelectedShelf() != null
                && getSelectedShelf().getTea_boxes().length > 0 ? getSelectedShelf().getTea_boxes()[rowIndex] : null;
    }
}
