/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.datatypes;

import java.awt.*;

public final class IdNameColorTriplet {

    public String id, name;
    public Color color;

    public IdNameColorTriplet(String id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
