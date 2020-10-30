package com.rubynaxela.teaeditor.ui.components;

import javax.swing.*;

public final class StaticJTable extends JTable {

    StaticJTable() {
        super();
        DefaultListSelectionModel brandsListModel = new DefaultListSelectionModel();
        brandsListModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionModel(brandsListModel);
        this.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
