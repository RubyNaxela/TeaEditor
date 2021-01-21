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
import com.rubynaxela.teaeditor.datatypes.database.Brand;
import com.rubynaxela.teaeditor.datatypes.database.Shelf;
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.managers.DataManager;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * The {@code BrandPreview} class serves as a preview document for tea brands
 *
 * @author Jacek Pawelski
 * @see Brand
 */
public final class BrandPreview extends AbstractPreviewDocument {

    /**
     * @param brand the {@link Brand} this is a preview of. Empty when given {@code null}
     */
    public BrandPreview(@Nullable Brand brand) {
        if (brand != null) {

            final P title = new P();

            title.appendText(brand.getName());
            title.setCSSClass("title");

            Map<String, Object> tableData = new LinkedHashMap<>();
            tableData.put(getString("table.header.id"), brand.getId());
            tableData.put(getString("table.header.name"), brand.getName());
            tableData.put(getString("table.header.color"), Color.decode(brand.getColor()));
            tableData.put(getString("table.header.teas_number"), countTeas(brand));

            body.appendChild(title);
            body.appendChild(new Hr());
            body.appendChild(new MapTable(tableData));
        }
    }

    private int countTeas(Brand brand) {
        int counter = 0;
        for (Shelf shelf : DataManager.getCurrentData().getShelves())
            for (TeaBox teaBox : shelf.getTea_boxes())
                if (teaBox.getBrand_id().equals(brand.getId())) counter++;
        return counter;
    }
}
