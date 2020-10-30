package com.rubynaxela.teaeditor.ui.components;

import com.rubynaxela.teaeditor.handlers.ButtonsHandler;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

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
