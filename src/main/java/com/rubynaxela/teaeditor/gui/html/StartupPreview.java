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

import static com.rubynaxela.teaeditor.util.Reference.Resources.getString;

/**
 * The {@code BrandPreview} class serves as a welcome message displayed in the preview panel of the main window
 *
 * @author Jacek Pawelski
 * @see Shelf
 */
public final class StartupPreview extends AbstractPreviewDocument {

    public StartupPreview() {

        final P greeting = new P(), instruction = new P();

        greeting.appendText(getString("preview.welcome_message"));
        greeting.setCSSClass("title");
        instruction.appendText(getString("preview.start_instruction"));

        body.appendChild(greeting);
        body.appendChild(new Hr());
        body.appendChild(instruction);
    }
}
