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

import java.util.Arrays;

/**
 * @since alpha 1.4
 */
public final class Language {

    public static final String
            ENGLISH_US = "en_US",
            POLISH = "pl_PL";

    private static String usedLanguage = ENGLISH_US;

    public static void useLanguage(String languageCode) {
        if (Arrays.asList(POLISH, ENGLISH_US).contains(languageCode)) usedLanguage = languageCode;
    }

    public static String getUsedLanguage() {
        return usedLanguage;
    }
}
