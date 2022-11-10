package Interfaces;

import Classes.AFile;
import Classes.Directory;

import java.util.Scanner;

public interface IDirectoryOperations {
    public AFile createFile( Scanner scanner);
    public AFile findFileToDelete(Scanner scanner, Directory directory);
    public void updateFile(AFile file);
    public void renameFile(AFile file, String newName);

}
