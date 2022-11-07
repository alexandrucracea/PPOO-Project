import Classes.*;
import Enums.EFileExtension;
import Enums.EFileType;
import Enums.EMenuOptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        final int EXTENSION_SIZE = 3;

//        Source text file manipulation======================================================================================
        TextFile file = new TextFile("C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\test.txt");

        if (file.open()) {
            String fileContent = file.readLine();

            //todo de mutat intr-o functie
            Directory[] directories = Directory.getAllDirectoryData(fileContent, EXTENSION_SIZE);

            //TODO salvare date citite intr-un fisier text
            //TODO realizarea de operatii CRUD cu salvare in memorie (de modificat acel hashmap)
            //TODO pentru statistici de utilizat doi vectori de tipuri fundamentale
            //TODO operatii de stergere a directoarelor din hashmap

//            Menu area
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);

            int closingId = 0;
            for (MenuOption menuOption : menu.getMenuOperations()) {
                if (menuOption.getOptionName().equals(EMenuOptions.CLOSE_APP.getName())) {
                    closingId = menuOption.optionId;
                }
            }
            menu.getMenuInitialDescription();

            while (scanner.nextInt() != closingId) {
                System.out.println("ATA ETE");
            }
            file.writeToFile(directories,"ceva.txt");
        } else {
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
