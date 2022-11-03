package Classes;

import java.io.*;

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
}
