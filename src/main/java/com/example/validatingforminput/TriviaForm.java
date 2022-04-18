package com.example.validatingforminput;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TriviaForm {

    @NotNull
    @Size(min=3, max=10)
    private String name;

    @NotNull
    @Size(min=3, max=10)
    private String store;

    @NotNull
    @Size(min=3, max=10)
    private String car;

    @NotNull
    @Size(min=3, max=10)
    private String vest;

    @NotNull
    @Size(min=3, max=10)
    private String food;


    //TODO: cannot run function on initial HTTP reqeust bc all strings null!
    public boolean isStoreCorrect() {
        boolean result = false;

        if (store != null){
            return (store.equalsIgnoreCase("apu") ||
                    store.equalsIgnoreCase("Apu Nahasapeemapetilon") ||
                    store.equalsIgnoreCase("Nahasapeemapetilon") ||
                    store.equalsIgnoreCase("Apu Nahasapasa"));
        }
       return result;
    }

    public boolean isCarCorrect() {
        return car.equalsIgnoreCase("canyon arrow") ||
                car.equalsIgnoreCase("Canyonero" );
    }

    public boolean isVestCorrect() {
        return vest.equalsIgnoreCase("gorrilla chest") ||
                vest.equalsIgnoreCase("gorrilla") ||
                vest.equalsIgnoreCase("real gorilla chest");
    }

    public boolean isFoodCorrect() {
        return food.equalsIgnoreCase("blowfish") ||
                food.equalsIgnoreCase("fugu");
    }


    public String toString() {
        return "Person(Name: " + this.name +
                " store: " + this.getStore() +
                " car: " + this.getCar() +
                " vest: " + this.getVest() +
                " food: " + this.getFood();
    }

}