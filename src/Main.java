import Classes.*;
import Enums.EFileExtension;
import Enums.EFileType;
import Enums.EMenuOptions;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        final int  EXTENSION_SIZE = 3;

//        Source text file manipulation======================================================================================
        TextFile file = new TextFile("C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\test.txt");

        if(file.open()){
            String fileContent = file.readLine();
            String[] fileContentSplitted = fileContent.split("/");

            Directory[] directories = new Directory[fileContentSplitted.length];
            for(int i=0; i< fileContentSplitted.length; i++){
                fileContentSplitted[i] = fileContentSplitted[i].replaceAll("\\s+", "");;
            }
            String[] fileContentSplittedNew = Arrays.copyOf(fileContentSplitted, fileContentSplitted.length-1);
            for(String str: fileContentSplittedNew){
                String[] allDirectoryInfo = str.split(";");

                LinkedList<AFile> imageFiles = new LinkedList<>();
                LinkedList<AFile> audioFiles = new LinkedList<>();
                Directory newDirectory = new Directory();

                //eliminate the whitespaces
                for(int i=0; i< allDirectoryInfo.length; i++){
                    allDirectoryInfo[i] = allDirectoryInfo[i].replaceAll("\\s+", "");
                }

                newDirectory.setPath(allDirectoryInfo[0]);
                for(int i=1; i< allDirectoryInfo.length; i++){
                    String[] fileInfo = allDirectoryInfo[i].split(",");
                    if(fileInfo[0].length() == EXTENSION_SIZE+1){
                        //todo de adaugat exceptie custom
                    }else{
                        String extension = fileInfo[0].substring(allDirectoryInfo[0].length()-EXTENSION_SIZE);
                        if(extension.equalsIgnoreCase(EFileExtension.JPG.name()) || extension.equalsIgnoreCase(EFileExtension.PNG.name())) {
                            String fileName = fileInfo[0].substring(0, fileInfo[0].length() - EXTENSION_SIZE - 1);
                            EFileExtension.getExtension(extension);
                            AFile imageFile = new ImageFile(fileName, EFileExtension.getExtension(extension), Integer.parseInt(fileInfo[1]), Integer.parseInt(fileInfo[2]), Integer.parseInt(fileInfo[3]));
                            imageFiles.add(imageFile);
                        }
                        if(extension.equalsIgnoreCase(EFileExtension.WAV.name()) || extension.equalsIgnoreCase(EFileExtension.MP3.name())) {
                            String fileName = fileInfo[0].substring(0, fileInfo[0].length() - EXTENSION_SIZE - 1);
                            EFileExtension.getExtension(extension);
                            AFile audioFile = new AudioFile(fileName, EFileExtension.getExtension(extension), Integer.parseInt(fileInfo[1]), Integer.parseInt(fileInfo[2]));
                            audioFiles.add(audioFile);
                        }
//
                    }
                }
                HashMap<EFileType,LinkedList<AFile>> directoryContent = new HashMap<>();
                directoryContent.put(EFileType.IMAGE,imageFiles);
                directoryContent.put(EFileType.AUDIO,audioFiles);
                newDirectory.setDirectoryFiles(directoryContent);
                directories[Directory.countDirectory()] = newDirectory;
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
