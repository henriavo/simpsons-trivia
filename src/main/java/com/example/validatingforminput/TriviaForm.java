package com.example.validatingforminput;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TriviaForm {

    @NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Pattern(regexp = "apu")
    private String store;

    @NotNull
    @Pattern(regexp = "canyon arrow")
    private String car;

    @NotNull
    @Pattern(regexp = "gorrilla chest")
    private String vest;

    @NotNull
    @Pattern(regexp = "blowfish")
    private String food;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getVest() {
        return vest;
    }

    public void setVest(String vest) {
        this.vest = vest;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String toString() {
        return "Person(Name: " + this.name;
    }

}