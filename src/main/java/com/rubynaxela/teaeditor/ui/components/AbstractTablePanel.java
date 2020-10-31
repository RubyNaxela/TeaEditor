/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.ui.components;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractTablePanel extends JPanel {

    static int PANEL_WIDTH = 460, PANEL_HEIGHT = 320;
    public StaticJTable dataTable;
    JLabel titleLabel;
    JButton addButton, removeButton, editButton;

    AbstractTablePanel() {
        super();
        titleLabel = new JLabel();
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 16));
        this.add(titleLabel);
        dataTable = new StaticJTable();
        addScrollPane();
        addButton = new JButton();
        this.add(addButton);
        removeButton = new JButton();
        this.add(removeButton);
        editButton = new JButton();
        this.add(editButton);
    }

    protected void addScrollPane() {
        this.add(new DefaultJScrollPane(dataTable, PANEL_WIDTH - 10, 250));
    }

    public void changeTitle(String newTitle) {
        titleLabel.setText(newTitle);
    }

    public abstract void updateButtons();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
