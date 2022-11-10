package Interfaces;

import Classes.AFile;
import Classes.Directory;
import Classes.Menu;

import java.util.Scanner;

public interface IDirectoryOperations {
    public AFile createFile( Scanner scanner);
    public AFile findFile(Scanner scanner, Directory directory);
    public AFile updateFile(Scanner scanner, AFile file, Menu menu);

}
