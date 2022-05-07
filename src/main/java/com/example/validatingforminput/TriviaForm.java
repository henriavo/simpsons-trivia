package com.example.validatingforminput;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class TriviaForm {

    private static String CORRECT = "correct";
    private static String WRONG = "wrong";
    private static String EMPTY = "empty";

    private String name;

    private String store;

    private String car;

    private String vest;

    private String food;

    public String isStoreCorrect() {
        if (store == null) {
            return EMPTY;
        }
        else if (store.equalsIgnoreCase("apu") ||
                    store.equalsIgnoreCase("Apu Nahasapeemapetilon") ||
                    store.equalsIgnoreCase("Nahasapeemapetilon") ||
                    store.equalsIgnoreCase("Apu Nahasapasa")){
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isCarCorrect() {
        if (car == null) {
            return EMPTY;
        }
        else if (car.equalsIgnoreCase("canyon arrow") ||
                car.equalsIgnoreCase("Canyonero" )) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isVestCorrect() {
        if (vest == null) {
            return EMPTY;
        }
        else if (vest.equalsIgnoreCase("gorrilla chest") ||
                vest.equalsIgnoreCase("gorrilla") ||
                vest.equalsIgnoreCase("real gorilla chest")) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isFoodCorrect() {
        if (food == null) {
            return EMPTY;
        }
        else if (food.equalsIgnoreCase("blowfish") ||
                food.equalsIgnoreCase("fugu")) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public boolean allCorrect(){
        if (Objects.equals(this.isStoreCorrect(), CORRECT) &&
                Objects.equals(this.isCarCorrect(), CORRECT) &&
                Objects.equals(this.isVestCorrect(), CORRECT) &&
                Objects.equals(this.isFoodCorrect(), CORRECT)) {
            return true;
        }
        else
            return false;
    }

    public String toString() {
        return "Person(Name: " + this.name +
                " store: " + this.getStore() +
                " car: " + this.getCar() +
                " vest: " + this.getVest() +
                " food: " + this.getFood();
    }

}