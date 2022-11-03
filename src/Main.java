import Classes.*;
import Enums.EFileExtension;
import Enums.EMenuOptions;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int  EXTENSION_SIZE = 4;

//        Text file manipulation======================================================================================
        TextFile file = new TextFile("C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\test.txt");

        if(file.open()){
            String fileContent = file.readLine();
            String[] fileContentSplitted = fileContent.split("/");

            Directory[] directories = new Directory[fileContentSplitted.length];
            for(String str: fileContentSplitted){
                String[] tempString = str.split(" ");
                LinkedList<AFile> imageFiles = new LinkedList<>();
                LinkedList<AFile> textFiles = new LinkedList<>();
                //todo de adaugat separator intre fisierele mici pentru ca trebuie adaugata dimensiunea etc etc in functie de tipul de fisier
                for(String directoryFile: tempString){
                    if(directoryFile.length() == EXTENSION_SIZE){

                    }else{
                        String extension = directoryFile.substring(directoryFile.length()-EXTENSION_SIZE);
                        if(extension.toUpperCase().equals(EFileExtension.JPG) || extension.toUpperCase().equals(EFileExtension.PNG)){
                            String fileName = directoryFile.substring(0,directoryFile.length()-EXTENSION_SIZE);
                            AFile imageFile = new ImageFile(fileName,EFileExtension.getExtension(extension),0,100,400);
                        }
                    }
                }
//                Directory directory = new Directory(tempString[0])

            }
            //TODO salvare date citite
            //TODO realizarea de operatii CRUD cu salvare prin scriere intr-un fisier text de iesire

//            Menu area
            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);

            int closingId=0;
            for(MenuOption menuOption : menu.getMenuOperations()){
                if(menuOption.getOptionName().equals(EMenuOptions.CLOSE_APP.getName())){
                    closingId = menuOption.optionId;
                }
            }
            menu.getMenuInitialDescription();

            while(scanner.nextInt()!=closingId){
                System.out.println("ATA ETE");
            }
        }else{
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
