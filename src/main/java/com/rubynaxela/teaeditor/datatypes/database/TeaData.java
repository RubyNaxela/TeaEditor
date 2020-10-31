/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class TeaData {
    private Brand[] brands;
    private Shelf[] shelves;

    public Brand[] getBrands() {
        return brands;
    }

    public void setBrands(Brand[] brands) {
        this.brands = brands;
    }

    public Shelf[] getShelves() {
        return shelves;
    }

    public void setShelves(Shelf[] shelves) {
        this.shelves = shelves;
    }
}
