package Enums;

public enum EUpdateFileOptions {
    RENAME_FILE(1,"REDENUMITI UN FISIER"),
    BACK_TO_MAIN_MENU(2,"INAPOI LA MENIUL ANTERIOR");

    private int id;
    private String name;

    EUpdateFileOptions(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static int getNoOfOptions(){
        int counter = 0;
        for(EUpdateFileOptions eMenuOptions: values()){
            counter++;
        }
        return counter;
    }
}
