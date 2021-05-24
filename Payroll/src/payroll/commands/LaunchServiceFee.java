package payroll.commands;

import payroll.IMenu;

public class LaunchServiceFee implements IMenu {
    Commands command;

    public LaunchServiceFee(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.AddServiceFee();
    }
}
