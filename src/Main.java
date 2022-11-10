import Classes.*;
import CustomExceptions.InvalidCommandException;
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

//            Menu area
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);

            int closingId = 0;
            String inputValue = "";
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
                            directories = menu.createDirectoryOption(scanner, directories);
                            menu.getMenuInitialDescription();
                            break;
                        case 3:
                            directories = menu.deleteDirectoryOption(scanner,directories);
                            shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                            break;
                        case 4:
                            directories = menu.updateData(scanner,directories,inputOperationTypeValue,menu,shouldContinue);
                            shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                            break;
                        case 5:
                            Directory.showAllDirectories(directories);
                            shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                            break;
                        case 6:
                            menu.generateStatistics(directories,scanner);
                            shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
                            break;
                        default:
                            try {
                                throw new InvalidCommandException("OPTIUNEA ALEASA NU EXISTA");
                            } catch (InvalidCommandException ex) {
                                System.err.println(ex);
                                shouldContinue = menu.getRerenderingMenuQuestion(inputValue, scanner);
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

            if (file.DeleteFile()) {
                file.writeToFile(directories, appFilePath);
                System.out.println("Aplicatia a fost inchisa cu succes, iar datele au fost salvate");
            }
        } else {
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
