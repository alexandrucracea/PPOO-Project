package Enums;

public enum EFileExtension {
    MP3,WAV,JPG,PNG;

    public static EFileExtension getExtension(String extensionName){
        if(extensionName.toUpperCase().equals(EFileExtension.MP3)){
            return (EFileExtension.MP3);
        }
        if(extensionName.toUpperCase().equals(EFileExtension.WAV)){
            return (EFileExtension.WAV);
        }
        if(extensionName.toUpperCase().equals(EFileExtension.JPG)){
            return (EFileExtension.JPG);
        }
        if(extensionName.toUpperCase().equals(EFileExtension.PNG)){
            return (EFileExtension.PNG);
        }
        return null;
    }
}
