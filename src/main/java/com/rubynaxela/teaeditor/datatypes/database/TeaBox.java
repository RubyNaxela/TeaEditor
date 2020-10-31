/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

/*=================================================================
 = This file is subject to the terms and conditions defined in    =
 = file 'LICENSE.txt', which is part of this source code package. =
 =================================================================*/

package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class TeaBox implements Identifiable {
    private String id, name, brand_id, description;
    private double amount, stars;
    private BrewingInstruction brewing;

    public TeaBox() {
    }

    public TeaBox(String id, String name, String brand_id, String description,
                  float amount, float stars, BrewingInstruction brewing) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.description = description;
        this.amount = amount;
        this.stars = stars;
        this.brewing = brewing;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public BrewingInstruction getBrewing() {
        return brewing;
    }

    public void setBrewing(BrewingInstruction brewing) {
        this.brewing = brewing;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Parameter {
        ID, NAME, BRAND_ID, DESCRIPTION, AMOUNT, STARS, BREW_TEMPERATURE, BREW_TIME, BREW_REUSES, BREW_GRAMS
    }
}
