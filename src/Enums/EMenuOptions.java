package Enums;

public enum EMenuOptions {
    SAVE_DATA(1,"Save data to text file"),
    CREATE_DIRECTORY(2,"Create new directory in txt source file"),
    RESTORE_DATA(3, "Restore data from the txt source file"),
    DELETE_EXISTING_DIRECTORY(4,"Delete an existing directory by its name"),
    CLOSE_APP(5,"Close Application");

    private int id;
    private String name;

    EMenuOptions(int id, String name) {
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
        for(EMenuOptions eMenuOptions: values()){
            counter++;
        }
        return counter;
    }
}
