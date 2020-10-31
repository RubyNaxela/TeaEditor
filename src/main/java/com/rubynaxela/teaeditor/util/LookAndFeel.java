/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.util;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

import static com.rubynaxela.teaeditor.util.OsCheck.OSType.MAC_OS;
import static com.rubynaxela.teaeditor.util.Reference.OS;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class LookAndFeel {

    public static void init() {
        if (OS == MAC_OS) {
            System.setProperty("apple.awt.application.name", getString("window.title"));
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
