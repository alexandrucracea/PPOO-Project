package Classes;

import Enums.EFileType;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;

/**
 * <h1>Text File Class</h1>
 * <p>This class models a Text File, making working with text files easier</p>
 */


public class TextFile {
    File myFile;
    String fileName;

    public TextFile(String fileName) {
        this.fileName = fileName;
    }

    public boolean open(){
        myFile = new File(fileName);
        if(myFile.exists()){
            return true;
        }else{
            return false;
        }
    }

    public String getFilePath() throws IOException {
        myFile = new File(fileName);
        if(myFile.exists()){
            return myFile.getAbsolutePath();
        }else{
            return null;
        }
    }

    /***
     * <h1>Method used to read from a text file</h1>
     * @return String
     */
    public String readLine(){
        try{
            String line;
            FileInputStream input = new FileInputStream(myFile);          //preiau streamul -> pas1
            InputStreamReader inputReader = new InputStreamReader(input); //fac legatura streamului din bytes in caractere -> pasul 2
            BufferedReader bufferInput = new BufferedReader(inputReader); //citesc textul din input stream reader -> pasul 3

            StringBuffer stringBuffer = new StringBuffer();
            while((line = bufferInput.readLine())!=null){
                stringBuffer.append(line);
                stringBuffer.append(" ");
            }
            bufferInput.close();
            return stringBuffer.toString();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /***
     * <h1>Method used to write to a textfile</h1>
     * <p>This method writes a collection of Directory objects into a text file</p>
     *
     * @param allDirectoriesInfo
     * @param fileName
     */
    public void writeToFile(Directory[] allDirectoriesInfo, String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        for (Directory directory: allDirectoriesInfo){
            if(directory != null){
                //            Attach the directory name
                stringBuilder.append(directory.getPath());

                stringBuilder.append(";\n");
                for(Map.Entry<EFileType,LinkedList<AFile>> directoryContent: directory.getDirectoryFiles().entrySet()){
                    if(directoryContent.getKey().name().equalsIgnoreCase(EFileType.IMAGE.name())){
                        LinkedList<AFile> files = directoryContent.getValue();
                        for(AFile file: files){
                            ImageFile imagefile = (ImageFile) file;
                            stringBuilder.append(imagefile.getFileName() + "." + imagefile.getFileExtension().name()+"," + imagefile.getFileSize() + ",");
                            stringBuilder.append(imagefile.getHeight() + "," + imagefile.getWidth());
                            stringBuilder.append(";\n");
                        }
                    }
                    if(directoryContent.getKey().name().equalsIgnoreCase(EFileType.AUDIO.name())){
                        LinkedList<AFile> files = directoryContent.getValue();
                        for(AFile file: files){
                            AudioFile audioFile = (AudioFile) file;
                            stringBuilder.append(audioFile.getFileName() + "." + audioFile.getFileExtension().name().toLowerCase()+"," + audioFile.getFileSize() + ",");
                            stringBuilder.append(audioFile.getDuration());
                            stringBuilder.append(";\n");
                        }
                    }
            }
                stringBuilder.append("/\n");

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(String.valueOf(stringBuilder));
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * <h1>Generate statistics</h1>
     * <p>Method used for generating statistics for a certain file type</p>
     *
     * @param averageFileSize
     * @param sizeOfAllImageFiles
     * @param maxSizeOfImageFiles
     * @param minSizeOfImageFiles
     * @param fileName
     * @param eFileType
     * @return boolean
     */

    public boolean writeStatisticsToFile(double averageFileSize,int sizeOfAllImageFiles, int maxSizeOfImageFiles,int minSizeOfImageFiles, String fileName, EFileType eFileType){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Statisticile generale sunt:\n");
        stringBuilder.append("Media dimensiunii fisierelor de tip" + eFileType.name() + " este\t");
        stringBuilder.append(averageFileSize + "\n");
        stringBuilder.append("Suma dimensiunii fisierelor de tip" + eFileType.name() + " este\t");
        stringBuilder.append(sizeOfAllImageFiles + "\n");
        stringBuilder.append("Maximul dimensiunii fisierelor de tip" + eFileType.name() + " este\t");
        stringBuilder.append(maxSizeOfImageFiles + "\n");
        stringBuilder.append("Minimul dimensiunii fisierelor de tip" + eFileType.name() + " este\t");
        stringBuilder.append(minSizeOfImageFiles + "\n");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(String.valueOf(stringBuilder));
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /***
     * <h1>Delete a Text File</h1>
     * <p>Method used to delete a text file</p>
     *
     * @return true if the file was deleted, otherwise it returns false
     */
    public boolean DeleteFile(){
       if(myFile.delete()){
           return true;
       }else{
           System.out.println("Fisierul nu a putut fi sters");
            return false;
       }
    }
}
