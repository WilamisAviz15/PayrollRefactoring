package payroll.commands;

import payroll.IMenu;

public class Rotate implements IMenu {
    Commands command;

    public Rotate(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.RotateP();
    }
}
