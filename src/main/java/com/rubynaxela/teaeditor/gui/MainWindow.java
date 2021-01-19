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

package com.rubynaxela.teaeditor.gui;

import com.rubynaxela.teaeditor.gui.components.MenuBar;
import com.rubynaxela.teaeditor.gui.panels.*;
import com.rubynaxela.teaeditor.handlers.DialogsHandler;
import com.rubynaxela.teaeditor.handlers.MenuHandler;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.dialogElementPosition;

public final class MainWindow extends JFrame {

    public com.rubynaxela.teaeditor.gui.components.MenuBar menuBar;
    public final AbstractTablePanel brandsPanel = new BrandsPanel();
    public final AbstractTablePanel shelvesPanel = new ShelvesPanel();
    public final AbstractTablePanel teaBoxesPanel = new TeaBoxesPanel();
    public final PreviewPanel previewPanel = new PreviewPanel();

    public MainWindow() {
        this.setResizable(false);
        this.setTitle(getString("window.title"));
        this.setLayout(new GridBagLayout());

        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        this.add(brandsPanel, dialogElementPosition(0, 0));
        this.add(previewPanel, dialogElementPosition(0, 1));
        this.add(shelvesPanel, dialogElementPosition(1, 0));
        this.add(teaBoxesPanel, dialogElementPosition(1, 1));

        this.pack();
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (DataManager.dataChanged)
                    switch (DialogsHandler.askYesNoCancelQuestion(getString("dialog.message.save_before_close"))) {
                        case POSITIVE:
                            MenuHandler.saveFile.actionPerformed(null);
                        case NEGATIVE:
                            System.exit(0);
                            break;
                    }
                else System.exit(0);
            }
        });
    }

    public static void init() {
        Reference.window = new MainWindow();
        WindowUpdatesManager.masterUpdate();
        WindowUpdatesManager.showWelcomeScreen();
    }
}
