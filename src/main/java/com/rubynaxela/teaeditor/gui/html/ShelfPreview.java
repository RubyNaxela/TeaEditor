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

package com.rubynaxela.teaeditor.gui.html;

import com.hp.gagawa.java.elements.Hr;
import com.hp.gagawa.java.elements.P;
import com.rubynaxela.teaeditor.datatypes.database.Shelf;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

public final class ShelfPreview extends AbstractPreviewDocument {

    public ShelfPreview(@Nullable Shelf shelf) {
        if (shelf != null) {

            final P title = new P();

            title.appendText(shelf.getName());
            title.setCSSClass("title");

            Map<String, Object> tableData = new LinkedHashMap<>();
            tableData.put(getString("table.header.id"), shelf.getId());
            tableData.put(getString("table.header.name"), shelf.getName());
            tableData.put(getString("table.header.color"), Color.decode(shelf.getColor()));
            tableData.put(getString("table.header.teas_number"), shelf.getTea_boxes().length);

            body.appendChild(title);
            body.appendChild(new Hr());
            body.appendChild(new MapTable(tableData));
        }
    }
}
