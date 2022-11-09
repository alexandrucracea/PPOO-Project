package Enums;

public enum EMenuOptions {
    SAVE_DATA(1,"SALVATI DATELE CURENTE"),
    CREATE_DIRECTORY(2,"CREATI UN NOU DIRECTOR"),
    DELETE_EXISTING_DIRECTORY(3,"STERGETI UN DIRECTOR"),
    UPDATE_EXISTING_DIRECTORY(4,"ACTUALIZATI UN DIRECTOR"),
    SHOW_ALL_DIRECTORIES_DATA(5,"AFISATI TOATE DIRECTOARELE"),
    GET_STATISTICS(6,"GENERATI STATISTICI"),
    CLOSE_APP(7,"INCHIDETI APLICATIA");

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
