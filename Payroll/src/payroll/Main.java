package payroll;

import java.util.ArrayList;
import java.util.Scanner;

import payroll.commands.AddEmployee;
import payroll.commands.Commands;
import payroll.commands.EditEmployee;
import payroll.commands.Exit;
import payroll.commands.LaunchSales;
import payroll.commands.LaunchServiceFee;
import payroll.commands.ListAllEmployee;
import payroll.commands.ListEmployeeById;
import payroll.commands.LoginTime;
import payroll.commands.LogoutTime;
import payroll.commands.Redo;
import payroll.commands.RemoveEmployee;
import payroll.commands.Rotate;
import payroll.commands.Schedules;
import payroll.commands.Undo;
import payroll.utils.Utils;

public class Main {
    public static void main(String[] args) {
        Scanner op = new Scanner(System.in);
        String option = "";
        int opc;
        Menu menu = new Menu();
        Commands commands = new Commands();
        ArrayList<IMenu> optionsMenu = new ArrayList<IMenu>();
        optionsMenu.add(new AddEmployee(commands));
        optionsMenu.add(new ListAllEmployee(commands));
        optionsMenu.add(new RemoveEmployee(commands));
        optionsMenu.add(new ListEmployeeById(commands));
        optionsMenu.add(new EditEmployee(commands));
        optionsMenu.add(new LoginTime(commands));
        optionsMenu.add(new LogoutTime(commands));
        optionsMenu.add(new LaunchSales(commands));
        optionsMenu.add(new LaunchServiceFee(commands));
        optionsMenu.add(new Rotate(commands));
        optionsMenu.add(new Schedules(commands));
        optionsMenu.add(new Undo(commands));
        optionsMenu.add(new Redo(commands));
        optionsMenu.add(new Exit(commands));
        while (true) {
            System.out.println("===============PAYROLL===============");
            System.out.println("Choose one:\n\n" + "[0]  Add employee\n" + "[1]  List all employees\n" + "[2]  Remove employee\n" + "[3]  Search Employees\n" + "[4]  Edit Employees\n" + "[5]  Add Timecard Login\n" + "[6]  Add Timecard Logout\n" + "[7]  Add Sales\n" + "[8]  Add Service Fee\n" + "[9]  Rotate Payroll\n" + "[10] Payout Schedules\n" + "[11] Undo\n" + "[12] Redo\n" + "[13] Exit\n");
            System.out.println("================================================");
            option = Utils.consoleReadInputIntegerOptions(option, op, 0, 14);
            opc = Integer.parseInt(option);
            if (opc == 13) {
                break;
            }
            menu.setCommand(optionsMenu.get(opc));
            menu.executeComand();
        }
    }
}
