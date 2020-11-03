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

package com.rubynaxela.teaeditor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.formdev.flatlaf.icons.*;
import com.rubynaxela.teaeditor.TeaEditor;
import com.rubynaxela.teaeditor.ui.MainWindow;
import com.rubynaxela.teaeditor.ui.dialogs.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
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

        /**
         * Returns a localized string from the given key. If not found in primary dictionary (that is, by default, the
         * currently used language file), then searches in the backup dictionary (that is, by default, English (US)). If
         * nothing is found in both, returns the key itself.
         *
         * @param key dictionary key of the string
         * @return localized string
         * @since alpha 1.4
         */
        public static String getString(String key) {
            if (primaryDictionary.containsKey(key))
                return Objects.requireNonNull(primaryDictionary.get(key));
            else if (backupDictionary.containsKey(key))
                return Objects.requireNonNull(backupDictionary.get(key));
            else
                return key;
        }

        /**
         * Returns a localized string from the given key. If not found in primary dictionary (that is, by default, the
         * currently used language file), then searches in the backup dictionary (that is, by default, English (US)). If
         * nothing is found in both, returns the fallback value.
         *
         * @param key           dictionary key of the string
         * @param fallbackValue the fallback value
         * @return localized string or the fallback value
         * @since beta 1.3
         */
        public static String getString(String key, String fallbackValue) {
            return (primaryDictionary.containsKey(key) || backupDictionary.containsKey(key)) ? getString(key) : fallbackValue;
        }

        public static Icon getIcon(String key) {
            switch (key) {
                case "dialog.info":
                    return new FlatOptionPaneInformationIcon();
                case "dialog.warning":
                    return new FlatOptionPaneWarningIcon();
                case "dialog.error":
                    return new FlatOptionPaneErrorIcon();
                case "dialog.question":
                    return new FlatOptionPaneQuestionIcon();
                case "dialog.data":
                    return new FlatOptionPaneAbstractIcon("Actions.Green", "Actions.Green") {
                        @Override
                        protected Shape createOutside() {
                            return new Ellipse2D.Float(2, 2, 28, 28);
                        }

                        @Override
                        protected Shape createInside() {
                            Path2D q = new Path2D.Float(Path2D.WIND_EVEN_ODD);
                            q.append(new Rectangle2D.Float(8, 8, 6, 16), false);
                            q.append(new Rectangle2D.Float(18, 8, 6, 6), false);
                            q.append(new Rectangle2D.Float(18, 18, 6, 6), false);
                            return q;
                        }
                    };
                default:
                    return null;
            }
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
