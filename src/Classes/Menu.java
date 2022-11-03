package Classes;

import Enums.EMenuOptions;
import Interfaces.IMenuOperations;

import java.util.Arrays;

public class Menu {
    private MenuOperation[] menuOperations;
    private static int counter = 0;

    public Menu() {
        this.menuOperations = new MenuOperation[EMenuOptions.getNoOfOptions()];
        for(EMenuOptions eMenuOptions: EMenuOptions.values()){
            MenuOperation menuOperation = new MenuOperation(eMenuOptions);
            menuOperations[counter++] = menuOperation;
        }
    }

    public MenuOperation[] getMenuOperations() {
        return menuOperations;
    }

    public void setMenuOperations(MenuOperation[] menuOperations) {
        this.menuOperations = menuOperations;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuOperations=" + Arrays.toString(menuOperations) +
                '}';
    }

}
