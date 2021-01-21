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

package com.rubynaxela.teaeditor.handlers;

import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;

import java.awt.event.ActionListener;
import java.io.File;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * The {@code MenuHandler} class contains functions called by the menu bar buttons
 *
 * @author Jacek Pawelski
 */
public final class MenuHandler {

    public static ActionListener saveFile = e -> {
        if (FileIOHandler.currentFile == null)
            FileIOHandler.currentFile = FileIOHandler.FILE_DIALOG.chooseFileToSave();
        FileIOHandler.exportJSON();
        DataManager.dataChanged = false;
        DataManager.newFileCreated = false;
    };

    public static ActionListener newDatabase = e -> {
        if (DataManager.dataChanged)
            switch (DialogsHandler.askYesNoCancelQuestion(getString("dialog.message.save_before_close"))) {
                case NEUTRAL:
                    return;
                case POSITIVE:
                    saveFile.actionPerformed(null);
                    break;
            }
        FileIOHandler.currentFile = null;
        DataManager.newFileCreated = true;
        DataManager.setCurrentData(DataManager.createEmptyDatabase());
        WindowUpdatesManager.updateLists();
    };

    public static ActionListener openFile = e -> {
        FileIOHandler.currentFile = FileIOHandler.FILE_DIALOG.chooseFileToLoad();
        if (FileIOHandler.currentFile != null) {
            FileIOHandler.parseJSON();
            WindowUpdatesManager.masterUpdate();
            DataManager.newFileCreated = false;
        }
    };

    public static ActionListener saveFileAs = e -> {
        File target = FileIOHandler.FILE_DIALOG.chooseFileToSave();
        if (target != null) FileIOHandler.exportJSON(target);
        DataManager.dataChanged = false;
        DataManager.newFileCreated = false;
    };

    public static ActionListener closeFile = e -> {
        if (DataManager.dataChanged)
            switch (DialogsHandler.askYesNoCancelQuestion(getString("dialog.message.save_before_close"))) {
                case NEUTRAL:
                    return;
                case POSITIVE:
                    saveFile.actionPerformed(null);
                    break;
            }
        FileIOHandler.currentFile = null;
        DataManager.newFileCreated = false;
        DataManager.setCurrentData(null);
        WindowUpdatesManager.masterUpdate();
    };

    public static ActionListener exitProgram = e -> {
        if (DataManager.dataChanged)
            switch (DialogsHandler.askYesNoCancelQuestion(getString("dialog.message.save_before_close"))) {
                case POSITIVE:
                    saveFile.actionPerformed(null);
                    break;
                case NEUTRAL:
                    return;
            }
        System.exit(0);
    };

    public static ActionListener undo = e -> DialogsHandler.showWarning(getString("dialog.message.feature_unavailable"));
    public static ActionListener redo = e -> DialogsHandler.showWarning(getString("dialog.message.feature_unavailable"));
}
