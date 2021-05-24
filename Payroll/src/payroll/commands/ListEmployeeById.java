package payroll.commands;

import payroll.IMenu;

public class ListEmployeeById implements IMenu {
    Commands command;

    public ListEmployeeById(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.ListEmployeeById();
    }
}
