package com.rubynaxela.teaeditor.util;

import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
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
