/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.util;

import java.util.Locale;

public final class OsCheck {

    public static OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            return OSType.MAC_OS;
        } else if (OS.contains("win")) {
            return OSType.WINDOWS;
        } else if (OS.contains("nux")) {
            return OSType.LINUX;
        } else {
            return OSType.OTHER;
        }
    }

    public enum OSType {
        WINDOWS, MAC_OS, LINUX, OTHER
    }
}