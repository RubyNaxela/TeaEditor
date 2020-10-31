/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.util;

import com.rubynaxela.teaeditor.datatypes.database.Identifiable;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.toHexString;

public final class Utils {

    private static final GridBagConstraints gbc = new GridBagConstraints();

    /**
     * Converts a {@link Color} to hexadecimal hashtag notation.
     *
     * @param color an {@code java.awt} {@link Color} object
     * @return a {@link String} containing the color in hexadecimal hashtag notation
     * @since alpha 1.1
     */
    public static String colorToHex(Color color) {
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
     * @since alpha 1.5
     */
    public static Identifiable findIdInArray(String id, Identifiable[] array) {
        for (Identifiable obj : array)
            if (obj.getId().equals((id))) return obj;
        return null;
    }

    /**
     * Gives a reference to the {@link JOptionPane} parent of the given {@link JComponent} element
     *
     * @param component a dialog component
     * @return the {@code JOptionPane} that the given element is a child of
     * @since beta 1.0
     */
    public static JOptionPane getOptionPane(JComponent component) {
        return component instanceof JOptionPane ? (JOptionPane) component : getOptionPane((JComponent) component.getParent());
    }

    /**
     * Returns a {@link GridBagConstraints} object with properties suitable for a dialog pane:
     * <ul>
     *     <li>{@code x} and {@code y} parameters will translate directly into {@code gridx} and {@code gridy} fields of the
     *         returned object respectively</li>
     *     <li></li>
     * </ul>
     *
     * @param x    horizontal position in the grid
     * @param y    vertical position in the grid
     * @param wide determines if a component takes up space of two columns
     * @return a {@code GridBagConstraints} instance
     * @since beta 1.2
     */
    public static GridBagConstraints dialogElementPosition(int x, int y, boolean wide) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = x == 0 ? GridBagConstraints.WEST : GridBagConstraints.WEST;
        if (wide) gbc.gridwidth = 2;
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
     * @since beta 1.1
     */
    public static String formatNumber(double number, @Nullable String suffix, @Nullable String zeroText) {
        if (number == 0 && zeroText != null)
            return zeroText;
        else {
            String ret = number % 1 == 0 ? "" + (int) number : "" + number;
            return suffix != null ? ret + suffix : ret;
        }
    }
}
