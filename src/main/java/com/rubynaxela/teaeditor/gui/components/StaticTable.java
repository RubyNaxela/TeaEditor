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

package com.rubynaxela.teaeditor.gui.components;

import javax.swing.*;

/**
 * The {@code StaticTable} class is an implementation of {@link JTable}, configured
 * in a way that enables only selection of a single row and disables any edits
 *
 * @author Jacek Pawelski
 */
public final class StaticTable extends JTable {

    private final boolean cellsEditable;

    StaticTable(boolean cellsEditable) {
        DefaultListSelectionModel brandsListModel = new DefaultListSelectionModel();
        brandsListModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setSelectionModel(brandsListModel);
        this.getTableHeader().setReorderingAllowed(false);
        this.cellsEditable = cellsEditable;
    }

    public StaticTable() {
        this(false);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return cellsEditable;
    }
}
