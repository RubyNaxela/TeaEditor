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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.rubynaxela.teaeditor.util.DataFormat.RoundMode.CLOSE;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * The {@code DataFormat} class provides a number of data format related
 * functions that are used for validating input and displaying output data
 *
 * @author Jacek Pawelski
 */
public final class DataFormat {


    /**
     * Checks if a given {@code String} is a valid ID, that is, whether it only consists of small latin letters and underscores
     *
     * @param id a {@code String} value
     * @return {@code true} if the {@code String} is a valid ID and {@code false} otherwise
     */
    public static boolean isValidId(@Nonnull String id) {
        return id.matches("^([a-z]|_)*$");
    }

    /**
     * Checks if a given {@code String} contains a valid parsable floating point number
     * (where {@code ""} is also considered valid as it is parsed to 0)
     *
     * @param number a {@code String} value
     * @return {@code true} if the {@code String} contains a valid number and {@code false} otherwise
     * @see DataFormat#parseDouble
     */
    public static boolean isValidNumber(@Nonnull String number) {
        return number.matches("^(?!^\\.$)(?!^([\\d]*\\.[\\d]*){2,}$)[\\d.]*$");
    }

    /**
     * Checks if a given {@code String} contains a valid parsable integer (and not a floating point number)
     * (where {@code ""} is also considered valid as it is parsed to 0)
     *
     * @param number a {@code String} value
     * @return {@code true} if the {@code String} contains an integer and {@code false} otherwise
     * @see DataFormat#parseInt
     */
    public static boolean isValidInteger(@Nonnull String number) {
        return number.matches("^\\d*$");
    }

    /**
     * Checks if a given {@code String} contains valid rating (is in range from 1 to 5 with step 0.5)
     * (where {@code ""} is also considered valid as it is translated to null)
     *
     * @param number a {@code String} value
     * @return {@code true} if the {@code String} contains valid rating and {@code false} otherwise
     */
    public static boolean isValidRating(@Nonnull String number) {
        if (number.equals("")) return true;
        if (isValidNumber(number)) {
            double rating = parseDouble(number);
            return rating % 0.5 == 0 && rating >= 1 && rating <= 5;
        }
        return false;
    }

    /**
     * Checks if a given {@code String} is valid grams data (compatible with the database format)
     *
     * @param data a {@code String} value
     * @return {@code true} if the {@code String} is valid grams data and {@code false} otherwise
     */
    public static boolean isValidGramsData(@Nonnull String data) {
        return data.matches("^(((?!^\\.$)(?!^(\\d*\\.\\d*){2,}$)[\\d.])+:\\d+)?" +
                "(\n((?!^\\.$)(?!^(\\d*\\.\\d*){2,}$)[\\d.])+:\\d+)*$");
    }

    /**
     * Parses an {@code double} number from {@code String}. Passed parameter may be {@code null}
     * or an empty {@code String} - in that cases the function will return {@code 0}
     *
     * @param number the number as a {@code String} value
     * @return parsed {@code double}
     */
    public static double parseDouble(@Nullable String number) {
        if (number == null || number.equals("")) return 0;
        else return Double.parseDouble(number);
    }

    /**
     * Parses an {@code int} number from {@code String}. Passed parameter may be {@code null}
     * or an empty {@code String} - in that cases the function will return {@code 0}
     *
     * @param number the number as a {@code String} value
     * @return parsed {@code int}
     */
    public static int parseInt(@Nullable String number) {
        if (number == null || number.equals("")) return 0;
        else return Integer.parseInt(number);
    }

    /**
     * Rounds a number to a multiple of a given unit
     *
     * @param number a numeric value
     * @param unit   a unit to round to
     * @param mode   can be one of: {@code FLOOR} - rounds down; {@code CEIL} - rounds up;
     *               {@code ROUNDS} - rounds to the closest multiple of the unit
     * @return a {@code double} containing the rounded number
     */
    public static double roundNumber(double number, double unit, RoundMode mode) {
        int whole = (int) (number / unit);
        switch (mode) {
            case FLOOR:
                return whole * number;
            case CEIL:
                return (whole + 1) * number;
            case CLOSE:
                return (number / unit) % 1 < 0.5 ? whole * number : (whole + 1) * number;
            default:
                return Double.MAX_VALUE;
        }
    }

    /**
     * Rounds a number to an integer
     *
     * @param number a numeric value
     * @param mode   can be one of: {@code FLOOR} - rounds down; {@code CEIL} - rounds up;
     *               {@code ROUNDS} - rounds to the closest integer
     * @return a {@code double} containing the rounded number
     */
    public static int roundNumber(double number, RoundMode mode) {
        return (int) roundNumber(number, 1, mode);
    }

    /**
     * Rounds a number to the closest multiple of a given unit
     *
     * @param number a numeric value
     * @param unit   a unit to round to
     * @return a {@code double} containing the rounded number
     */
    public static double roundNumber(double number, double unit) {
        int whole = (int) (number / unit);
        return (number / unit) % 1 < 0.5 ? whole * number : (whole + 1) * number;
    }

    /**
     * Rounds a number to the closest integer
     *
     * @param number a numeric value
     * @return a {@code double} containing the rounded number
     */
    public static int roundNumber(double number) {
        return roundNumber(number, CLOSE);
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
     * @param number a numeric value
     * @param suffix the suffix, may be {@code null}
     * @return a {@code String} containing the formatted number
     */
    public static String formatNumber(double number, @Nullable String suffix) {
        return formatNumber(number, suffix, null);
    }

    /**
     * Formats a number returning a {@link String}; removes {@code .0} when the value is a whole number
     *
     * @param number a numeric value
     * @return a {@code String} containing the formatted number
     */
    public static String formatNumber(double number) {
        return formatNumber(number, null, null);
    }

    public static String displayStars(double stars, boolean displayNumeric) {
        if (stars == 0) return getString("table.none");
        int doubleStars = (int) (stars * 2);
        final StringBuilder ret = new StringBuilder();
        for (int i = 0; i < 10; i++)
            if (doubleStars >= 1) {
                ret.append("\u2605");
                doubleStars--;
            } else ret.append("\u2606");
        if (displayNumeric)
            ret.append(" (").append(formatNumber(stars, " " + getString("units.per_five"))).append(")");
        return ret.toString();
    }

    public enum RoundMode {
        FLOOR, CEIL, CLOSE
    }
}
