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
import com.rubynaxela.teaeditor.datatypes.database.Brand;
import com.rubynaxela.teaeditor.datatypes.database.Shelf;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.gui.dialogs.Dialogs;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.managers.ListsManager;
import com.rubynaxela.teaeditor.managers.WindowUpdatesManager;

import java.awt.event.ActionListener;
import java.util.Objects;

import static com.rubynaxela.teaeditor.util.Reference.DataDialogFlag.*;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class ButtonsHandler {

    public static ActionListener addBrand = e -> {
        IdNameColorTriplet brandData = Dialogs.showIdNameColorDataDialog(null, NEW, BRAND);
        if (brandData != null) {
            DataManager.defineBrand(brandData.id, brandData.name, brandData.color);
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener removeBrand = e -> {
        if (Dialogs.askYesNoQuestion(getString("dialog.message.are_you_sure"), false)) {
            DataManager.removeBrand(Objects.requireNonNull(ListsManager.getSelectedBrand()).getId());
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener editBrand = e -> {
        Brand editedBrand = Objects.requireNonNull(ListsManager.getSelectedBrand());
        IdNameColorTriplet brandData = Dialogs.showIdNameColorDataDialog(editedBrand, EDIT, BRAND);
        if (brandData != null) {
            DataManager.editBrandParameter(Brand.Parameter.ID, editedBrand.getId(), brandData.id);
            DataManager.editBrandParameter(Brand.Parameter.NAME, editedBrand.getId(), brandData.name);
            DataManager.editBrandParameter(Brand.Parameter.COLOR, editedBrand.getId(), brandData.color);
            WindowUpdatesManager.updateLists();
        }
    };

    public static ActionListener addShelf = e -> {
        IdNameColorTriplet shelfData = Dialogs.showIdNameColorDataDialog(null, NEW, SHELF);
        if (shelfData != null) {
            DataManager.defineShelf(shelfData.id, shelfData.name, shelfData.color);
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener removeShelf = e -> {
        if (Dialogs.askYesNoQuestion(getString("dialog.message.are_you_sure"), false)) {
            DataManager.removeShelf(Objects.requireNonNull(ListsManager.getSelectedShelf()).getId());
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener editShelf = e -> {
        Shelf editedShelf = Objects.requireNonNull(ListsManager.getSelectedShelf());
        IdNameColorTriplet shelfData = Dialogs.showIdNameColorDataDialog(editedShelf, EDIT, SHELF);
        if (shelfData != null) {
            DataManager.editShelfParameter(Shelf.Parameter.ID, editedShelf.getId(), shelfData.id);
            DataManager.editShelfParameter(Shelf.Parameter.NAME, editedShelf.getId(), shelfData.name);
            DataManager.editShelfParameter(Shelf.Parameter.COLOR, editedShelf.getId(), shelfData.color);
            WindowUpdatesManager.updateLists();
        }
    };

    public static ActionListener addTeaBox = e -> {
        FlatTeaBox teaBoxData = Dialogs.showTeaBoxDataDialog(null, NEW, TEABOX);
        if (teaBoxData != null) {
            DataManager.defineTeaBox(Objects.requireNonNull(ListsManager.getSelectedShelf()).getId(), teaBoxData.id,
                    teaBoxData.name, teaBoxData.brandId, teaBoxData.description, teaBoxData.amount, teaBoxData.stars,
                    teaBoxData.temperature, teaBoxData.time, teaBoxData.reuses, teaBoxData.grams);
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener removeTeaBox = e -> {
        if (Dialogs.askYesNoQuestion(getString("dialog.message.are_you_sure"), false)) {
            DataManager.removeTeaBox(Objects.requireNonNull(ListsManager.getSelectedShelf()).getId(),
                    Objects.requireNonNull(ListsManager.getSelectedTeaBox()).getId());
            WindowUpdatesManager.updateLists();
        }
    };
    public static ActionListener editTeaBox = e -> {
        TeaBox editedTeaBox = Objects.requireNonNull(ListsManager.getSelectedTeaBox());
        String shelfId = Objects.requireNonNull(ListsManager.getSelectedShelf()).getId(),
                teaBoxId = editedTeaBox.getId();
        FlatTeaBox teaBoxData = Dialogs.showTeaBoxDataDialog(editedTeaBox, EDIT, TEABOX);
        if (teaBoxData != null) {
            DataManager.editTeaBoxParameter(TeaBox.Parameter.ID, shelfId, teaBoxId, teaBoxData.id);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.NAME, shelfId, teaBoxId, teaBoxData.name);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.BRAND_ID, shelfId, teaBoxId, teaBoxData.brandId);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.DESCRIPTION, shelfId, teaBoxId, teaBoxData.description);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.AMOUNT, shelfId, teaBoxId, teaBoxData.amount);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.STARS, shelfId, teaBoxId, teaBoxData.stars);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.BREW_TEMPERATURE, shelfId, teaBoxId, teaBoxData.temperature);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.BREW_TIME, shelfId, teaBoxId, teaBoxData.time);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.BREW_REUSES, shelfId, teaBoxId, teaBoxData.reuses);
            DataManager.editTeaBoxParameter(TeaBox.Parameter.BREW_GRAMS, shelfId, teaBoxId, teaBoxData.grams);
            WindowUpdatesManager.updateLists();
        }
    };
}
