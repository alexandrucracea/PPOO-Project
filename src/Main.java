import Classes.*;
import CustomExceptions.InvalidCommandException;
import Enums.EFileExtension;
import Enums.EFileType;
import Enums.EMenuOptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        final int EXTENSION_SIZE = 3;
        final String appFilePath = "C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\test.txt";

//        Source text file manipulation======================================================================================
        TextFile file = new TextFile(appFilePath);

        if (file.open()) {
            String fileContent = file.readLine();

            Directory[] directories = Directory.getAllDirectoryData(fileContent, EXTENSION_SIZE);

            //TODO realizarea de operatii CRUD cu salvare in memorie (de modificat acel hashmap)
            //TODO pentru statistici de utilizat doi vectori de tipuri fundamentale

//            Menu area
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);

            int closingId = 0;
            String inputValue;
            int inputOperationTypeValue = -1;

            for (MenuOption menuOption : menu.getMenuOperations()) {
                if (menuOption.getOptionName().equals(EMenuOptions.CLOSE_APP.getName())) {
                    closingId = menuOption.optionId;
                }
            }
            menu.getMenuInitialDescription();
            int optionId = scanner.nextInt();
            boolean shouldContinue = true;
            while (optionId != closingId) {
                try {
                    switch (optionId) {
                        case 1:
                            file.writeToFile(directories, "ceva.txt");
                            System.out.println("Datele au fost salvate cu succes\n");
                            Thread.sleep(2000);
                            menu.getMenuInitialDescription();
                            break;

                        case 2:
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
                            menu.getMenuInitialDescription();
                            break;

                        case 3:
                            System.out.println("INTRODUCETI DENUMIREA DIRECTORULUI PE CARE DORITI SA IL STERGETI");
                            String directoryToFind = scanner.next();
                            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                                directories = Directory.DeleteDirectory(directoryToFind, directories);
                                System.out.println("Eliminarea a fost finalizata");
                                break;
                                //todo de adaugat -> doriti sa continuati + caz cand nu mai este nimic de sters
                            } else {
                                System.out.println("Acest director nu exista. Doriti sa continuati?");
                            }

                        case 4:
                            System.out.println("Ce operatie doriti sa realizat in acest director?");
                            System.out.println("Pentru redenumirea directorului tastati 1");
                            System.out.println("Pentru operatii de gestiune a fisierelor dintr-un anumit director apasati 2");
                            inputOperationTypeValue = scanner.nextInt();

                            if(inputOperationTypeValue == 1){
                                System.out.println("INTRODUCETI DENUMIREA DIRECTORULUI PE CARE DORITI SA IL REDENUMITI");
                                String directoryName = scanner.next();
                                System.out.println("INTRODUCETI NOUA DENUMIRE A DIRECTORULUI");
                                String directoryNewName = scanner.next();
                                Directory.updateDirectoryName(directories,directoryName,directoryNewName);
                                System.out.println("Operatia a fost finalizata cu succes");
                                System.out.println("\nDaca doriti sa continuati scrieti DA. Pentru a inchide aplicatia scrieti NU");
                                inputValue = scanner.next();
                                if (inputValue.equalsIgnoreCase("DA")) {
                                    menu.getMenuInitialDescription();
                                    break;
                                } else {
                                    if (inputValue.equalsIgnoreCase("NU")) {
                                        shouldContinue = false;
                                        break;
                                    }
                                }
                            }else{
                                if(inputOperationTypeValue == 2){
                                    menu.getMenuFileOperations();
                                    break;
                                }
                            }
                        case 5:
                            Directory.showAllDirectories(directories);
                            System.out.println("Daca doriti sa continuati scrieti DA. Pentru a inchide aplicatia scrieti NU");
                            inputValue = scanner.next();
                            if (inputValue.equalsIgnoreCase("DA")) {
                                menu.getMenuInitialDescription();
                                break;
                            } else {
                                if (inputValue.equalsIgnoreCase("NU")) {
                                    shouldContinue = false;
                                    break;
                                }
                            }

                        default:
                            try {
                                throw new InvalidCommandException("OPTIUNEA ALEASA NU EXISTA");
                            } catch (InvalidCommandException ex) {
                                System.err.println(ex);
                                System.out.println("\nDaca doriti sa continuati scrieti DA. Pentru a inchide aplicatia scrieti NU");

                                inputValue = scanner.next();
                                if (inputValue.equalsIgnoreCase("DA")) {
                                    menu.getMenuInitialDescription();
                                    break;
                                } else {
                                    if (inputValue.equalsIgnoreCase("NU")) {
                                        shouldContinue = false;
                                        break;
                                    }
                                }
                            }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!shouldContinue) {
                    break;
                }
                optionId = scanner.nextInt();
            }

            if(file.DeleteFile()){
                file.writeToFile(directories, appFilePath);
                System.out.println("Aplicatia a fost inchisa cu succes, iar datele au fost salvate");
            }
        } else {
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
