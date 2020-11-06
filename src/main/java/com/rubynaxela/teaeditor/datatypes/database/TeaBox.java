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

package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class TeaBox implements Identifiable {
    private String id, name, brand_id, description;
    private double amount, stars;
    private BrewingInstruction brewing;

    public TeaBox() {
    }

    public TeaBox(String id, String name, String brand_id, String description,
                  double amount, double stars, BrewingInstruction brewing) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.description = description;
        this.amount = amount;
        this.stars = stars;
        this.brewing = brewing;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public BrewingInstruction getBrewing() {
        return brewing;
    }

    public void setBrewing(BrewingInstruction brewing) {
        this.brewing = brewing;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Parameter {
        ID, NAME, BRAND_ID, DESCRIPTION, AMOUNT, STARS, BREW_TEMPERATURE, BREW_TIME, BREW_REUSES, BREW_GRAMS
    }
}
