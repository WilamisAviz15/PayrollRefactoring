package payroll.commands;

import payroll.IMenu;

public class Exit implements IMenu {
    Commands command;

    public Exit(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.Exit();
    }
}
