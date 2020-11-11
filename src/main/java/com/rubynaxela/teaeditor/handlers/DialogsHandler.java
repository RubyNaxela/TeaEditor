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

package com.rubynaxela.teaeditor.handlers;

import com.rubynaxela.teaeditor.datatypes.FlatTeaBox;
import com.rubynaxela.teaeditor.datatypes.IdNameColorTriplet;
import com.rubynaxela.teaeditor.datatypes.Trilean;
import com.rubynaxela.teaeditor.datatypes.database.AbstractPrimaryElement;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.gui.dialogs.INCDataDialogPanel;
import com.rubynaxela.teaeditor.gui.dialogs.TeaBoxDialogPanel;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.util.Reference;

import javax.swing.*;

import static com.rubynaxela.teaeditor.util.DataFormat.parseDouble;
import static com.rubynaxela.teaeditor.util.DataFormat.parseInt;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlag;
import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlag.BRAND;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getIcon;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class DialogsHandler {

    public static void showInfo(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                getIcon("dialog.info"), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showWarning(String message) {
        JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.warning"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                getIcon("dialog.warning"), new String[]{getString("button.ok")},
                getString("button.ok"));
    }

    public static void showError(String message, boolean exitOnClose) {
        final String title = getString("dialog.title.error");
        final String okButtonText = getString("button.ok", "OK");
        Object okButton;
        if (exitOnClose) {
            okButton = new JButton(okButtonText);
            ((JButton) okButton).addActionListener(e -> System.exit(0));
        } else
            okButton = okButtonText;
        JOptionPane.showOptionDialog(Reference.window, message, !title.equals("dialog.title.error") ? title : "Error",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
                getIcon("dialog.error"), new Object[]{okButton}, okButton);
    }

    public static void showError(String message) {
        showError(message, false);
    }

    public static boolean askYesNoQuestion(String message, boolean defaultAnswer) {
        return JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                getIcon("dialog.question"), new String[]{getString("button.yes"), getString("button.no")},
                defaultAnswer ? getString("button.yes") : getString("button.no")) == 0;
    }

    public static Trilean askYesNoCancelQuestion(String message) {
        int answer = JOptionPane.showOptionDialog(Reference.window, message, getString("dialog.title.default"),
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, getIcon("dialog.question"),
                new String[]{getString("button.yes"), getString("button.no"), getString("button.cancel")},
                getString("button.cancel"));
        return Trilean.from012Model(answer);
    }

    public static IdNameColorTriplet showIdNameColorDataDialog(AbstractPrimaryElement editedElement, DataDialogFlag flag) {
        final INCDataDialogPanel dialogPanel = new INCDataDialogPanel(editedElement);

        String title;
        if (flag == BRAND) {
            if (editedElement == null) title = getString("dialog.title.new_brand");
            else title = getString("dialog.title.edit_brand");
        } else {
            if (editedElement == null) title = getString("dialog.title.new_shelf");
            else title = getString("dialog.title.edit_shelf");
        }

        return (JOptionPane.showOptionDialog(Reference.window,
                dialogPanel, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                getIcon("dialog.data"), new Object[]{dialogPanel.okButton, getString("button.cancel")},
                dialogPanel.okButton) == 0) ?
                new IdNameColorTriplet(
                        dialogPanel.idInput.getText(),
                        dialogPanel.nameInput.getText(),
                        dialogPanel.colorInput.getColor())
                : null;
    }

    public static FlatTeaBox showTeaBoxDataDialog(TeaBox editedElement) {
        final TeaBoxDialogPanel dialogPanel = new TeaBoxDialogPanel(editedElement);
        String title;
        if (editedElement == null) title = getString("dialog.title.new_tea_box");
        else title = getString("dialog.title.edit_tea_box");
        return (JOptionPane.showOptionDialog(Reference.window,
                dialogPanel, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                getIcon("dialog.data"), new Object[]{dialogPanel.okButton, getString("button.cancel")},
                dialogPanel.okButton) == 0) ?
                new FlatTeaBox(
                        dialogPanel.idInput.getText(),
                        dialogPanel.nameInput.getText(),
                        DataManager.getCurrentData().getBrands()[dialogPanel.brandInput.getSelectedIndex()].getId(),
                        DataManager.getCurrentData().getShelves()[dialogPanel.shelfInput.getSelectedIndex()].getId(),
                        dialogPanel.descriptionInput.getText().replace("\n", "<br>"),
                        parseDouble(dialogPanel.amountInput.getText()),
                        parseDouble(dialogPanel.starsInput.getText()),
                        parseInt(dialogPanel.brewingPanel.brewingTempInput.getText()),
                        parseInt(dialogPanel.brewingPanel.brewingTimeInput.getText()),
                        !dialogPanel.brewingPanel.brewingReusesInput.getText().equals("") ?
                                (dialogPanel.brewingPanel.brewingReusesInput.getText()
                                        + (dialogPanel.brewingPanel.addMinuteCheckbox.isSelected() ? "/1" : "")) : null,
                        dialogPanel.brewingPanel.brewingGramsInput.getText().replace("\n", "/"))
                : null;
    }
}
