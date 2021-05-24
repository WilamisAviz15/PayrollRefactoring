package payroll.commands;

import payroll.IMenu;

public class LoginTime implements IMenu {
    Commands command;

    public LoginTime(Commands command){
        this.command = command;
    }

    public void execute() {
        this.command.AddTimecardLogin();
    }
}
