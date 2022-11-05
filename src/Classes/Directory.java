package Classes;

import Enums.EFileType;
import Interfaces.IDirectoryOperations;

import java.util.HashMap;
import java.util.LinkedList;

public class Directory implements IDirectoryOperations {
    private String path;
    private HashMap<EFileType, LinkedList<AFile>> directoryFiles;
    private static int directoryCount = 0;

    public Directory() {
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


    @Override
    public String toString() {
        return "Directory{" +
                "path='" + path + '\'' +
                ", directoryFiles=" + directoryFiles +
                '}';
    }

    @Override
    public void populateDirectory(String directoryData) {
        //parse the string received with the following rule
        //line 1 - directory path
        //next lines are the files
            //i have to decide which files to create to pupulate the hashmap
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
