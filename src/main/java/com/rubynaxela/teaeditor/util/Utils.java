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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.toHexString;

/**
 * The {@code Utils} class provides a number of utility functions that are used in different places throughout the project.
 *
 * @author Jacek Pawelski
 */
public final class Utils {

    /**
     * Converts a {@link Color} to hexadecimal hashtag notation.
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
     * Finds an {@link Identifiable} object in an array by its {@code id}.
     *
     * @param id    {@code id} field of the desired {@link Identifiable} object
     * @param array an array of objects to search through
     * @return the object with the requested {@code id}, {@code null} if nothing found
     * @see Identifiable
     */
    @Nullable
    public static Identifiable findIdInArray(String id, @Nonnull Identifiable[] array) {
        for (Identifiable obj : array)
            if (obj.getId().equals((id))) return obj;
        return null;
    }

    /**
     * Gives a reference to the {@link JOptionPane} parent of the given {@link JComponent} element
     *
     * @param component a dialog component
     * @return the {@code JOptionPane} that the given element is a child of
     */
    public static JOptionPane getOptionPane(JComponent component) {
        return component instanceof JOptionPane ? (JOptionPane) component : getOptionPane((JComponent) component.getParent());
    }

    /**
     * Returns a {@link GridBagConstraints} object with properties suitable for a dialog pane:
     * <ul>
     *     <li>{@code row} and {@code column} parameters will translate directly into {@code gridy} and {@code gridx} fields
     *         of the returned object respectively</li>
     *     <li>if {@code wide} is {@code true}, the {@link GridBagConstraints#gridwidth} field will be set to {@code 2}, which
     *         means it will take up space of two grid columns</li>
     * </ul>
     *
     * @param row    vertical position in the grid
     * @param column horizontal position in the grid
     * @param wide   determines if a component takes up space of two columns
     * @return a {@code GridBagConstraints} instance
     */
    @Nonnull
    public static GridBagConstraints dialogElementPosition(int row, int column, boolean wide) {
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
        if (wide) {
            gbc.gridwidth = 2;
        }
        return gbc;
    }

    /**
     * Formats a number returning a {@link String}:
     * <ul>
     *     <li>removes {@code .0} when the value is a whole number,</li>
     *     <li>can add a suffix at the end of the number,</li>
     *     <li>can replace the whole string with some text when the value is equal to {@code 0}.</li>
     * </ul>
     *
     * @param number   a numeric value
     * @param suffix   the suffix, may be {@code null}
     * @param zeroText displayed when the value is equal to {@code 0}, displays the number normally when {@code null}
     * @return a {@code String} containing the formatted number
     */
    @Nonnull
    public static String formatNumber(double number, @Nullable String suffix, @Nullable String zeroText) {
        if (number == 0 && zeroText != null)
            return zeroText;
        else {
            String ret = number % 1 == 0 ? "" + (int) number : "" + number;
            return suffix != null ? ret + suffix : ret;
        }
    }

    /**
     * Formats a number returning a {@link String}:
     * <ul>
     *     <li>removes {@code .0} when the value is a whole number,</li>
     *     <li>can add a suffix at the end of the number</li>
     * </ul>
     *
     * @param number   a numeric value
     * @param suffix   the suffix, may be {@code null}
     * @return a {@code String} containing the formatted number
     */
    public static String formatNumber(double number, @Nullable String suffix) {
        return formatNumber(number, suffix, null);
    }

    /**
     * Formats a number returning a {@link String}; removes {@code .0} when the value is a whole number</li>
     *
     * @param number   a numeric value
     * @return a {@code String} containing the formatted number
     */
    public static String formatNumber(double number) {
        return formatNumber(number, null, null);
    }
}
