package Interfaces;

import Classes.Directory;
import Classes.Menu;

import java.util.Scanner;

public interface IMenuOperations {
    public Directory[] createDirectoryOption(Scanner scanner, Directory[] directories);
    public Directory[] deleteDirectoryOption(Scanner scanner, Directory[] directories);
    public Directory[] updateData(Scanner scanner, Directory[] directories, int inputOperationValue, Menu menu, boolean shouldContinue);
    public void generateStatistics(Directory[] directories, Scanner scanner);

}
