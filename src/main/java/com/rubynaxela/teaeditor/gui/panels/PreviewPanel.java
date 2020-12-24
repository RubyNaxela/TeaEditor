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

package com.rubynaxela.teaeditor.gui.panels;

import com.hp.gagawa.java.elements.Html;
import com.rubynaxela.teaeditor.gui.components.DefaultJScrollPane;

import javax.swing.*;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;

@SuppressWarnings("FieldCanBeLocal")
public final class PreviewPanel extends JPanel {

    static int PANEL_WIDTH = 920, PANEL_HEIGHT = 320;
    private final JEditorPane documentArea;
    private final JScrollPane documentPane;

    public PreviewPanel() {
        this.setLayout(new GridBagLayout());
        documentArea = new JEditorPane();
        documentArea.setContentType("text/html");
        documentArea.setEditable(false);
        documentPane = new DefaultJScrollPane(documentArea, PANEL_WIDTH - 10, PANEL_HEIGHT - 10);
        documentPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.add(documentPane, dialogElementPosition(0, 0));
    }

    public void setContent(Html content) {
        documentArea.setText(content.write());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
