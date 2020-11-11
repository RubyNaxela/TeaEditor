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

import com.rubynaxela.teaeditor.datatypes.database.Identifiable;
import com.rubynaxela.teaeditor.gui.dialogs.INCDataDialogPanel;
import com.rubynaxela.teaeditor.gui.dialogs.TeaBoxDialogPanel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.toHexString;

/**
 * The {@code Utils} class provides a number of unclassified utility
 * functions that are used in different places throughout the project
 *
 * @author Jacek Pawelski
 */
public final class Utils {

    /**
     * Converts a {@link Color} to hexadecimal hashtag notation
     *
     * @param color an {@code java.awt} {@link Color} object
     * @return a {@link String} containing the color in hexadecimal hashtag notation
     */
    @Nonnull
    public static String colorToHex(@Nonnull Color color) {
        String r = toHexString(color.getRed()), g = toHexString(color.getGreen()), b = toHexString(color.getBlue());
        return "#" + (r.length() == 2 ? r : "0" + r) + (g.length() == 2 ? g : "0" + g) + (b.length() == 2 ? b : "0" + b);
    }

    /**
     * Finds an {@link Identifiable} object in an array by its {@code id}
     *
     * @param id    {@code id} of the desired {@link Identifiable} object
     * @param array an array of objects to search through
     * @return the object with the requested {@code id}, or {@code null} if not found
     * @see Identifiable
     */
    @Nullable
    public static Identifiable findIdInArray(String id, @Nonnull Identifiable[] array) {
        for (Identifiable obj : array) if (obj.getId().equals((id))) return obj;
        return null;
    }

    /**
     * Finds the index of an {@link Identifiable} object in an array by its {@code id}
     *
     * @param id    {@code id} of the desired {@link Identifiable} object
     * @param array an array of objects to search through
     * @return the index of the object with the requested {@code id}, or {@code -1} if not found
     * @see Identifiable
     */
    public static int findIndexInArray(String id, @Nonnull Identifiable[] array) {
        for (int i = 0; i < array.length; i++) if (array[i].getId().equals(id)) return i;
        return -1;
    }

    /**
     * Provides a reference to the {@link JOptionPane} parent of a given {@link JComponent} element
     *
     * @param component a dialog component
     * @return the {@code JOptionPane} that a given element is a child of
     */
    public static JOptionPane getOptionPane(JComponent component) {
        return component instanceof JOptionPane ? (JOptionPane) component : getOptionPane((JComponent) component.getParent());
    }

    /**
     * Returns a {@link GridBagConstraints} object with properties suitable for a dialog pane:
     * <ul>
     *     <li>{@code row} and {@code column} parameters translate directly into {@code gridy} and {@code gridx} fields
     *         of the returned object respectively</li>
     *     <li>the {@code width} parameter translates to {@link GridBagConstraints#gridwidth} field of the returned object,
     *         which means it will take up space of that much grid columns</li>
     * </ul>
     *
     * @param row    vertical position in the grid
     * @param column horizontal position in the grid
     * @param width  determines the component width (in columns)
     * @return a {@code GridBagConstraints} instance
     */
    @Nonnull
    public static GridBagConstraints dialogElementPosition(int row, int column, int width) {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = column;
        gbc.gridy = row;
        if (column == 0) {
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 0, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
        } else {
            gbc.anchor = GridBagConstraints.EAST;
            gbc.insets = new Insets(5, 5, 5, 0);
            gbc.fill = GridBagConstraints.HORIZONTAL;
        }
        gbc.gridwidth = width;
        return gbc;
    }

    /**
     * Returns a {@link GridBagConstraints} object where {@code row} and {@code column} parameters translate directly into
     * {@code gridy} and {@code gridx} fields of the returned object respectively
     *
     * @param row    vertical position in the grid
     * @param column horizontal position in the grid
     * @return a {@code GridBagConstraints} instance
     */
    @Nonnull
    public static GridBagConstraints dialogElementPosition(int row, int column) {
        return dialogElementPosition(row, column, 1);
    }

    /**
     * Used to allocate a concrete amount of space for a label while a dialog layout is initialized
     *
     * @param lines the maximal expected number of lines that the label will take up
     * @return a placeholder html text consisting of invisible characters
     * @see INCDataDialogPanel
     * @see TeaBoxDialogPanel
     */
    @Deprecated
    public static String stringPlaceholder(int lines) {
        StringBuilder ret = new StringBuilder("<html>&nbsp;");
        for (int i = 1; i < lines; i++) ret.append("<br>&nbsp;");
        ret.append("</html>");
        return ret.toString();
    }
}
