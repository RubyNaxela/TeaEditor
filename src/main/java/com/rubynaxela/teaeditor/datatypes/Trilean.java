/*
 * Copyright 2020 Jacek Pawelski a.k.a. RubyNaxela
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

package com.rubynaxela.teaeditor.datatypes;

/**
 * The {@code Trilean} enum is an implementation of the basic trinary logic value, which, unlike the binary logic value, can be
 * either positive, negative or neutral.
 *
 * @author Jacek Pawelski
 * @since beta 1.3
 */
public enum Trilean {
    POSITIVE, NEUTRAL, NEGATIVE;

    /**
     * Returns a trilean value corresponding to the integer value in 012 model, that is:
     * <ul>
     *     <li>{@code POSITIVE} for {@code 0}</li>
     *     <li>{@code NEGATIVE} for {@code 1}</li>
     *     <li>{@code NEUTRAL} for {@code 2}</li>
     * </ul>
     *
     * @param value integer trilean value in 012 model
     * @return a corresponding trilean value
     * @since beta 1.3
     */
    public static Trilean from012Model(int value) {
        return value == 2 ? NEUTRAL : (value == 0 ? POSITIVE : NEGATIVE);
    }
}