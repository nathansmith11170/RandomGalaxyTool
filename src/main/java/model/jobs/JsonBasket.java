package model.jobs;

import model.AbstractJson;

public class JsonBasket extends AbstractJson {

    private String basket;

    public JsonBasket() {
    }

    public String getBasket() {
        return basket;
    }

    public void setBasket(String basket) {
        this.basket = basket;
    }
}
