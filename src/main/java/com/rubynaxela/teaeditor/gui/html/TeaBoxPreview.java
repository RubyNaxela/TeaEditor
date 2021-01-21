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
import com.rubynaxela.teaeditor.datatypes.database.TeaBox;
import com.rubynaxela.teaeditor.managers.DataManager;
import com.rubynaxela.teaeditor.util.DataFormat;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static com.rubynaxela.teaeditor.util.DataFormat.formatNumber;
import static com.rubynaxela.teaeditor.util.DataFormat.parseInt;
import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;
import static com.rubynaxela.teaeditor.util.Utils.findIdInArray;

/**
 * The {@code BrandPreview} class serves as a preview document for teas
 *
 * @author Jacek Pawelski
 * @see TeaBox
 */
public final class TeaBoxPreview extends AbstractPreviewDocument {

    /**
     * @param teaBox the {@link TeaBox} this is a preview of. Empty when given {@code null}
     */
    public TeaBoxPreview(@Nullable TeaBox teaBox) {
        if (teaBox != null) {

            final P title = new P(), desc = new P();
            final Map<String, Object> tableData, brewingData, gramsData;

            title.appendText(teaBox.getName());
            title.setCSSClass("title");

            tableData = new LinkedHashMap<>();
            tableData.put(getString("table.header.id"), teaBox.getId());
            tableData.put(getString("table.header.name"), teaBox.getName());
            tableData.put(getString("table.header.brand"), ((Brand) Objects.requireNonNull(
                    findIdInArray(teaBox.getBrand_id(), DataManager.getCurrentData().getBrands()))).getName());
            tableData.put(getString("table.header.amount"),
                    formatNumber(teaBox.getAmount(), " g", getString("table.none")));
            tableData.put(getString("table.header.stars"), DataFormat.displayStars(teaBox.getStars(), true));

            brewingData = new LinkedHashMap<>();
            if (teaBox.getBrewing() != null) {
                brewingData.put(getString("table.header.temperature"),
                        formatNumber(teaBox.getBrewing().getTemperature(), getString("units.degree")));
                brewingData.put(getString("table.header.time"),
                        formatNumber(teaBox.getBrewing().getTime(), getString("units.minute"), "-"));
                if (teaBox.getBrewing().getReuses() != null)
                    brewingData.put(getString("table.header.reuses"),
                            formatNumber(parseInt(teaBox.getBrewing().getReuses().split("/")[0]),
                                    getString("units.times"))
                                    + (teaBox.getBrewing().getReuses().contains("/1") ? " (+1min)" : ""));
                else brewingData.put(getString("table.header.reuses"), getString("table.one_time"));

                gramsData = new LinkedHashMap<>();
                for (String entry : teaBox.getBrewing().getGrams().split("/"))
                    gramsData.put(entry.split(":")[0] + getString("units.gram"), entry.split(":")[1] + "ml");

                brewingData.put(getString("table.header.proportions"), new MapTable(gramsData));
            }

            tableData.put(getString("table.header.brewing"), new MapTable(brewingData));

            desc.appendText(teaBox.getDescription());
            desc.setCSSClass("desc");

            body.appendChild(title);
            body.appendChild(new Hr());
            body.appendChild(new MapTable(tableData));
            body.appendChild(desc);
        }
    }

}
