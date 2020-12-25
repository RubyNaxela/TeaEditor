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

/**
 * The {@code Shelf} class is used to store shelves from database. Extends {@link AbstractPrimaryElement} class because it
 * has an ID, a name and a color
 *
 * @author Jacek Pawelski
 */
@SuppressWarnings("unused")
public final class Shelf extends AbstractPrimaryElement {

    private TeaBox[] tea_boxes;

    public Shelf() {
    }

    public Shelf(String id, String name, String color, TeaBox[] tea_boxes) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.tea_boxes = tea_boxes;
    }

    public TeaBox[] getTea_boxes() {
        return tea_boxes;
    }

    public void setTea_boxes(TeaBox[] teaBoxes) {
        this.tea_boxes = teaBoxes;
    }
}
