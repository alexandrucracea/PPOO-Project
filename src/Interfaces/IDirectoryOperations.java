package Interfaces;

import Classes.AFile;
import Classes.Directory;

import java.util.Scanner;

public interface IDirectoryOperations {
    public void populateDirectory(String directoryData);
    public void createFile(AFile file);
    public void deleteFile(AFile file);
    public void updateFile(AFile file);
    public void renameFile(AFile file, String newName);

}
