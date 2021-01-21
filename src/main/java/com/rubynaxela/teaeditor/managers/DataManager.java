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

package com.rubynaxela.teaeditor.managers;

import com.rubynaxela.teaeditor.datatypes.database.*;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.util.Utils;

import java.awt.*;
import java.util.List;
import java.util.*;

import static com.rubynaxela.teaeditor.util.DataFormat.displayStars;
import static com.rubynaxela.teaeditor.util.DataFormat.formatNumber;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.colorToHex;

/**
 * The {@code DataManager} class contains functions that control the contents of the database
 *
 * @author Jacek Pawelski
 */
public final class DataManager {

    public static boolean dataChanged = false;
    public static boolean newFileCreated = false;

    private static TeaData currentData = null;

    public static TeaData getCurrentData() {
        return currentData;
    }

    public static void setCurrentData(TeaData data) {
        currentData = data;
    }

    public static TeaData createEmptyDatabase() {
        TeaData database = new TeaData();
        database.setBrands(new Brand[0]);
        database.setShelves(new Shelf[0]);
        return database;
    }

    ///////////////////////
    // Brands management //
    ///////////////////////

    public static void defineBrand(String id, String name, Color color) {
        if ((Utils.findIdInArray(id, currentData.getBrands())) == null) {
            Brand brand = new Brand(id, name, colorToHex(color));
            List<Brand> brandsList = new ArrayList<>(Arrays.asList(getCurrentData().getBrands()));
            brandsList.add(brand);
            currentData.setBrands(brandsList.toArray(new Brand[0]));
            dataChanged = true;
        }
    }

    public static void removeBrand(String id) {
        List<Brand> brandsList = new ArrayList<>(Arrays.asList(getCurrentData().getBrands()));
        for (int i = 0; i < brandsList.size(); i++)
            if (brandsList.get(i).getId().equals(id)) {
                brandsList.remove(i);
                currentData.setBrands(brandsList.toArray(new Brand[0]));
                dataChanged = true;
                return;
            }
    }

    public static void editBrandParameter(Brand.Parameter parameter, String id, Object newValue) {
        Brand brand = ((Brand) Utils.findIdInArray(id, currentData.getBrands()));
        if (brand != null) {
            switch (parameter) {
                case ID:
                    if (!id.equals(newValue)) {
                        brand.setId((String) newValue);
                        for (Shelf shelf : currentData.getShelves())
                            for (TeaBox teaBox : shelf.getTea_boxes())
                                if (teaBox.getBrand_id().equals(id))
                                    teaBox.setBrand_id((String) newValue);
                    } else return;
                    break;
                case NAME:
                    if (!brand.getName().equals(newValue)) brand.setName((String) newValue);
                    else return;
                    break;
                case COLOR:
                    if (!brand.getColor().equals(colorToHex((Color) newValue))) brand.setColor(colorToHex((Color) newValue));
                    else return;
                    break;
            }
            dataChanged = true;
        }
    }

    public static Vector<Vector<String>> getBrandsDataVector() {
        if (FileIOHandler.currentFile == null && !DataManager.newFileCreated)
            return null;
        Vector<Vector<String>> data = new Vector<>();
        for (Brand brand : currentData.getBrands()) {
            Vector<String> brandData = new Vector<>();
            brandData.add(brand.getId());
            brandData.add(brand.getName());
            brandData.add(brand.getColor());
            data.add(brandData);
        }
        return data;
    }

    public static Vector<String> getBrandsNamesVector() {
        if (FileIOHandler.currentFile == null && !DataManager.newFileCreated)
            return null;
        Vector<String> names = new Vector<>();
        for (Brand brand : currentData.getBrands()) {
            names.add(brand.getName());
        }
        return names;
    }

    ////////////////////////
    // Shelves management //
    ////////////////////////

    public static void defineShelf(String id, String name, Color color) {
        if ((Utils.findIdInArray(id, currentData.getShelves())) == null) {
            Shelf shelf = new Shelf(id, name, colorToHex(color), new TeaBox[0]);
            List<Shelf> shelvesList = new ArrayList<>(Arrays.asList(getCurrentData().getShelves()));
            shelvesList.add(shelf);
            currentData.setShelves(shelvesList.toArray(new Shelf[0]));
            dataChanged = true;
        }
    }

    public static void removeShelf(String id) {
        List<Shelf> shelvesList = new ArrayList<>(Arrays.asList(getCurrentData().getShelves()));
        for (int i = 0; i < shelvesList.size(); i++)
            if (shelvesList.get(i).getId().equals(id)) {
                shelvesList.remove(i);
                currentData.setShelves(shelvesList.toArray(new Shelf[0]));
                dataChanged = true;
                return;
            }
    }

    public static void editShelfParameter(Shelf.Parameter parameter, String id, Object newValue) {
        Shelf shelf = ((Shelf) Utils.findIdInArray(id, currentData.getShelves()));
        if (shelf != null) {
            switch (parameter) {
                case ID:
                    if (!id.equals(newValue)) shelf.setId((String) newValue);
                    else return;
                    break;
                case NAME:
                    if (!shelf.getName().equals(newValue)) shelf.setName((String) newValue);
                    else return;
                    break;
                case COLOR:
                    if (!shelf.getColor().equals(colorToHex((Color) newValue))) shelf.setColor(colorToHex((Color) newValue));
                    else return;
                    break;
            }
            dataChanged = true;
        }
    }

