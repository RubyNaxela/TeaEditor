package com.rubynaxela.teaeditor.datatypes.database;

@SuppressWarnings("unused")
public final class Shelf extends AbstractPrimaryElement {

    private TeaBox[] tea_boxes;

    public Shelf() {
    }

    public Shelf(String id, String name, String color, TeaBox[] tea_boxes) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.tea_boxes = tea_boxes;
    }

    public TeaBox[] getTea_boxes() {
        return tea_boxes;
    }

    public void setTea_boxes(TeaBox[] teaBoxes) {
        this.tea_boxes = teaBoxes;
    }
}
