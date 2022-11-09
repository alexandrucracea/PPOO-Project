package Enums;

public enum EFileOptions {
    CREATE_FILE(8, "CREATI UN FISIER"),
    DELETE_FILE(9, "STERGETI UN FISIER"),
    UPDATE_FILE(10, "ACTUALIZATI UN FISIER");

    private int id;
    private String name;

    EFileOptions(int id, String name) {
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
        for(EFileOptions eMenuOptions: values()){
            counter++;
        }
        return counter;
    }
}
