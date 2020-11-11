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

package com.rubynaxela.teaeditor.gui.dialogs;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.HashMap;

abstract class AbstractValidInputListener implements DocumentListener {

    private static final Border
            defaultBorder = new JTextField().getBorder(),
            errorBorder = BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0x834141), 3, true),
                    BorderFactory.createEmptyBorder(2, 6, 2, 6)),
            errorScrollPaneBorder = BorderFactory.createLineBorder(new Color(0x834141), 3, true);
    private final JButton okButton;
    private final HashMap<JComponent, BalloonTip> errorTooltips;

    protected AbstractValidInputListener(JButton okButton) {
        this.okButton = okButton;
        this.errorTooltips = new HashMap<>();
    }

    protected void displayError(JComponent inputComponent, String message) {
        if (!errorTooltips.containsKey(inputComponent)) {
            final BalloonTip errorTooltip = new BalloonTip(inputComponent,
                    "<html>" + message.replace("\n", "<br>") + "</html>", new RoundedBalloonStyle(
                    5, 5, new Color(0x834141), inputComponent.getForeground()), false);
            errorTooltips.put(inputComponent, errorTooltip);
            if (!(inputComponent instanceof JScrollPane)) inputComponent.setBorder(errorBorder);
            else inputComponent.setBorder(errorScrollPaneBorder);
        }
    }

    protected void cancelError(JComponent... components) {
        for (JComponent component : components)
            if (errorTooltips.containsKey(component)) {
                errorTooltips.get(component).closeBalloon();
                errorTooltips.remove(component);
                component.setBorder(defaultBorder);
            }
    }

    protected abstract boolean dataValid();

    @Override
    public void changedUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        okButton.setEnabled(dataValid());
    }
}
