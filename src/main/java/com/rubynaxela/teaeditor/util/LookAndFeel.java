/*
 * Copyright (c) 2020 Jacek Pawelski a.k.a. RubyNaxela
 *
 * Licensed under the GNU General Public License v3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * The license is included in file 'LICENSE.txt', which is part of this
 * source code package. You may also obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rubynaxela.teaeditor.util;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

import static com.rubynaxela.teaeditor.util.Reference.OS;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * @since beta 1.0
 */
public final class LookAndFeel {

    public static void init() {
        switch (OS) {
            case MAC_OS:
                System.setProperty("apple.awt.application.name", getString("window.title"));
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                break;
            case WINDOWS:
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                break;
        }
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UIManager.put("CheckBox.icon.style", "filled");
    }
}
