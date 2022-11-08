package Interfaces;

import Classes.AFile;
import Classes.Directory;

import java.util.Scanner;

public interface IDirectoryOperations {
    public void populateDirectory(String directoryData);
    public AFile createFile(AFile file, Scanner scanner);
    public void deleteFile(AFile file);
    public void updateFile(AFile file);
    public void renameFile(AFile file, String newName);

}
