package payroll.commands;

import payroll.IMenu;

public class LaunchSales implements IMenu {
    Commands command;

    public LaunchSales(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.AddSales();
    }
}
