package com.rubynaxela.teaeditor.ui.components;

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
