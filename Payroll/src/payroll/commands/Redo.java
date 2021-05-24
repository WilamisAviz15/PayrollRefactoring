package payroll.commands;

import payroll.IMenu;

public class Redo implements IMenu {
    Commands command;

    public Redo(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.Redo();
    }
}
