package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class Brand extends AbstractPrimaryElement {

    public Brand() {
    }

    public Brand(String id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
