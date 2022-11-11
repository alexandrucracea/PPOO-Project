package Classes;

import CustomExceptions.InvalidDirectoryName;
import Enums.EFileExtension;
import Enums.EFileType;
import Enums.EUpdateFileOptions;
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
        directoryFiles.put(EFileType.IMAGE,new LinkedList<>());
        directoryFiles.put(EFileType.AUDIO,new LinkedList<>());
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

    public static int countDirectory() {
        directoryCount++;
        return directoryCount;
    }

    public static int getDirectoryCount() {
        return directoryCount;
    }

    @Override
    public String toString() {
        return "Directory{" + "path='" + path + '\'' + ", directoryFiles=" + directoryFiles + '}';
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
        if (directoryPath != null) {
            Directory newDirectory = new Directory(directoryPath);
            return newDirectory;
        }
        System.out.println("Directorul nu a putut fi creat");
        //todo de adaugat exceptie custom
        return null;
    }

    public static Directory[] DeleteDirectory(String path, Directory[] directories) {
        //get index in directories array
        int indexToDelete = -1;
        for (int i = 0; i < directories.length; i++) {
            if (directories[i].getPath().equalsIgnoreCase(path)) {
                indexToDelete = i;
            }
        }
        ArrayList<Directory> directoryArrayList = new ArrayList<>(Arrays.asList(directories));
        directoryArrayList.remove(indexToDelete);
        Directory[] directoriesToReturn = directoryArrayList.toArray(new Directory[directories.length - 1]);
        return directoriesToReturn;
    }

    public static void updateDirectoryName(Directory[] directories, String directoryName, String directoryNewName) throws InvalidDirectoryName {
        if (checkIfDirectoryExists(directoryName, directories)) {
            for (Directory directory : directories) {
                if (directory.getPath().equalsIgnoreCase(directoryName)) {
                    directory.setPath(directoryNewName);
                }
            }
        } else {
            throw new InvalidDirectoryName("Directorul cautat nu exista");
        }

    }

    public static boolean checkIfDirectoryExists(String directoryName, Directory[] directories) {
        boolean exists = false;
        for (Directory directory : directories) {
            if (directory.getPath().equalsIgnoreCase(directoryName)) exists = true;
        }
        return exists;
    }

    public static void showAllDirectories(Directory[] directories) {
        System.out.println("Directoarele existente sunt:");
        for (Directory directory : directories) {
            System.out.println("Directory " + directory.getPath() + " data");
            directory.getDirectoryFiles().forEach((key, value) -> System.out.println(key + " files: " + value));
        }
    }

    public static void populateDirectory(Scanner scanner, Directory[] directories) {
        if (directories != null) {
            System.out.println("Care este numele directorului in care doriti sa creati fisierul?");
            String directoryToFind = scanner.next();
            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                Optional<Directory> directoryToCheck = Arrays.stream(directories).filter(x -> x.getPath().equalsIgnoreCase(directoryToFind)).findFirst();
                Directory directory = directoryToCheck.get();
                boolean loopCheck = false;
                do {
                    AFile fileToAdd = directory.createFile(scanner);
                    if (String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.JPG.name()) || String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.PNG.name())) {
                        directory.getDirectoryFiles().get(EFileType.IMAGE).add(fileToAdd); //todo ce se intampla cand nu am nimic, cum adaug?
                    }
                    if (String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.MP3.name()) || String.valueOf(fileToAdd.getFileExtension()).equalsIgnoreCase(EFileExtension.WAV.name())) {
                        directory.getDirectoryFiles().get(EFileType.AUDIO).add(fileToAdd);
                    }
                    System.out.println("Doriti sa mai adaugati un fisier in director? (DA sau NU)");
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("DA")) {
                        loopCheck = true;
                    } else if (choice.equalsIgnoreCase("NU")) {
                        loopCheck = false;
                    }
                } while (loopCheck);

            } else {
                System.out.println("Directorul cautat nu exista");
            }
        }
        //todo ce se intampla daca nu avem nimic in directories
    }

    public static void deleteDirectoryContent(Scanner scanner, Directory[] directories) {
        if (directories != null) {
            boolean loopCheck = false;
            System.out.println("Care este numele directorului in care doriti sa eliminati fisiere?");
            String directoryToFind = scanner.next();
            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                Optional<Directory> directoryToCheck = Arrays.stream(directories).filter(x -> x.getPath().equalsIgnoreCase(directoryToFind)).findFirst();
                Directory directory = directoryToCheck.get();
                do {
                    AFile fileToDelete = directory.findFile(scanner, directory);
                    if (fileToDelete != null) {
                        if (fileToDelete.getFileExtension() == EFileExtension.JPG || fileToDelete.getFileExtension() == EFileExtension.PNG) {
                            directory.getDirectoryFiles().get(EFileType.IMAGE).remove(fileToDelete);
                        }
                        if (fileToDelete.getFileExtension() == EFileExtension.WAV || fileToDelete.getFileExtension() == EFileExtension.MP3) {
                            directory.getDirectoryFiles().get(EFileType.AUDIO).remove(fileToDelete);
                        }
                        System.out.println("Fisierul a fost eliminat");
                        System.out.println("Doriti sa mai stergeti un fisier din director? (DA sau NU)");
                        String choice = scanner.next();
                        if (choice.equalsIgnoreCase("DA")) {
                            loopCheck = true;
                        } else if (choice.equalsIgnoreCase("NU")) {
                            loopCheck = false;
                        }
                    }else{
                        System.out.println("Fisierul cautat nu exista");
                        System.out.println("Doriti sa mai stergeti un fisier din director? (DA sau NU)");
                        String choice = scanner.next();
                        if (choice.equalsIgnoreCase("DA")) {
                            loopCheck = true;
                        } else if (choice.equalsIgnoreCase("NU")) {
                            loopCheck = false;
                        }
                    }

                } while (loopCheck);

            } else {
                //todo de creat exceptie ca nu exista directorul
            }
        }
        //todo ce se intampla daca nu avem nimic in directories
    }

    public static void updateDirectoryContent(Scanner scanner, Directory[] directories, Menu menu) {
        if (directories != null) {
            System.out.println("Care este numele directorului in care doriti sa modificati fisiere?");
            String directoryToFind = scanner.next();
            if (Arrays.stream(directories).anyMatch(x -> x.getPath().equalsIgnoreCase(directoryToFind))) {
                Optional<Directory> directoryToCheck = Arrays.stream(directories).filter(x -> x.getPath().equalsIgnoreCase(directoryToFind)).findFirst();
                Directory directory = directoryToCheck.get();
                boolean loopCheck = false;
                do {
                    AFile fileToUpdate = directory.findFile(scanner, directory);
                    if(fileToUpdate!=null){
                        if (fileToUpdate.getFileExtension() == EFileExtension.JPG || fileToUpdate.getFileExtension() == EFileExtension.PNG) {
                            int index = directory.getDirectoryFiles().get(EFileType.IMAGE).indexOf(fileToUpdate);
                            AFile updatedFile = directory.updateFile(scanner, fileToUpdate, menu);
                            if (updatedFile != null && !updatedFile.getFileName().equalsIgnoreCase(fileToUpdate.getFileName())) {
                                directory.getDirectoryFiles().get(EFileType.IMAGE).set(index, updatedFile);
                                System.out.println("Fisierul a fost actualizat");
                            }
                        }
                        if (fileToUpdate.getFileExtension() == EFileExtension.WAV || fileToUpdate.getFileExtension() == EFileExtension.MP3) {
                            int index = directory.getDirectoryFiles().get(EFileType.AUDIO).indexOf(fileToUpdate);
                            AFile updatedFile = directory.updateFile(scanner, fileToUpdate, menu);
                            if (updatedFile != null && !updatedFile.getFileName().equalsIgnoreCase(fileToUpdate.getFileName())) {
                                directory.getDirectoryFiles().get(EFileType.AUDIO).set(index, updatedFile);
                                System.out.println("Fisierul a fost actualizat");
                            }
                        }
                    }else{
                        System.out.println("Fisierul cautat nu se afla in acest director/nu exista");
                    }
                    System.out.println("Doriti sa mai actualizati un fisier din director? (DA sau NU)");
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("DA")) {
                        loopCheck = true;
                    } else if (choice.equalsIgnoreCase("NU")) {
                        loopCheck = false;
                    }
                } while (loopCheck);

            } else {
                System.out.println("Directorul cautat nu exista");
            }
        }
    }

    public static int getAllDirectoryFilesNumber(Directory[] directories) {
        int count = 0;
        for (Directory directory : directories) {
            count += directory.getDirectoryFiles().get(EFileType.IMAGE).size() + directory.getDirectoryFiles().get(EFileType.AUDIO).size();
        }
        return count;
    }

    public static void generateFileStatistics(Directory[] directories, EFileType eFileType) {
        int allFilesCount = getAllDirectoryFilesNumber(directories);
        int[] imageFilesSizes = new int[allFilesCount];
        int i = 0;
        for (Directory directory : directories) {
            for (AFile file : directory.getDirectoryFiles().get(EFileType.IMAGE)) {
                imageFilesSizes[i++] = file.getFileSize();
            }
        }
        OptionalDouble averageFileSize = Arrays.stream(imageFilesSizes).average();
        int sizeOfAllImageFiles = Arrays.stream(imageFilesSizes).sum();
        OptionalInt maxSizeOfImageFiles = Arrays.stream(imageFilesSizes).max();
        OptionalInt minSizeOfImageFiles = Arrays.stream(imageFilesSizes).min();
        if (averageFileSize.isPresent() && maxSizeOfImageFiles.isPresent() && minSizeOfImageFiles.isPresent()) {
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.format("%-70s %s", "Descriere", "Valoare\n");
            System.out.format("%-70s %s", "Media dimensiunii fisierelor de tip" + eFileType.name() + " este", averageFileSize.getAsDouble() + "\n");
            System.out.format("%-70s %s", "Suma dimensiunii fisierelor de tip" + eFileType.name() + " este", sizeOfAllImageFiles + "\n");
            System.out.format("%-70s %s", "Maximul dimensiunii fisierelor de tip" + eFileType.name() + " este", maxSizeOfImageFiles.getAsInt() + "\n");
            System.out.format("%-70s %s", "Minimul dimensiunii fisierelor de tip" + eFileType.name() + " este", minSizeOfImageFiles.getAsInt() + "\n");
            System.out.println("----------------------------------------------------------------------------------------");

            String fileName = "C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\" + eFileType.name() + "_STATISTICS.txt";
            TextFile file = new TextFile(fileName);
            boolean generated = false;
            if (file.open()) {
                generated = file.writeStatisticsToFile(averageFileSize.getAsDouble(), sizeOfAllImageFiles, maxSizeOfImageFiles.getAsInt(), minSizeOfImageFiles.getAsInt(), file.fileName, eFileType);
            }
            if (generated) {
                System.out.println("Statisticile au fost salvate cu succes in fisierul cu numele " + file.fileName);
            }
        }

    }

    @Override
    public AFile createFile(Scanner scanner) {
        AFile file;
        System.out.println("Ce fel de fisier doriti sa creati?");
        System.out.println("Pentru fisier audio introduceti tasta 1. Pentru fisier imagine introduceti tasta 2");
        int option = scanner.nextInt();
        if (option == 2) {
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
            if (fileName != null && extension != null && dimension != 0 && width != 0 && height != 0) {
                ImageFile imageFile = new ImageFile(fileName, EFileExtension.getExtension(extension), dimension, height, width);
                return imageFile;
            }

        } else if (option == 1) {
            System.out.println("Introduceti denumirea fisierului pe care doriti sa il creati");
            String fileName = scanner.next();
            System.out.println("Introduceti extensia noului fisier: wav sau mp3.");
            String extension = scanner.next();
            System.out.println("Introduceti dimensiunea noului fisier");
            int dimension = scanner.nextInt();
            System.out.println("Introduceti durata noului fisier");
            int duration = scanner.nextInt();
            if (fileName != null && extension != null && dimension != 0 && duration != 0) {
                AudioFile audioFile = new AudioFile(fileName, EFileExtension.getExtension(extension), dimension, duration);
                return audioFile;
            }//todo ce se intampla pe else si aici
        } else {
            System.out.println("Optiunea introdusa nu este corecta");
            //todo de tratat acest caz de exceptie -> eventual o revenire sau o intrebare daca se doreste continuarea
        }
        return null;
    }

    @Override
    public AFile findFile(Scanner scanner, Directory directory) {
        Optional<AFile> file;
        System.out.println("Care este denumirea fisierului pe care doriti sa il stergeti/actualizati?");
        String fileNameToDelete = scanner.next();
        System.out.println("Care este extensia acestui fisier? (MP3, WAV, JPG, PNG)");
        String fileExtensionToDelete = scanner.next();

        if (fileExtensionToDelete.equalsIgnoreCase(EFileExtension.JPG.name()) || fileExtensionToDelete.equalsIgnoreCase(EFileExtension.PNG.name())) {
            file = directory.getDirectoryFiles().get(EFileType.IMAGE).stream().filter(x -> x.getFileName().equalsIgnoreCase(fileNameToDelete) && String.valueOf(x.getFileExtension()).equalsIgnoreCase(fileExtensionToDelete)).findFirst();
            if (file.isPresent()) {
                return file.get();
            }
        }
        if (fileExtensionToDelete.equalsIgnoreCase(EFileExtension.MP3.name()) || fileExtensionToDelete.equalsIgnoreCase(EFileExtension.WAV.name())) {
            file = directory.getDirectoryFiles().get(EFileType.AUDIO).stream().filter(x -> x.getFileName().equalsIgnoreCase(fileNameToDelete) && String.valueOf(x.getFileExtension()).equalsIgnoreCase(fileExtensionToDelete)).findFirst();
            if (file.isPresent()) {
                return file.get();
            }
        }
        return null;
        //todo de rezolvat eroarea de aici
    }

    @Override
    public AFile updateFile(Scanner scanner, AFile file, Menu menu) {
        menu.getMenuFileUpdateOperations();
        int option = scanner.nextInt();
        if (option == EUpdateFileOptions.RENAME_FILE.getId()) {
            System.out.println("Introduceti noua denumire a fisierului");
            String newName = scanner.next();
            file.setFileName(newName);
        }
        if (option == EUpdateFileOptions.BACK_TO_MAIN_MENU.getId()) {
            return file;
        }
        return file;
//        Menu.getMenuFileUpdateOperations();
//        int option = scanner.nextInt();

    }

}
