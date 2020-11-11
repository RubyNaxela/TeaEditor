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

package com.rubynaxela.teaeditor.gui.components;

import javax.swing.*;
import java.awt.*;

public class ColorPreviewBox extends JPanel {

    public ColorPreviewBox() {
    }

    public static ColorPreviewBox init(Color initialColor) {
        return new ColorPreviewBox() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setPreviewColor(g, initialColor);
            }
        };
    }

    public void setPreviewColor(Graphics g, Color color) {
        g.setColor(color);
        g.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 5, 5);
        g.setColor(new Color(0x646464));
        g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 5, 5);
    }

    public void setPreviewColor(Color color) {
        setPreviewColor(this.getGraphics(), color);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(64, 32);
    }
}
