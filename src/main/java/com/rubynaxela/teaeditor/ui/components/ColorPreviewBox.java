/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.ui.components;

import javax.swing.*;
import java.awt.*;

public final class ColorPreviewBox extends JPanel {

    public ColorPreviewBox() {
        super();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(64, 32);
    }
}
