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

import java.awt.*;

/**
 * The {@code IdNameColorTriplet} class objects are used as an easy reference to
 * {@link com.rubynaxela.teaeditor.datatypes.database.AbstractPrimaryElement} parameters
 *
 * @author Jacek Pawelski
 * @see com.rubynaxela.teaeditor.handlers.ButtonsHandler#addBrand
 * @see com.rubynaxela.teaeditor.handlers.ButtonsHandler#editBrand
 * @see com.rubynaxela.teaeditor.handlers.ButtonsHandler#addShelf
 * @see com.rubynaxela.teaeditor.handlers.ButtonsHandler#editShelf
 * @see com.rubynaxela.teaeditor.handlers.DialogsHandler#showIdNameColorDataDialog
 */
public final class IdNameColorTriplet {

    public String id, name;
    public Color color;

    public IdNameColorTriplet(String id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
