/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor;

import com.rubynaxela.teaeditor.handlers.MenuHandler;
import com.rubynaxela.teaeditor.ui.MainWindow;
import com.rubynaxela.teaeditor.util.Language;
import com.rubynaxela.teaeditor.util.LookAndFeel;
import com.rubynaxela.teaeditor.util.Reference;

public final class TeaEditor {

    public static void main(String[] args) {
        Language.useLanguage(Language.POLISH);
        Reference.init();
        LookAndFeel.init();
        MainWindow.init();

        // TODO Zmieić textfield z ID żeby info o błędzie było tylko grube + ogarnąć error border
        // TODO Naprawić ColorPreviewBox

        // TODO Dokumentacja - szczegółowa instrukcja instalacji (i kompilacji) oraz obsługi programu (PDF)
        // TODO Dokumentacja - JavaDoc, autogenerated PDF

        MenuHandler.openFile.actionPerformed(null);
    }
}