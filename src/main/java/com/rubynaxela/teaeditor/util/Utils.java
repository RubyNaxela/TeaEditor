package com.rubynaxela.teaeditor.util;

import com.rubynaxela.teaeditor.datatypes.database.Identifiable;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.toHexString;

public final class Utils {

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
     * @param id {@code id} field of the desired {@link Identifiable} object
     * @param array an array of objects to search through
     * @return a reference to the {@link Identifiable} object with the requested {@code id}, {@code null} if nothing found
     * @since alpha 1.5
     */
    public static Identifiable findIdInArray(String id, Identifiable[] array) {
        for (Identifiable obj : array)
            if (obj.getId().equals((id))) return obj;
        return null;
    }

    /**
     * @param parent a component of a {@link JOptionPane} dialog
     * @return a reference to the {@link JOptionPane} that the given element is a child of
     * @since beta 1.0
     */
    public static JOptionPane getOptionPane(JComponent parent) {
        return parent instanceof JOptionPane ? (JOptionPane) parent : getOptionPane((JComponent) parent.getParent());
    }

    /**
     * Creates a {@link GridBagConstraints} object with properties suitable for a dialog pane.
     *
     * @param x    horizontal position in the grid
     * @param y    vertical position in the grid
     * @param wide determines if a component takes up space of two columns
     * @return a {@link GridBagConstraints} instance
     * @since beta 1.2
     */
    public static GridBagConstraints dialogElementPosition(int x, int y, boolean wide) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = x == 0 ? GridBagConstraints.WEST : GridBagConstraints.WEST;
        if (wide) gbc.gridwidth = 2;
        return gbc;
    }

    /**
     * Formats a number removing "{@code .0}" when the value is a whole number, adding an optional suffix and optionally
     * replacing the number with some text when the value is equal to {@code 0}. Outputs a {@link String}.
     *
     * @param number   a numeric value
     * @param suffix   attached at the end of the number unless the value is equal to {@code 0}, may be {@code null}
     * @param zeroText displayed instead of the number when the value is equal to {@code 0}, display the number normally when
     *                 {@code null}
     * @return a {@link String} containing the formatted number
     * @since beta 1.1
     */
    public static String formatNumber(double number, @Nullable String suffix, @Nullable String zeroText) {
        if (zeroText != null && number == 0)
            return zeroText;
        else {
            String ret = number % 1 == 0 ? "" + (int) number : "" + number;
            return suffix != null ? ret + suffix : ret;
        }
    }
}
