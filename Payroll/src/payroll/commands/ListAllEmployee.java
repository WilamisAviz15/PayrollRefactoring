package payroll.commands;

import payroll.IMenu;

public class ListAllEmployee implements IMenu {
    Commands command;

    public ListAllEmployee(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.ListAllEmployee();
    }
}