/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.ui.dialogs;

import com.rubynaxela.teaeditor.util.Reference;

import java.awt.*;
import java.io.File;

import static com.rubynaxela.teaeditor.util.OsCheck.OSType.WINDOWS;
import static com.rubynaxela.teaeditor.util.Reference.OS;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Reference.STARTING_DIRECTORY;

public final class AWTFileDialog extends FileDialog {

    public AWTFileDialog() {
        super(Reference.window);

        if (STARTING_DIRECTORY.exists()) this.setDirectory(STARTING_DIRECTORY.getAbsolutePath());
        else this.setDirectory(System.getProperty("user.home"));

        if (OS == WINDOWS) this.setFile("*.json");
        else this.setFilenameFilter((dir, name) -> name.endsWith(".json"));
    }

    public File chooseFileToLoad() {
        this.setTitle(getString("dialog.file_chooser.file_to_open"));
        this.setMode(LOAD);
        this.setVisible(true);
        return getSelectedFile();
    }

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
