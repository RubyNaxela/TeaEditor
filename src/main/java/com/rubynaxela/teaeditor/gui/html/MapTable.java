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

import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Tbody;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;

import java.awt.*;
import java.util.Map;

import static com.rubynaxela.teaeditor.util.Utils.colorToHex;

public final class MapTable extends Table {

    public MapTable(Map<String, Object> data) {

        Tbody tableBody = new Tbody();

        for (String key : data.keySet()) {

            Tr row = new Tr();
            Td keyCell = new Td(), valueCell = new Td();

            keyCell.appendText(key);
            keyCell.setStyle("font-weight:bold;");

            Object value = data.get(key);
            if (value instanceof Color)
                valueCell.setStyle("background-color:" + colorToHex(((Color) value)) + ";");
            else if (value instanceof Node) valueCell.appendChild((Node) value);
            else valueCell.appendText("" + value);
            if (value instanceof Integer || value instanceof Double || value instanceof Float)
                valueCell.setStyle("text-align:right;");

            row.appendChild(keyCell);
            row.appendChild(valueCell);
            tableBody.appendChild(row);
        }

        this.appendChild(tableBody);
    }
}
