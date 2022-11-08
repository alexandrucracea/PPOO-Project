package Classes;

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

    @Override
    public void populateDirectory(String directoryData) {

    }

    @Override
    public void createFile(AFile file) {

    }

    @Override
    public void deleteFile(AFile file) {

    }

    @Override
    public void updateFile(AFile file) {

    }

    @Override
    public void renameFile(AFile file, String newName) {

    }
}
