package com.maxim.denisov.tander;

/**
 * Created by Максим on 06.08.2017.
 */


public class Number {

    private Integer id;
    private Integer coefficient;

    public Number(Integer id, Integer coefficient) {
        this.id = id;
        this.coefficient = coefficient;
    }

    public double getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String name) {
        this.coefficient = coefficient;
    }
}