    public static Vector<Vector<String>> getShelvesDataVector() {
        if (FileIOHandler.currentFile == null && !DataManager.newFileCreated)
            return null;
        Vector<Vector<String>> data = new Vector<>();
        for (Shelf shelf : currentData.getShelves()) {
            Vector<String> shelfData = new Vector<>();
            shelfData.add(shelf.getId());
            shelfData.add(shelf.getName());
            shelfData.add(shelf.getColor());
            data.add(shelfData);
        }
        return data;
    }

    public static Vector<String> getShelvesNamesVector() {
        if (FileIOHandler.currentFile == null && !DataManager.newFileCreated)
            return null;
        Vector<String> names = new Vector<>();
        for (Shelf shelf : currentData.getShelves()) {
            names.add(shelf.getName());
        }
        return names;
    }

    //////////////////////////
    // Tea boxes management //
    //////////////////////////

    @SuppressWarnings("unchecked")
    public static void defineTeaBox(String shelfId, String id, String name, String brand_id, String description, double amount,
                                    double stars, int temperature, int time, String reuses, String grams) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId))
                if ((Utils.findIdInArray(id, shelf.getTea_boxes())) == null) {
                    TeaBox teaBox = new TeaBox(id, name, brand_id, description, amount, stars,
                            new BrewingInstruction(temperature, time, reuses, grams));
                    List<TeaBox> teaBoxesList = new ArrayList<>(Arrays.asList(shelf.getTea_boxes()));
                    teaBoxesList.add(teaBox);
                    Collections.sort(teaBoxesList);
                    shelf.setTea_boxes(teaBoxesList.toArray(new TeaBox[0]));
                    dataChanged = true;
                    return;
                } else return;
    }

    public static void removeTeaBox(String shelfId, String id) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId)) {
                List<TeaBox> teaBoxesList = new ArrayList<>(Arrays.asList(shelf.getTea_boxes()));
                for (int i = 0; i < teaBoxesList.size(); i++)
                    if (teaBoxesList.get(i).getId().equals(id)) {
                        teaBoxesList.remove(i);
                        shelf.setTea_boxes(teaBoxesList.toArray(new TeaBox[0]));
                        dataChanged = true;
                        return;
                    }
                return;
            }
    }

    public static void editTeaBoxParameter(TeaBox.Parameter parameter, String shelfId, String id, Object newValue) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId)) {
                TeaBox teaBox = ((TeaBox) Utils.findIdInArray(id, shelf.getTea_boxes()));
                if (teaBox != null) {
                    BrewingInstruction brewing = teaBox.getBrewing();
                    switch (parameter) {
                        case ID:
                            if (!id.equals(newValue)) teaBox.setId((String) newValue);
                            else return;
                            break;
                        case NAME:
                            if (!teaBox.getName().equals(newValue)) teaBox.setName((String) newValue);
                            else return;
                            break;
                        case BRAND_ID:
                            if (!teaBox.getBrand_id().equals(newValue)) teaBox.setBrand_id((String) newValue);
                            else return;
                            break;
                        case DESCRIPTION:
                            if (!teaBox.getDescription().equals(newValue)) teaBox.setDescription((String) newValue);
                            else return;
                            break;
                        case AMOUNT:
                            if (teaBox.getAmount() != (double) newValue) teaBox.setAmount((double) newValue);
                            else return;
                            break;
                        case STARS:
                            if (teaBox.getStars() != (double) newValue) teaBox.setStars((double) newValue);
                            else return;
                            break;
                        case BREW_TEMPERATURE:
                            if (brewing.getTemperature() != (int) newValue) brewing.setTemperature((int) newValue);
                            else return;
                            break;
                        case BREW_TIME:
                            if (brewing.getTime() != (int) newValue) brewing.setTime((int) newValue);
                            else return;
                            break;
                        case BREW_REUSES:
                            if (brewing.getReuses() != newValue) brewing.setReuses((String) newValue);
                            else return;
                            break;
                        case BREW_GRAMS:
                            if (!brewing.getGrams().equals(newValue)) brewing.setGrams((String) newValue);
                            else return;
                            break;
                    }
                } else return;

                dataChanged = true;
                return;
            }
    }

    public static Vector<Vector<String>> getTeaBoxesDataVector(Shelf shelf) {
        if (shelf == null)
            return null;
        Vector<Vector<String>> data = new Vector<>();
        for (TeaBox teaBox : shelf.getTea_boxes()) {
            Vector<String> teaBoxData = new Vector<>();
            teaBoxData.add(teaBox.getId());
            teaBoxData.add(teaBox.getName());
            teaBoxData.add(teaBox.getBrand_id());
            teaBoxData.add(teaBox.getDescription());
            teaBoxData.add(formatNumber(teaBox.getAmount(), " g", getString("table.none")));
            teaBoxData.add(displayStars(teaBox.getStars(), false));
            data.add(teaBoxData);
        }
        return data;
    }

    public static Shelf getTeaBoxShelf(TeaBox box) {
        for (Shelf shelf : currentData.getShelves())
            for (TeaBox teaBox : shelf.getTea_boxes())
                if (teaBox.equals(box)) return shelf;
        return null;
    }

    public static void setTeaBoxShelf(TeaBox box, Shelf newShelf) {
        for (Shelf shelf : currentData.getShelves())
            for (TeaBox teaBox : shelf.getTea_boxes())
                if (teaBox.equals(box) && !shelf.getId().equals(newShelf.getId())) {
                    removeTeaBox(shelf.getId(), box.getId());
                    defineTeaBox(newShelf.getId(), box.getId(), box.getName(), box.getBrand_id(), box.getDescription(),
                            box.getAmount(), box.getStars(), box.getBrewing().getTemperature(), box.getBrewing().getTime(),
                            box.getBrewing().getReuses(), box.getBrewing().getGrams());
                }
    }
}
