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

    private String computer;

    private String gun;

    private String justmoe;

    private String zeppelin;

    public String isComputerCorrect() {
        if (computer == null) {
            return EMPTY;
        }
        else if (computer.equalsIgnoreCase("computer")){
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isGunCorrect() {
        if (gun == null) {
            return EMPTY;
        }
        else if (gun.equalsIgnoreCase("gun") ||
                gun.equalsIgnoreCase("a gun" )) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isMurderCorrect() {
        if (justmoe == null) {
            return EMPTY;
        }
        else if (justmoe.equalsIgnoreCase("just moe")) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public String isZeppelinCorrect() {
        if (zeppelin == null) {
            return EMPTY;
        }
        else if (zeppelin.equalsIgnoreCase("zeppelin rules!") ||
                zeppelin.equalsIgnoreCase("zeppelin rules")) {
            return CORRECT;
        }
        else
            return WRONG;
    }

    public boolean allCorrect(){
        if (Objects.equals(this.isComputerCorrect(), CORRECT) &&
                Objects.equals(this.isGunCorrect(), CORRECT) &&
                Objects.equals(this.isMurderCorrect(), CORRECT) &&
                Objects.equals(this.isZeppelinCorrect(), CORRECT)) {
            return true;
        }
        else
            return false;
    }

    public String toString() {
        return "Person(Name: " + this.name +
                " store: " + this.getComputer() +
                " car: " + this.getGun() +
                " vest: " + this.getJustmoe() +
                " food: " + this.getZeppelin();
    }

}