package Classes;

import Enums.EFileType;
import Interfaces.IDirectoryOperations;

import java.util.HashMap;
import java.util.LinkedList;

public class Directory implements IDirectoryOperations {
    private String path;
    private HashMap<EFileType, LinkedList<AFile>> directoryFiles;

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

    @Override
    public String toString() {
        return "Directory{" +
                "path='" + path + '\'' +
                ", directoryFiles=" + directoryFiles +
                '}';
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
