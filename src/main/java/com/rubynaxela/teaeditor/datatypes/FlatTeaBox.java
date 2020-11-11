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

package com.rubynaxela.teaeditor.datatypes;

public class FlatTeaBox {

    public String id, name, brandId, shelfId, description, reuses, grams;
    public double amount, stars;
    public int temperature, time;

    public FlatTeaBox(String id, String name, String brandId, String shelfId, String description, double amount,
                      double stars, int temperature, int time, String reuses, String grams) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.shelfId = shelfId;
        this.description = description;
        this.amount = amount;
        this.stars = stars;
        this.temperature = temperature;
        this.time = time;
        this.reuses = reuses;
        this.grams = grams;
    }
}
