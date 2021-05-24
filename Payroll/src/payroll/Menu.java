package payroll;

public class Menu {
    IMenu slot;

    public Menu(){}

    public void setCommand(IMenu command) {
        this.slot = command;
    }

    public void executeComand() {
        this.slot.execute();
    }
}
