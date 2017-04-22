package com.fms.fmsindia.adapter;

public class Country {

    private String code = "";
    private String name = "";
    private String name1 = "";
    private int population = 0;

    public Country(String code, String name,String name1, int population) {
        super();
        this.code = code;
        this.name = name;
        this.name1 =name1;
        this.population = population;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName1() {
        return name1;
    }
    public void setName1(String name1) {
        this.name1 = name1;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

}
