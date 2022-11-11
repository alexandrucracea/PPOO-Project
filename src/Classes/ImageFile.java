package Classes;

import Enums.EFileExtension;

/***
 * <h1>Image File Class</h1>
 * <p>This class extends AFile abstract class and models an image file</p>
 */
public class ImageFile extends AFile{
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
        return "ImageFile{" +
                "height=" + height +
                ", width=" + width +
                "} " + super.toString();
    }

}
