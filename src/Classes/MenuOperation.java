package Classes;

import Enums.EMenuOptions;
import Interfaces.IMenuOperations;

public class MenuOperation implements IMenuOperations {
    public int optionId;
    public String optionName;

    public MenuOperation(EMenuOptions eMenuOptions) {
        this.optionId = eMenuOptions.getId();
        this.optionName = eMenuOptions.getName();
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
