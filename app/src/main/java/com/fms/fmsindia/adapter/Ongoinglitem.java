package com.fms.fmsindia.adapter;

/**
 * Created by androiduser2 on 4/9/15.
 */
public class Ongoinglitem {
    private int drawableId;
    private String model;
    private String color;
    private String model1;
    private String color1;
    private String color2;
    private String Med1;
    private String Med2;
    private String Med3;
    private double price;

    public Ongoinglitem(String model, String color, String model1, String color1, String color2, double price, String Med1, String Med2, String Med3) {
        super();
        this.drawableId = drawableId;
        this.model = model;
        this.color = color;
        this.model1 = model1;
        this.color1 = color1;
        this.color2 = color2;
        this.price = price;
        this.Med1 = Med1;
        this.Med2 = Med2;
        this.Med3 = Med3;
    }

    public void setModel2(String Med1) {
        this.Med1 = Med1;
    }

    public void setModel12(String Med2) {
        this.Med2 = Med2;
    }

    public void setModel13(String Med3) {
        this.Med3 = Med3;
    }

    public void setModel1(String model1) {
        this.model1 = model1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }
    public void setColor2(String color2) {
        this.color2 = color2;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }
    public String getMode2() {
        return Med1;
    }
    public String getMode12() {
        return Med2;
    }
    public String getMode13() {
        return Med3;
    }
    public String getColor() {
        return color;
    }

    public String getModel1() {
        return model1;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }


    public double getPrice() {
        return price;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
