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

import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Style;

/**
 * The {@code AbstractPreviewDocument} class is the base class for
 * HTML documents displayed in the preview panel of the main window
 *
 * @author Jacek Pawelski
 */
public abstract class AbstractPreviewDocument extends Html {

    public final Body body;
    public final Head head;

    public AbstractPreviewDocument() {
        this.body = new Body();
        this.head = new Head();
        this.appendChild(head);
        this.appendChild(body);
        final Style style = new Style("text/css");
        style.appendText("body { font-size: 10px }" +
                ".title { font-size: 13px; font-weight: bold; text-align: center; }" +
                "td { padding: 3px; }");
        head.appendChild(style);
    }
}
