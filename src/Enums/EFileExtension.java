package Enums;

public enum EFileExtension {
    MP3,WAV,JPG,PNG;

    public static EFileExtension getExtension(String extensionName){
        if(extensionName.equalsIgnoreCase(EFileExtension.MP3.name())){
            return (EFileExtension.MP3);
        }
        if(extensionName.equalsIgnoreCase(EFileExtension.WAV.name())){
            return (EFileExtension.WAV);
        }
        if(extensionName.equalsIgnoreCase(EFileExtension.JPG.name())){
            return (EFileExtension.JPG);
        }
        if(extensionName.equalsIgnoreCase(EFileExtension.PNG.name())){
            return (EFileExtension.PNG);
        }
        return null;
    }
}
