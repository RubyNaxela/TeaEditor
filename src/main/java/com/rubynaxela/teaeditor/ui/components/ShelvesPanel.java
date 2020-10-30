package com.rubynaxela.teaeditor.ui.components;

import com.rubynaxela.teaeditor.handlers.ButtonsHandler;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;
import com.rubynaxela.teaeditor.util.Reference;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

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
