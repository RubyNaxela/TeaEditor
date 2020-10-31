/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

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
