package Classes;

import Enums.EFileOptions;
import Enums.EMenuOptions;
import Enums.EUpdateFileOptions;
import Interfaces.IMenuOperations;

public class MenuOption implements IMenuOperations {
    public int optionId;
    public String optionName;

    public MenuOption(EMenuOptions eMenuOptions) {
        this.optionId = eMenuOptions.getId();
        this.optionName = eMenuOptions.getName();
    }
    public MenuOption(EFileOptions eFileOptions) {
        this.optionId = eFileOptions.getId();
        this.optionName = eFileOptions.getName();
    }

    public MenuOption(EUpdateFileOptions eFileOptions) {
        this.optionId = eFileOptions.getId();
        this.optionName = eFileOptions.getName();
    }

    public int getOptionId() {
        return optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    @Override
    public void createDirectoryOption() {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void restoreData() {

    }

    @Override
    public String toString() {
        return "MenuOperation{" +
                "optionId=" + optionId +
                ", optionName='" + optionName + '\'' +
                '}';
    }
}
