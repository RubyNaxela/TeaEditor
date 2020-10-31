/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rubynaxela.teaeditor.TeaEditor;
import com.rubynaxela.teaeditor.ui.MainWindow;
import com.rubynaxela.teaeditor.ui.dialogs.Dialogs;

import javax.swing.*;
import java.io.File;
import java.util.Map;
import java.util.Objects;

import static com.rubynaxela.teaeditor.util.OsCheck.OSType.MAC_OS;
import static java.awt.event.KeyEvent.*;
import static javax.swing.KeyStroke.getKeyStroke;

@SuppressWarnings("unchecked")
public final class Reference {

    public static final File STARTING_DIRECTORY = new File(
            System.getProperty("user.home"), "Dropbox/Herbatki/ksiega_herbat/data");
    public static final File SAMPLE_FILE = new File(STARTING_DIRECTORY, "tea_data.json");
    public static final OsCheck.OSType OS = OsCheck.getOperatingSystemType();
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    public static MainWindow window;

    private static Map<String, String> primaryDictionary, backupDictionary;

    public static void init() {
        JSON_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            primaryDictionary = JSON_MAPPER.readValue(TeaEditor.class.getResource(
                    "/lang/" + Language.getUsedLanguage() + ".json"), Map.class);
            try {
                backupDictionary = JSON_MAPPER.readValue(TeaEditor.class.getResource(
                        "/lang/" + Language.ENGLISH_US + ".json"), Map.class);
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialogs.showError("Attempt to read language file has resulted an unhandled error.", true);
        }
    }

    public enum DataDialogFlags {
        NEW, EDIT, BRAND, SHELF
    }

    public static final class Resources {

        public static String getString(String key) {
            try {
                return Objects.requireNonNull(primaryDictionary.get(key));
            } catch (Exception ignored1) {
                try {
                    return Objects.requireNonNull(backupDictionary.get(key));
                } catch (Exception ignored2) {
                }
            }
            return key;
        }

        public static Icon getIcon(String key) {
            return new ImageIcon(TeaEditor.class.getResource(
                    "/icons/" + key.replaceAll("\\.", "/") + ".png"));
        }
    }

    public static final class Shortcuts {
        private static final boolean USE_CTRL = OS != MAC_OS;
        public static final KeyStroke
                CLOSE_STROKE = getKeyStroke(VK_W, USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK),
                OPEN_STROKE = getKeyStroke(VK_O, USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK),
                REDO_STROKE = getKeyStroke(VK_Y, USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK),
                SAVE_STROKE = getKeyStroke(VK_S, USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK),
                SAVE_AS_STROKE = getKeyStroke(VK_S, (USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK) + SHIFT_DOWN_MASK),
                UNDO_STROKE = getKeyStroke(VK_Z, USE_CTRL ? CTRL_DOWN_MASK : META_DOWN_MASK);
    }
}
