package com.rubynaxela.teaeditor.ui;

import com.rubynaxela.teaeditor.handlers.MenuHandler;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;
import com.rubynaxela.teaeditor.ui.components.MenuBar;
import com.rubynaxela.teaeditor.ui.components.*;
import com.rubynaxela.teaeditor.ui.dialogs.Dialogs;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getIcon;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class MainWindow extends JFrame {

    public com.rubynaxela.teaeditor.ui.components.MenuBar menuBar;
    public AbstractTablePanel brandsPanel = new BrandsPanel();
    public AbstractTablePanel shelvesPanel = new ShelvesPanel();
    public AbstractTablePanel teaBoxesPanel = new TeaBoxesPanel();

    public MainWindow() {
        this.setResizable(false);
        this.setTitle(getString("window.title"));
        this.setLayout(new GridBagLayout());

        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;

        this.add(brandsPanel, layoutConstraints);
        layoutConstraints.gridy = 1;
        this.add(shelvesPanel, layoutConstraints);
        layoutConstraints.gridx = 1;
        this.add(teaBoxesPanel, layoutConstraints);

        this.pack();
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (DataManager.dataChanged)
                    if (Dialogs.askYesNoQuestion(getString("dialog.message.save_before_close"),
                            true, getIcon("dialog.diskette")))
                        MenuHandler.saveFile.actionPerformed(null);
                System.exit(0);
            }
        });
    }

    public static void init() {
        Reference.window = new MainWindow();
        WindowUpdatesManager.masterUpdate();
    }
}
