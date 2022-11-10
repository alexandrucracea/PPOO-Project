package Classes;

import CustomExceptions.InvalidCommandException;
import CustomExceptions.InvalidDirectoryName;
import CustomExceptions.InvalidDirectorySizeException;
import Enums.EFileOptions;
import Enums.EFileType;
import Enums.EMenuOptions;
import Enums.EUpdateFileOptions;
import Interfaces.IMenuOperations;

import java.util.Arrays;
import java.util.Scanner;

public class Menu implements IMenuOperations {
    private MenuOption[] menuOperations;
    private static int counter = 0;

    public Menu() {
        this.menuOperations = new MenuOption[EMenuOptions.getNoOfOptions()];
        for (EMenuOptions eMenuOptions : EMenuOptions.values()) {
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

    public void getMenuInitialDescription() {
        System.out.println("Va rugam sa alegeti optiunile corespunzatoare operatiilor pe care doriti sa le efectuati");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("%-70s %s", "Descriere", "Tasta\n");
        for (MenuOption menuOperation : menuOperations) {
            System.out.format("%-72s %s", menuOperation.getOptionName(), menuOperation.getOptionId() + "\n");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Alegeti tasta corespunzatoare optiunii pe care o doriti:");
    }

    public void getMenuFileOperations() {
        MenuOption[] fileOptions = new MenuOption[EFileOptions.getNoOfOptions()];
        int fileOptCounter = 0;
        for (EFileOptions eFileOptions : EFileOptions.values()) {
            MenuOption fileOperation = new MenuOption(eFileOptions);
            fileOptions[fileOptCounter++] = fileOperation;
        }

        System.out.println("Va rugam sa alegeti optiunile corespunzatoare operatiilor pe care doriti sa le efectuati");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("%-70s %s", "Descriere", "Tasta\n");
        for (MenuOption fileOption : fileOptions) {
            System.out.format("%-72s %s", fileOption.getOptionName(), fileOption.getOptionId() + "\n");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Alegeti tasta corespunzatoare optiunii pe care o doriti:");
    }

    public void getMenuFileUpdateOperations() {
        MenuOption[] fileOptions = new MenuOption[EUpdateFileOptions.getNoOfOptions()];
        int fileOptCounter = 0;
        for (EUpdateFileOptions eUpdateFileOptions : EUpdateFileOptions.values()) {
            MenuOption updateFileOperation = new MenuOption(eUpdateFileOptions);
            fileOptions[fileOptCounter++] = updateFileOperation;
        }

        System.out.println("Va rugam sa alegeti optiunile corespunzatoare operatiilor pe care doriti sa le efectuati");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.format("%-70s %s", "Descriere", "Tasta\n");
        for (MenuOption fileOption : fileOptions) {
            System.out.format("%-72s %s", fileOption.getOptionName(), fileOption.getOptionId() + "\n");
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Alegeti tasta corespunzatoare optiunii pe care o doriti:");
    }

    public boolean getRerenderingMenuQuestion(String inputValue, Scanner scanner) {
        System.out.println("\nDaca doriti sa accesati meniul principal scrieti DA. Pentru a inchide aplicatia scrieti NU");
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

    @Override
    public Directory[] createDirectoryOption(Scanner scanner, Directory[] directories) {
        do {
            Directory newDirectory = Directory.createDirectory(scanner);
            if (newDirectory != null) {
                Directory[] tempDirectories = new Directory[directories.length + 1];
                for (int i = 0; i < tempDirectories.length - 1; i++) {
                    tempDirectories[i] = directories[i];
                }
                tempDirectories[tempDirectories.length - 1] = newDirectory;
                directories = null;
                directories = tempDirectories.clone();
                Directory.countDirectory();
                System.out.println("Directorul a fost creat cu succes");
            }
            //todo optiune pentru populat directorul recent creat
            System.out.println("Doriti sa populati directorul cu fisiere multimedia?");

            System.out.println("\nDaca doriti sa reluati operatia, scrieti DA, altfel scrieti NU");

        } while (!scanner.next().equalsIgnoreCase("NU"));
        return directories;
    }

    @Override
    public Directory[] deleteDirectoryOption(Scanner scanner, Directory[] directories) {
        System.out.println("INTRODUCETI DENUMIREA DIRECTORULUI PE CARE DORITI SA IL STERGETI");
        String directoryToFind = scanner.next();
        try {
            if (directories.length == 0)
                throw new InvalidDirectorySizeException("Nu exista niciun director pentru a putea fi efectuata stergerea");
        } catch (InvalidDirectorySizeException ex) {
            System.err.println(ex);
            return directories;
        }
        if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
            directories = Directory.DeleteDirectory(directoryToFind, directories);
            System.out.println("Eliminarea a fost finalizata");
        } else {
            System.out.println("Acest director nu exista. Doriti sa continuati?");
        }
        return directories;
    }

    @Override
    public Directory[] updateData(Scanner scanner, Directory[] directories, int inputOperationTypeValue, Menu menu, boolean shouldContinue) {
        String inputValue = "";
        System.out.println("Ce operatie doriti sa realizat in acest director?");
        System.out.println("Pentru redenumirea directorului tastati 1");
        System.out.println("Pentru operatii de gestiune a fisierelor dintr-un anumit director apasati 2");
        inputOperationTypeValue = scanner.nextInt();

        if (inputOperationTypeValue == 1) {
            System.out.println("INTRODUCETI DENUMIREA DIRECTORULUI PE CARE DORITI SA IL REDENUMITI");
            String directoryName = scanner.next();
            System.out.println("INTRODUCETI NOUA DENUMIRE A DIRECTORULUI");
            String directoryNewName = scanner.next();
            try {
                Directory.updateDirectoryName(directories, directoryName, directoryNewName);
            } catch (InvalidDirectoryName e) {
                System.err.println(e);
            }
//                                Thread.sleep(500);
            shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
            return directories;
        } else {
            if (inputOperationTypeValue == 2) {
                menu.getMenuFileOperations();
                int inputFileOperationValue = scanner.nextInt();
                System.out.println("Directoarele existente sunt:");
                System.out.println("");
                if (inputFileOperationValue == EFileOptions.CREATE_FILE.getId()) {
                    Directory.showAllDirectories(directories);
                    Directory.populateDirectory(scanner, directories);
                    System.out.println("Fisierul a fost adaugat cu succes");
                    shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                    return directories;
                } else if (inputFileOperationValue == EFileOptions.DELETE_FILE.getId()) {
                    Directory.showAllDirectories(directories);
                    Directory.deleteDirectoryContent(scanner, directories);
                    shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                    return directories;
                } else if (inputFileOperationValue == EFileOptions.UPDATE_FILE.getId()) {
                    Directory.showAllDirectories(directories);
                    Directory.updateDirectoryContent(scanner, directories, menu);
                    shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                    return directories;
                } else if (inputFileOperationValue == EFileOptions.BACK_TO_MAIN_MENU.getId()) {
                    shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                    return directories;
                } else {
                    try {
                        throw new InvalidCommandException("OPTIUNEA ALEASA NU EXISTA");
                    } catch (InvalidCommandException ex) {
                        System.err.println(ex);
                        shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                    }
                }
            }
            return directories;
        }
    }

    @Override
    public void generateStatistics(Directory[] directories, Scanner scanner) {
        System.out.println("Pentru statistici pentru fisiere de tip IMAGINE apasati tasta 1");
        System.out.println("Pentru statistici pentru fisiere de tip AUDIO apasati tasta 2");
        int option = scanner.nextInt();
        if(option == 1){
            Directory.generateFileStatistics(directories, EFileType.IMAGE);
        }else if(option == 2){
            Directory.generateFileStatistics(directories,EFileType.AUDIO);
        }
    }


}
