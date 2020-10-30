package com.rubynaxela.teaeditor.ui.components;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;

public final class RGBColorChooser extends JColorChooser {

    public RGBColorChooser(Color color) {
        super(color);

        AbstractColorChooserPanel[] tabs = this.getChooserPanels();
        Component[] components = tabs[3].getComponents();
        Component[] slidersComponents = ((JPanel) components[0]).getComponents();

        ((JFormattedTextField) components[2]).setHorizontalAlignment(SwingConstants.LEFT);

        this.removeChooserPanel(tabs[0]);
        this.removeChooserPanel(tabs[1]);
        this.removeChooserPanel(tabs[2]);
        this.removeChooserPanel(tabs[4]);
        ((JPanel) components[0]).remove(slidersComponents[3]);
        ((JPanel) components[0]).remove(slidersComponents[8]);
        ((JPanel) components[0]).remove(slidersComponents[13]);
        this.remove(0);
    }
}
