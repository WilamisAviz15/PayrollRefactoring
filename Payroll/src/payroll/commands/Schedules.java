package payroll.commands;

import payroll.IMenu;

public class Schedules implements IMenu {
    Commands command;

    public Schedules(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.Schedules();
    }
}
