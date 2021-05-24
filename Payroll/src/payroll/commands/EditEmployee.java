package payroll.commands;

import payroll.IMenu;

public class EditEmployee implements IMenu {
    Commands command;

    public EditEmployee(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.EditEmployee();
    }
}
