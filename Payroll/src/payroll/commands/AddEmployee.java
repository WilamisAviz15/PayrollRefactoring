package payroll.commands;

import payroll.IMenu;

public class AddEmployee implements IMenu {
    Commands command;

    public AddEmployee(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.createEmployee();
    }
}
