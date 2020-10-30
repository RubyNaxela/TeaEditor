package com.rubynaxela.teaeditor.managers;

import com.rubynaxela.teaeditor.util.Reference;

import java.util.Objects;

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
    }

    public static void updateOnShelfSelection() {
        Reference.window.shelvesPanel.updateButtons();
        if (ListsManager.getSelectedShelf() != null) Reference.window.brandsPanel.dataTable.clearSelection();
        updateTeaBoxesList();
    }

    public static void updateOnTeaBoxSelection() {
        Reference.window.teaBoxesPanel.updateButtons();
    }

    public static void updateLists() {
        ListsManager.brandsTableModel.setDataVector(
                DataManager.getBrandsDataVector(), ListsManager.brandsAndShelvesTableHeaders);
        ListsManager.shelvesTableModel.setDataVector(
                DataManager.getShelvessDataVector(), ListsManager.brandsAndShelvesTableHeaders);
        updateTeaBoxesList();
    }

    private static void updateTeaBoxesList() {
        Reference.window.teaBoxesPanel.changeTitle(
                ListsManager.getSelectedShelf() != null ?
                        Objects.requireNonNull(ListsManager.getSelectedShelf()).getName() : getString("label.tea_boxes"));
        ListsManager.teaBoxesTableModel.setDataVector(
                DataManager.getTeaBoxesDataVector(ListsManager.getSelectedShelf()), ListsManager.teaBoxesTableHeaders);
    }

}
