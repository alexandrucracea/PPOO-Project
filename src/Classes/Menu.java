package Classes;

import Enums.EFileOptions;
import Enums.EMenuOptions;

import java.util.Arrays;
import java.util.Scanner;

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

    public void getMenuFileOperations(){
        MenuOption[] fileOptions = new MenuOption[EFileOptions.getNoOfOptions()];
        int fileOptCounter = 0;
        for(EFileOptions eFileOptions: EFileOptions.values()){
            MenuOption fileOperation = new MenuOption(eFileOptions);
            fileOptions[fileOptCounter++] = fileOperation;
        }

        System.out.println("Va rugam sa alegeti optiunile corespunzatoare operatiilor pe care doriti sa le efectuati");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("%-70s %s", "Descriere","Tasta\n");
        for(MenuOption fileOption : fileOptions){
            System.out.format("%-72s %s",fileOption.getOptionName(),fileOption.getOptionId() + "\n");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Alegeti tasta corespunzatoare optiunii pe care o doriti:");
    }
    public boolean getRerenderingMenuQuestion(String inputValue, Scanner scanner){
        System.out.println("\nDaca doriti sa continuati scrieti DA. Pentru a inchide aplicatia scrieti NU");
        inputValue = scanner.next();
        boolean shouldContinue = true;
        if (inputValue.equalsIgnoreCase("DA")) {
            this.getMenuInitialDescription();
        } else {
            if (inputValue.equalsIgnoreCase("NU")) {
               shouldContinue = false;
            }
        }
        return shouldContinue;
    }
}
