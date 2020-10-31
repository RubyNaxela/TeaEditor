/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.ui.components;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public final class DefaultJScrollPane extends JScrollPane {

    private final int paneWidth, paneHeight;

    DefaultJScrollPane(Component component, int paneWidth, int paneHeight) {
        super(component);
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(paneWidth, paneHeight);
    }
}
