import Classes.ImageFile;
import Classes.Menu;
import Classes.TextFile;
import Enums.EMenuOptions;

public class Main {
    public static void main(String[] args) {

//        Text file manipulation
        TextFile file = new TextFile("C:\\Facultate\\MASTER EBUS\\AN1\\SEM1\\PPOO\\PROIECT\\src\\test.txt");

        if(file.open()){
            String fileContent = file.readLine();
            System.out.println(fileContent);
            //todo citire mai multe linii + separator pentru a delimita ceea ce cere proiectul - oarecum realizat
            //TODO salvare date citite + creare clasa Director care contine o colectie de fisiere audio si image
            //TODO realizarea de operatii CRUD cu salvare prin scriere intr-un fisier text de iesire

//            Menu area
            //TODO realizarea unui meniu in consola
            Menu menu = new Menu();
            System.out.println(menu);
        }else{
            System.out.println("Fisierul nu a putut fi deschis");
        }
    }
}
