/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.managers;

import com.rubynaxela.teaeditor.datatypes.database.Brand;
import com.rubynaxela.teaeditor.datatypes.database.Shelf;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Vector;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

@SuppressWarnings("WeakerAccess")
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

    public static Brand getSelectedBrand() {
        int rowIndex = Reference.window.brandsPanel.dataTable.getSelectedRow();
        return rowIndex >= 0 && DataManager.getCurrentData() != null ?
                DataManager.getCurrentData().getBrands()[rowIndex] : null;
    }

    public static Shelf getSelectedShelf() {
        int rowIndex = Reference.window.shelvesPanel.dataTable.getSelectedRow();
        return rowIndex >= 0 && DataManager.getCurrentData() != null ?
                DataManager.getCurrentData().getShelves()[rowIndex] : null;
    }

    public static TeaBox getSelectedTeaBox() {
        int rowIndex = Reference.window.teaBoxesPanel.dataTable.getSelectedRow();
        return rowIndex >= 0 && DataManager.getCurrentData() != null && getSelectedShelf() != null ?
                getSelectedShelf().getTea_boxes()[rowIndex] : null;
    }
}
