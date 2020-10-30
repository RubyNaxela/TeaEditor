package com.rubynaxela.teaeditor.handlers;

import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;
import com.rubynaxela.teaeditor.ui.dialogs.Dialogs;

import java.awt.event.ActionListener;
import java.io.File;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getIcon;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class MenuHandler {

    public static ActionListener openFile = e -> {
        FileIOHandler.currentFile = FileIOHandler.FILE_DIALOG.chooseFileToLoad();
        if (FileIOHandler.currentFile != null) {
            FileIOHandler.parseJSON();
            WindowUpdatesManager.masterUpdate();
        }
    };

    public static ActionListener saveFile = e -> {
        FileIOHandler.exportJSON();
        DataManager.dataChanged = false;
    };

    public static ActionListener saveFileAs = e -> {
        File target = FileIOHandler.FILE_DIALOG.chooseFileToSave();
        if (target != null) FileIOHandler.exportJSON(target);
    };

    public static ActionListener closeFile = e -> {
        if (DataManager.dataChanged)
            if (Dialogs.askYesNoQuestion(getString("dialog.message.save_before_close"),
                    true, getIcon("dialog.diskette")))
                saveFile.actionPerformed(null);
        FileIOHandler.currentFile = null;
        DataManager.setCurrentData(null);
        WindowUpdatesManager.masterUpdate();
    };

    public static ActionListener undo = e -> Dialogs.showWarning(getString("dialog.message.feature_unavailable"));
    public static ActionListener redo = e -> Dialogs.showWarning(getString("dialog.message.feature_unavailable"));
}
