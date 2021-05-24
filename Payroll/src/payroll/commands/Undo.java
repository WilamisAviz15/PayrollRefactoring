package payroll.commands;

import payroll.IMenu;

public class Undo implements IMenu {
    Commands command;

    public Undo(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.Undo();
    }
}
