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

            Directory[] directories = Directory.getAllDirectoryData(fileContent, EXTENSION_SIZE);

            //TODO realizarea de operatii CRUD cu salvare in memorie (de modificat acel hashmap)
            //TODO pentru statistici de utilizat doi vectori de tipuri fundamentale

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
            int optionId = scanner.nextInt();
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
                            Directory newDirectory = Directory.createDirectory(scanner);
                            if(newDirectory !=null){
                                Directory[] tempDirectories = new Directory[directories.length+1];
                                for(int i=0; i<tempDirectories.length-1; i++){
                                    tempDirectories[i] = directories[i];
                                }
                                tempDirectories[tempDirectories.length-1] = newDirectory;
                                directories = null;
                                directories = tempDirectories.clone();
                                Directory.countDirectory();
                                System.out.println("Directorul a fost creat cu succes");
                                menu.getMenuInitialDescription();
                                break;
                            }
                            //todo de adaugat posibil caz de else
                        case 3:
                            System.out.println("INTRODUCETI DENUMIREA DIRECTORULUI PE CARE DORITI SA IL STERGETI");
                            String directoryToFind = scanner.next();
                            if(Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))){
                                directories = Directory.DeleteDirectory(directoryToFind,directories);
                                System.out.println("Eliminarea a fost finalizata");
                                //todo de adaugat -> doriti sa continuati + caz cand nu mai este nimic de sters
                            }else{
                                System.out.println("Acest director nu exista. Doriti sa continuati?");
                            }
                        default:
                            System.out.println("OPTIUNEA ALEASA NU EXISTA");
                            //todo de adaugat intrebare -> doriti sa continuati (Da sau NU)
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                optionId = scanner.nextInt();
            }
            file.writeToFile(directories, "ceva.txt");
            System.out.println("Aplicatia a fost inchisa cu succes, iar datele au fost salvate");
        } else {
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
