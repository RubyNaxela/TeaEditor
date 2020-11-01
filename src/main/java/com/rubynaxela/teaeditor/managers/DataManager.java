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

import com.rubynaxela.teaeditor.datatypes.ActionResult;
import com.rubynaxela.teaeditor.datatypes.ActionResult.ResultType;
import com.rubynaxela.teaeditor.datatypes.database.*;
import com.rubynaxela.teaeditor.handlers.FileIOHandler;
import com.rubynaxela.teaeditor.util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.colorToHex;
import static com.rubynaxela.teaeditor.util.Utils.formatNumber;

@SuppressWarnings("UnusedReturnValue")
public final class DataManager {

    public static boolean dataChanged = false;

    private static TeaData currentData = null;

    public static TeaData getCurrentData() {
        return currentData;
    }

    public static void setCurrentData(TeaData data) {
        currentData = data;
    }

    ///////////////////////
    // Brands management //
    ///////////////////////

    public static ActionResult defineBrand(String id, String name, Color color) {
        if ((Utils.findIdInArray(id, currentData.getBrands())) == null) {
            Brand brand = new Brand(id, name, colorToHex(color));
            List<Brand> brandsList = new ArrayList<>(Arrays.asList(getCurrentData().getBrands()));
            brandsList.add(brand);
            currentData.setBrands(brandsList.toArray(new Brand[0]));
            dataChanged = true;
            return new ActionResult(ResultType.SUCCESS);
        }
        return new ActionResult(ResultType.ERROR, getString("action.result.brand.already_exists"));
    }

    public static ActionResult removeBrand(String id) {
        List<Brand> brandsList = new ArrayList<>(Arrays.asList(getCurrentData().getBrands()));
        for (int i = 0; i < brandsList.size(); i++)
            if (brandsList.get(i).getId().equals(id)) {
                brandsList.remove(i);
                currentData.setBrands(brandsList.toArray(new Brand[0]));
                dataChanged = true;
                return new ActionResult(ResultType.SUCCESS);
            }
        return new ActionResult(ResultType.ERROR, getString("action.result.brand.invalid_id"));
    }

    public static ActionResult editBrandParameter(Brand.Parameter parameter, String id, Object newValue) {
        Brand brand = ((Brand) Utils.findIdInArray(id, currentData.getBrands()));
        if (brand != null) {
            switch (parameter) {
                case ID:
                    if (id.equals(newValue))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.id"));
                    else {
                        brand.setId((String) newValue);
                        for (Shelf shelf : currentData.getShelves())
                            for (TeaBox teaBox : shelf.getTea_boxes())
                                if (teaBox.getBrand_id().equals(id))
                                    teaBox.setBrand_id((String) newValue);
                    }
                    break;
                case NAME:
                    if (brand.getName().equals(newValue))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.name"));
                    else
                        brand.setName((String) newValue);
                    break;
                case COLOR:
                    if (brand.getColor().equals(colorToHex((Color) newValue)))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.color"));
                    else
                        brand.setColor(colorToHex((Color) newValue));
                    break;
            }
        } else return new ActionResult(ResultType.ERROR, getString("action.result.brand.invalid_id"));

        dataChanged = true;
        return new ActionResult(ResultType.SUCCESS);
    }

