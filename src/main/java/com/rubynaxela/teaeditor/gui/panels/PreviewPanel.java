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

import com.rubynaxela.teaeditor.gui.components.DefaultJScrollPane;
import com.rubynaxela.teaeditor.gui.html.AbstractPreviewDocument;

import javax.swing.*;
import java.awt.*;

import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;

/**
 * The {@code PreviewPanel} class serves a panel for element preview documents. Is is a part of the main window
 *
 * @author Jacek Pawelski
 */
public final class PreviewPanel extends JPanel {

    static int PANEL_WIDTH = 920, PANEL_HEIGHT = 320;
    private final JEditorPane documentArea;

    public PreviewPanel() {
        final JScrollPane documentPane;
        documentArea = new JEditorPane();
        documentArea.setContentType("text/html");
        documentArea.setEditable(false);
        documentPane = new DefaultJScrollPane(documentArea, PANEL_WIDTH - 10, PANEL_HEIGHT - 10);
        documentPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(new GridBagLayout());
        this.add(documentPane, dialogElementPosition(0, 0));
    }

    /**
     * Displays a HTML document in the preview panel
     *
     * @param content the new HTML document to be displayed
     */
    public void setContent(AbstractPreviewDocument content) {
        documentArea.setText(content.write());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }
}
