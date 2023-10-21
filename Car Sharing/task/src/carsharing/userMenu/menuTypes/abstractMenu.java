package carsharing.userMenu.menuTypes;

import java.util.List;

public abstract class abstractMenu {

    protected final List<String> ZERO_OPTIONS = List.of("Back", "Exit");
    protected abstract List<String> listOfOptions();
    public void printMenu() {
        listOfOptions().forEach( option -> {
            if (ZERO_OPTIONS.contains(option)) {
                System.out.println(0 + ". " + option);
            } else {
                System.out.println((listOfOptions().indexOf(option) + 1) + ". " + option);
            }
        });
    }

    public abstract void executeOption(Integer option);

}
