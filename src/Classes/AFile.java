package Classes;

import Enums.EFileExtension;

/***
 * <h1>AFile abstract class</h1>
 * Clasa abstracta creata pentru a putea fi mostenita.
 * Aceasta are rolul de a oferi atribute si metode claselor ce o vor mosteni.
 *
 *
 * @version : 1.0
 * @author: A.Cracea
 */
public abstract class AFile {
    private String fileName;
    private EFileExtension fileExtension;
    private int fileSize;

    public AFile(String fileName, EFileExtension fileExtension, int fileSize) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public EFileExtension getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(EFileExtension fileExtension) {
        this.fileExtension = fileExtension;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "File:" +
                "fileName='" + fileName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                " ";
    }
}
