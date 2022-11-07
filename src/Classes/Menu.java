package Classes;

import Enums.EMenuOptions;

import java.util.Arrays;

public class Menu {
    private MenuOption[] menuOperations;
    private static int counter = 0;

    public Menu() {
        this.menuOperations = new MenuOption[EMenuOptions.getNoOfOptions()];
        for(EMenuOptions eMenuOptions: EMenuOptions.values()){
            MenuOption menuOperation = new MenuOption(eMenuOptions);
            menuOperations[counter++] = menuOperation;
        }
    }

    public MenuOption[] getMenuOperations() {
        return menuOperations;
    }

    public void setMenuOperations(MenuOption[] menuOperations) {
        this.menuOperations = menuOperations;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuOperations=" + Arrays.toString(menuOperations) +
                '}';
    }
    public void getMenuInitialDescription(){
        System.out.println("Va rugam sa alegeti optiunile corespunzatoare operatiilor pe care doriti sa le efectuati");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("%-70s %s", "Descriere","Tasta\n");
        for(MenuOption menuOperation : menuOperations){
            System.out.format("%-72s %s",menuOperation.getOptionName(),menuOperation.getOptionId() + "\n");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Alegeti tasta corespunzatoare optiunii pe care o doriti:");
    }
}
