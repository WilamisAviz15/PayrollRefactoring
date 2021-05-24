package payroll.commands;

import payroll.IMenu;

public class LogoutTime implements IMenu {
    Commands command;

    public LogoutTime(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.AddTimecardLogout();
    }
}
