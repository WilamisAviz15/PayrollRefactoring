package payroll.commands;

import payroll.IMenu;

public class RemoveEmployee implements IMenu {
    Commands command;

    public RemoveEmployee(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.RemoveEmployee();
    }
}