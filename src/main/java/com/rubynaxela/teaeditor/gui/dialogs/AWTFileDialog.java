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

package com.rubynaxela.teaeditor.gui.dialogs;

import com.rubynaxela.teaeditor.util.Reference;

import java.awt.*;
import java.io.File;

import static com.rubynaxela.teaeditor.util.OsCheck.OSType.WINDOWS;
import static com.rubynaxela.teaeditor.util.Reference.OS;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Reference.STARTING_DIRECTORY;

/**
 * The {@code AWTFileDialog} class is an implementation of {@link FileDialog} which
 * is the only non-Swing GUI component used in the project. The file dialog accepts
 * only {@code .json} files and its default directory is set to user's home directory
 *
 * @author Jacek Pawelski
 */
public final class AWTFileDialog extends FileDialog {

    public AWTFileDialog() {
        super(Reference.window);

        if (STARTING_DIRECTORY.exists()) this.setDirectory(STARTING_DIRECTORY.getAbsolutePath());
        else this.setDirectory(System.getProperty("user.home"));

        if (OS == WINDOWS) this.setFile("*.json");
        else this.setFilenameFilter((dir, name) -> name.endsWith(".json"));
    }

    /**
     * Opens a file selection dialog in load mode
     *
     * @return file chosen by the user
     */
    public File chooseFileToLoad() {
        this.setTitle(getString("dialog.file_chooser.file_to_open"));
        this.setMode(LOAD);
        this.setVisible(true);
        return getSelectedFile();
    }

    /**
     * Opens a file selection dialog in save mode
     *
     * @return file chosen by the user
     */
    public File chooseFileToSave() {
        this.setTitle(getString("dialog.file_chooser.file_to_save"));
        this.setMode(SAVE);
        this.setVisible(true);
        return getSelectedFile();
    }

    private File getSelectedFile() {
        try {
            return new File(this.getDirectory(), this.getFile());
        } catch (NullPointerException ignored) {
            return null;
        }
    }
}
