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
