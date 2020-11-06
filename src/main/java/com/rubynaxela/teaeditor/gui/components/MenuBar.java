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

import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.handlers.MenuHandler;

import javax.swing.*;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Reference.Shortcuts.*;

@SuppressWarnings("FieldCanBeLocal")
public final class MenuBar extends JMenuBar {

    private final JMenuItem open, save, saveAs, close, undo, redo;

    public MenuBar() {
        JMenu fileMenu = new JMenu(getString("menu.file")),
                editMenu = new JMenu(getString("menu.edit"));

        open = new JMenuItem(getString("menu.item.open"));
        open.setAccelerator(OPEN_STROKE);
        open.addActionListener(MenuHandler.openFile);
        fileMenu.add(open);

        save = new JMenuItem(getString("menu.item.save"));
        save.setAccelerator(SAVE_STROKE);
        save.addActionListener(MenuHandler.saveFile);
        fileMenu.add(save);

        saveAs = new JMenuItem(getString("menu.item.save_as"));
        saveAs.setAccelerator(SAVE_AS_STROKE);
        saveAs.addActionListener(MenuHandler.saveFileAs);
        fileMenu.add(saveAs);

        close = new JMenuItem(getString("menu.item.close"));
        close.setAccelerator(CLOSE_STROKE);
        close.addActionListener(MenuHandler.closeFile);
        fileMenu.add(close);

        undo = new JMenuItem(getString("menu.item.undo"));
        undo.setAccelerator(UNDO_STROKE);
        undo.addActionListener(MenuHandler.undo);
        editMenu.add(undo);

        redo = new JMenuItem(getString("menu.item.redo"));
        redo.setAccelerator(REDO_STROKE);
        redo.addActionListener(MenuHandler.redo);
        editMenu.add(redo);

        this.add(fileMenu);
        this.add(editMenu);
        updateMenuBarButtons();
    }

    public void updateMenuBarButtons() {
        save.setEnabled(FileIOHandler.currentFile != null);
        saveAs.setEnabled(FileIOHandler.currentFile != null);
        close.setEnabled(FileIOHandler.currentFile != null);
        undo.setEnabled(FileIOHandler.currentFile != null);
        redo.setEnabled(FileIOHandler.currentFile != null);
    }
}