    public static Vector<Vector<String>> getBrandsDataVector() {
        if (FileIOHandler.currentFile == null)
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

    ////////////////////////
    // Shelves management //
    ////////////////////////

    public static ActionResult defineShelf(String id, String name, Color color) {
        if ((Utils.findIdInArray(id, currentData.getShelves())) == null) {
            Shelf shelf = new Shelf(id, name, colorToHex(color), new TeaBox[0]);
            List<Shelf> shelvesList = new ArrayList<>(Arrays.asList(getCurrentData().getShelves()));
            shelvesList.add(shelf);
            currentData.setShelves(shelvesList.toArray(new Shelf[0]));
            dataChanged = true;
            return new ActionResult(ResultType.SUCCESS);
        }
        return new ActionResult(ResultType.ERROR, getString("action.result.shelf.already_exists"));
    }

    public static ActionResult removeShelf(String id) {
        List<Shelf> shelvesList = new ArrayList<>(Arrays.asList(getCurrentData().getShelves()));
        for (int i = 0; i < shelvesList.size(); i++)
            if (shelvesList.get(i).getId().equals(id)) {
                shelvesList.remove(i);
                currentData.setShelves(shelvesList.toArray(new Shelf[0]));
                dataChanged = true;
                return new ActionResult(ResultType.SUCCESS);
            }
        return new ActionResult(ResultType.ERROR, getString("action.result.shelf.invalid_id"));
    }

    public static ActionResult editShelfParameter(Shelf.Parameter parameter, String id, Object newValue) {
        Shelf shelf = ((Shelf) Utils.findIdInArray(id, currentData.getShelves()));
        if (shelf != null) {
            switch (parameter) {
                case ID:
                    if (id.equals(newValue))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.id"));
                    else
                        shelf.setId((String) newValue);
                    break;
                case NAME:
                    if (shelf.getName().equals(newValue))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.name"));
                    else
                        shelf.setName((String) newValue);
                    break;
                case COLOR:
                    if (shelf.getColor().equals(colorToHex((Color) newValue)))
                        return new ActionResult(ResultType.NOTHING_CHANGED, getString("action.result.same.color"));
                    else
                        shelf.setColor(colorToHex((Color) newValue));
                    break;
            }
        } else return new ActionResult(ResultType.ERROR, getString("action.result.shelf.invalid_id"));

        dataChanged = true;
        return new ActionResult(ResultType.SUCCESS);
    }

    public static Vector<Vector<String>> getShelvessDataVector() {
        if (FileIOHandler.currentFile == null)
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

    //////////////////////////
    // Tea boxes management //
    //////////////////////////

    public static ActionResult defineTeaBox(String shelfId, String id, String name, String brand_id, String description,
                                            float amount, float stars, int temperature, int time, String reuses, String grams) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId))
                if ((Utils.findIdInArray(id, shelf.getTea_boxes())) == null) {
                    TeaBox teaBox = new TeaBox(id, name, brand_id, description, amount, stars,
                            new BrewingInstruction(temperature, time, reuses, grams));
                    List<TeaBox> teaBoxesList = new ArrayList<>(Arrays.asList(shelf.getTea_boxes()));
                    teaBoxesList.add(teaBox);
                    shelf.setTea_boxes(teaBoxesList.toArray(new TeaBox[0]));
                    dataChanged = true;
                    return new ActionResult(ResultType.SUCCESS);
                } else {
                    return new ActionResult(ResultType.ERROR, getString("action.result.tea_box.invalid_id"));
                }
        return new ActionResult(ResultType.ERROR, getString("action.result.shelf.invalid_id"));
    }

    public static ActionResult removeTeaBox(String shelfId, String id) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId)) {
                List<TeaBox> teaBoxesList = new ArrayList<>(Arrays.asList(shelf.getTea_boxes()));
                for (int i = 0; i < teaBoxesList.size(); i++)
                    if (teaBoxesList.get(i).getId().equals(id)) {
                        teaBoxesList.remove(i);
                        shelf.setTea_boxes(teaBoxesList.toArray(new TeaBox[0]));
                        dataChanged = true;
                        return new ActionResult(ResultType.SUCCESS);
                    }
                return new ActionResult(ResultType.ERROR, getString("action.result.tea_box.invalid_id"));
            }
        return new ActionResult(ResultType.ERROR, getString("action.result.shelf.invalid_id"));
    }

    public static ActionResult editTeaBoxParameter(TeaBox.Parameter parameter, String shelfId, String id, Object newValue) {
        for (Shelf shelf : currentData.getShelves())
            if (shelf.getId().equals(shelfId)) {
                TeaBox teaBox = ((TeaBox) Utils.findIdInArray(id, shelf.getTea_boxes()));
                if (teaBox != null) {
                    BrewingInstruction brewing = teaBox.getBrewing();
                    switch (parameter) {
                        case ID:
                            if (id.equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.id"));
                            else
                                teaBox.setId((String) newValue);
                            break;
                        case NAME:
                            if (teaBox.getName().equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.name"));
                            else
                                teaBox.setName((String) newValue);
                            break;
                        case BRAND_ID:
                            if (teaBox.getBrand_id().equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.brand_id"));
                            else
                                teaBox.setBrand_id((String) newValue);
                            break;
                        case DESCRIPTION:
                            if (teaBox.getDescription().equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.description"));
                            else
                                teaBox.setDescription((String) newValue);
                            break;
                        case AMOUNT:
                            if (teaBox.getAmount() == (double) newValue)
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.amount"));
                            else
                                teaBox.setAmount((double) newValue);
                            break;
                        case STARS:
                            if (teaBox.getStars() == (double) newValue)
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.stars"));
                            else
                                teaBox.setStars((double) newValue);
                            break;
                        case BREW_TEMPERATURE:
                            if (brewing.getTemperature() == (int) newValue)
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.brew_temperature"));
                            else
                                brewing.setTemperature((int) newValue);
                            break;
                        case BREW_TIME:
                            if (brewing.getTime() == (int) newValue)
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.brew_time"));
                            else
                                brewing.setTime((int) newValue);
                            break;
                        case BREW_REUSES:
                            if (brewing.getReuses().equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.brew_reuses"));
                            else
                                brewing.setReuses((String) newValue);
                            break;
                        case BREW_GRAMS:
                            if (brewing.getGrams().equals(newValue))
                                return new ActionResult(ResultType.NOTHING_CHANGED,
                                        getString("action.result.same.brew_grams"));
                            else
                                brewing.setGrams((String) newValue);
                            break;
                    }
                } else return new ActionResult(ResultType.ERROR, getString("action.result.tea_box.invalid_id"));

                dataChanged = true;
                return new ActionResult(ResultType.SUCCESS);
            }
        return new ActionResult(ResultType.ERROR, getString("action.result.shelf.invalid_id"));
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
            teaBoxData.add(formatNumber(teaBox.getStars(), "/5", getString("table.none")));
            data.add(teaBoxData);
        }
        return data;
    }
}
