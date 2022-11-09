package Classes;

import Enums.EFileType;

import java.io.*;
import java.util.LinkedList;
import java.util.Map;

/**
 * <h1>Java class responsible of manipulating Text Files</h1>
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

    public boolean DeleteFile(){
       if(myFile.delete()){
           System.out.println("Fisierul a fost sters cu succes");
           return true;
       }else{
           System.out.println("Fisierul nu a putut fi sters");
            return false;
       }
    }
}
