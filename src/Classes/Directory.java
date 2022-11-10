package Classes;

import CustomExceptions.InvalidDirectoryName;
import Enums.EFileExtension;
import Enums.EFileType;
import Interfaces.IDirectoryOperations;

import java.util.*;

public class Directory implements IDirectoryOperations {
    private String path;
    private HashMap<EFileType, LinkedList<AFile>> directoryFiles;
    private static int directoryCount = 0;

    public Directory() {
    }

    public Directory(String path) {
        this.path = path;
        this.directoryFiles = new HashMap<>();
    }

    public Directory(String path, HashMap<EFileType, LinkedList<AFile>> directoryFiles) {
        this.path = path;
        this.directoryFiles = directoryFiles;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<EFileType, LinkedList<AFile>> getDirectoryFiles() {
        return directoryFiles;
    }

    public void setDirectoryFiles(HashMap<EFileType, LinkedList<AFile>> directoryFiles) {
        this.directoryFiles = directoryFiles;
    }

    public static int countDirectory(){
        directoryCount++;
        return directoryCount;
    }

    public static int getDirectoryCount() {
        return directoryCount;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "path='" + path + '\'' +
                ", directoryFiles=" + directoryFiles +
                '}';
    }

    public static Directory[] getAllDirectoryData(String fileContent, int EXTENSION_SIZE) {
        String[] fileContentSplitted = fileContent.split("/");

        for (int i = 0; i < fileContentSplitted.length; i++) {
            fileContentSplitted[i] = fileContentSplitted[i].replaceAll("\\s+", "");
        }

        String[] fileContentSplittedNew = Arrays.copyOf(fileContentSplitted, fileContentSplitted.length - 1);
        Directory[] directories = new Directory[fileContentSplittedNew.length]; //fileContentSplittedNew.length

        for (String str : fileContentSplittedNew) {
            String[] allDirectoryInfo = str.split(";");

            LinkedList<AFile> imageFiles = new LinkedList<>();
            LinkedList<AFile> audioFiles = new LinkedList<>();
            Directory newDirectory = new Directory();

            //eliminate the whitespaces
            //todo de pus in alta functie
            for (int i = 0; i < allDirectoryInfo.length; i++) {
                allDirectoryInfo[i] = allDirectoryInfo[i].replaceAll("\\s+", "");
            }

            newDirectory.setPath(allDirectoryInfo[0]);
            for (int i = 1; i < allDirectoryInfo.length; i++) {
                String[] fileInfo = allDirectoryInfo[i].split(",");
                if (fileInfo[0].length() == EXTENSION_SIZE + 1) {
                    //todo de adaugat exceptie custom
                } else {
                    String extension = fileInfo[0].substring(allDirectoryInfo[0].length() - EXTENSION_SIZE);
                    if (extension.equalsIgnoreCase(EFileExtension.JPG.name()) || extension.equalsIgnoreCase(EFileExtension.PNG.name())) {
                        String fileName = fileInfo[0].substring(0, fileInfo[0].length() - EXTENSION_SIZE - 1);
                        EFileExtension.getExtension(extension);
                        AFile imageFile = new ImageFile(fileName, EFileExtension.getExtension(extension), Integer.parseInt(fileInfo[1]), Integer.parseInt(fileInfo[2]), Integer.parseInt(fileInfo[3]));
                        imageFiles.add(imageFile);
                    }
                    if (extension.equalsIgnoreCase(EFileExtension.WAV.name()) || extension.equalsIgnoreCase(EFileExtension.MP3.name())) {
                        String fileName = fileInfo[0].substring(0, fileInfo[0].length() - EXTENSION_SIZE - 1);
                        EFileExtension.getExtension(extension);
                        AFile audioFile = new AudioFile(fileName, EFileExtension.getExtension(extension), Integer.parseInt(fileInfo[1]), Integer.parseInt(fileInfo[2]));
                        audioFiles.add(audioFile);
                    }
                }
            }
            HashMap<EFileType, LinkedList<AFile>> directoryContent = new HashMap<>();
            directoryContent.put(EFileType.IMAGE, imageFiles);
            directoryContent.put(EFileType.AUDIO, audioFiles);
            newDirectory.setDirectoryFiles(directoryContent);
            directories[Directory.directoryCount] = newDirectory;
            Directory.countDirectory();

        }
        return directories;
    }

    public static Directory createDirectory(Scanner scanner) {
        System.out.println("Va rugam introduceti calea/denumirea directorului pe care doriti sa il creati");
        String directoryPath = scanner.next();
        if(directoryPath!=null){
            Directory newDirectory = new Directory(directoryPath);
            return newDirectory;
        }
        System.out.println("Directorul nu a putut fi creat");
        //todo de adaugat exceptie custom
        return null;
    }

    public static Directory[] DeleteDirectory(String path, Directory[] directories){
        //get index in directories array
        int indexToDelete = 0;
        for(int i=0; i<directories.length;i++){
            if(directories[i].getPath().equalsIgnoreCase(path)){
                indexToDelete = i;
            }
        }
        ArrayList<Directory> directoryArrayList = new ArrayList<>(Arrays.asList(directories));
        directoryArrayList.remove(indexToDelete);
        Directory[] directoriesToReturn = directoryArrayList.toArray(new Directory[directories.length-1]);
        return directoriesToReturn;
    }

    public static void updateDirectoryName(Directory[] directories, String directoryName, String directoryNewName) throws InvalidDirectoryName {
        if(checkIfDirectoryExists(directoryName,directories)){
            for(Directory directory : directories){
                if(directory.getPath().equalsIgnoreCase(directoryName)){
                    directory.setPath(directoryNewName);
                }
            }
        }else{
            throw new InvalidDirectoryName("Directorul cautat nu exista");
        }

    }

    public static boolean checkIfDirectoryExists(String directoryName, Directory[] directories){
        boolean exists = false;
        for(Directory directory: directories){
            if(directory.getPath().equalsIgnoreCase(directoryName))
                exists = true;
        }
        return exists;
    }

    public static void showAllDirectories(Directory[] directories){
        for(Directory directory : directories){
            System.out.println("Directory " + directory.getPath() + " data");
            directory.getDirectoryFiles().forEach((key, value) -> System.out.println(key + " files: " + value));
        }
    }

    public static void populateDirectory(Scanner scanner, Directory[] directories) {
        if(directories!=null){
            System.out.println("Care este numele directorului in care doriti sa creati fisierul?");
            String directoryToFind = scanner.next();
            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                Optional<Directory> directoryToCheck = Arrays.stream(directories).filter(x -> x.getPath().equalsIgnoreCase(directoryToFind))
                        .findFirst();
                Directory directory = directoryToCheck.get();
                boolean loopCheck = false;
                do{
                    AFile fileToAdd = directory.createFile(scanner);
                    if (String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.JPG.name()) ||
                            String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.PNG.name())) {
                        directory.getDirectoryFiles().get(EFileType.IMAGE).add(fileToAdd);
                    }
                    if (String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.MP3.name()) ||
                            String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.WAV.name())) {
                        directory.getDirectoryFiles().get(EFileType.IMAGE).add(fileToAdd);
                    }
                    System.out.println("Doriti sa mai adaugati un fisier in director? (DA sau NU)");
                    String choice = scanner.next();
                    if(choice.equalsIgnoreCase("DA")){
                        loopCheck = true;
                    }else if(choice.equalsIgnoreCase("NU")){
                        loopCheck = false;
                    }
                }while(loopCheck );

            }else{
                //todo de creat exceptie ca nu exista directorul
            }
        }
       //todo ce se intampla daca nu avem nimic in directories
    }

    public static void deleteDirectoryContent(Scanner scanner, Directory[] directories){
        if(directories!=null){
            System.out.println("Care este numele directorului in care doriti sa eliminati fisiere?");
            String directoryToFind = scanner.next();
            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                Optional<Directory> directoryToCheck = Arrays.stream(directories).filter(x -> x.getPath().equalsIgnoreCase(directoryToFind))
                        .findFirst();
                Directory directory = directoryToCheck.get();
                boolean loopCheck = false;
                do{
                    AFile fileToDelete = directory.findFileToDelete(scanner,directory);
                    if(fileToDelete.getFileExtension() == EFileExtension.JPG || fileToDelete.getFileExtension() == EFileExtension.PNG){
                        directory.getDirectoryFiles().get(EFileType.IMAGE).remove(fileToDelete);
                    }
                    if(fileToDelete.getFileExtension() == EFileExtension.WAV || fileToDelete.getFileExtension() == EFileExtension.MP3){
                        directory.getDirectoryFiles().get(EFileType.AUDIO).remove(fileToDelete);
                    }
                    //todo ce se intampla cand nu mai avem fisiere de sters
                    System.out.println("Fisierul a fost eliminat");
                    System.out.println("Doriti sa mai stergeti un fisier din director? (DA sau NU)");
                    String choice = scanner.next();
                    if(choice.equalsIgnoreCase("DA")){
                        loopCheck = true;
                    }else if(choice.equalsIgnoreCase("NU")){
                        loopCheck = false;
                    }
                }while(loopCheck);

            }else{
                //todo de creat exceptie ca nu exista directorul
            }
        }
        //todo ce se intampla daca nu avem nimic in directories
    }

    @Override
    public AFile createFile( Scanner scanner) {
        AFile file;
        System.out.println("Ce fel de fisier doriti sa creati?");
        System.out.println("Pentru fisier audio introduceti tasta 1. Pentru fisier imagine introduceti tasta 2");
        int option = scanner.nextInt();
        if(option == 2){
                System.out.println("Introduceti denumirea fisierului pe care doriti sa il creati");
                String fileName = scanner.next();
                System.out.println("Introduceti extensia noului fisier: jpg sau png.");
                String extension = scanner.next();
                System.out.println("Introduceti dimensiunea noului fisier");
                int dimension = scanner.nextInt();
                System.out.println("Introduceti dimensiunile fisierului imagine:");
                System.out.println("WIDTH");
                int width = scanner.nextInt();
                System.out.println("HEIGHT");
                int height = scanner.nextInt();
                if(fileName!= null && extension!=null && dimension != 0 && width != 0 && height != 0 ){
                    ImageFile imageFile = new ImageFile(fileName,EFileExtension.getExtension(extension),dimension,height,width);
                    return imageFile;
                }

        }else if(option == 1){
            System.out.println("Introduceti denumirea fisierului pe care doriti sa il creati");
            String fileName = scanner.next();
            System.out.println("Introduceti extensia noului fisier: wav sau mp3.");
            String extension = scanner.next();
            System.out.println("Introduceti dimensiunea noului fisier");
            int dimension = scanner.nextInt();
            System.out.println("Introduceti durata noului fisier");
            int duration = scanner.nextInt();
            if(fileName!= null && extension!=null && dimension != 0 && duration !=0){
                AudioFile audioFile = new AudioFile(fileName,EFileExtension.getExtension(extension),dimension,duration);
                return audioFile;
            }//todo ce se intampla pe else si aici
        }else{
            System.out.println("Optiunea introdusa nu este corecta");
            //todo de tratat acest caz de exceptie -> eventual o revenire sau o intrebare daca se doreste continuarea
        }
        return null;
    }

    @Override
    public AFile findFileToDelete(Scanner scanner,Directory directory) {
        Optional<AFile> file;
        System.out.println("Care este denumirea fisierului pe care doriti sa il stergeti?");
        String fileNameToDelete= scanner.next();
        System.out.println("Care este extensia acestui fisier? (MP3, WAV, JPG, PNG)");
        String fileExtensionToDelete = scanner.next();

        if (fileExtensionToDelete.equalsIgnoreCase(EFileExtension.JPG.name()) ||
                fileExtensionToDelete.equalsIgnoreCase(EFileExtension.PNG.name())) {
          file = directory.getDirectoryFiles().get(EFileType.IMAGE).stream()
                  .filter(x -> x.getFileName().equalsIgnoreCase(fileNameToDelete) && String.valueOf(x.getFileExtension()).equalsIgnoreCase(fileExtensionToDelete)).findFirst();
            if(file.isPresent()){
                return file.get();
            }
        }
        if (fileExtensionToDelete.equalsIgnoreCase(EFileExtension.MP3.name()) ||
                fileExtensionToDelete.equalsIgnoreCase(EFileExtension.WAV.name())) {
            file = directory.getDirectoryFiles().get(EFileType.AUDIO).stream()
                    .filter(x -> x.getFileName().equalsIgnoreCase(fileNameToDelete) && String.valueOf(x.getFileExtension()).equalsIgnoreCase(fileExtensionToDelete)).findFirst();
            if(file.isPresent()){
                return file.get();
            }
        }
        return null;
    }

    @Override
    public void updateFile(AFile file) {

    }

    @Override
    public void renameFile(AFile file, String newName) {

    }
}
