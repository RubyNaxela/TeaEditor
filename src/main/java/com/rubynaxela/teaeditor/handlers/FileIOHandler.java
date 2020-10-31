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

import com.rubynaxela.teaeditor.datatypes.database.TeaData;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.ui.dialogs.AWTFileDialog;
import com.rubynaxela.teaeditor.ui.dialogs.Dialogs;

import java.io.File;

import static com.rubynaxela.teaeditor.util.Reference.JSON_MAPPER;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class FileIOHandler {

    public static final AWTFileDialog FILE_DIALOG = new AWTFileDialog();
    public static File currentFile;

    public static void parseJSON() {
        try {
            DataManager.setCurrentData(JSON_MAPPER.readValue(currentFile, TeaData.class));
        } catch (Exception ignored) {
            Dialogs.showError(getString("dialog.message.error.load_faild"));
        }
    }

    public static void exportJSON(File target) {
        try {
            JSON_MAPPER.writeValue(target, DataManager.getCurrentData());
        } catch (Exception ignored) {
            Dialogs.showError(getString("dialog.message.error.save_faild"));
        }
    }

    public static void exportJSON() {
        exportJSON(currentFile);
    }
}
