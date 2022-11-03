package Classes;

import Enums.EFileExtension;

public class ImageFile extends AFile{
    private static final String resolutionMeasurment = "pixels";
    private int height;
    private int width;

    public ImageFile(String fileName, EFileExtension fileExtension, int fileSize) {
        super(fileName, fileExtension, fileSize);
    }

    public ImageFile(String fileName, EFileExtension fileExtension, int fileSize, int height, int width) {
        super(fileName, fileExtension, fileSize);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "ImageFile:" +
                "height=" + height +
                ", width=" + width +
                ' ';
    }

    //todo de adaugat in toString si constanta
    //todo de regandit daca adaugam un measurment unit la AFIle si ii dam o alta valoare in functie de clasa
    //todo de creat interfetele + gandit operatiile din acestea
}
