package payroll.payment;

import java.util.ArrayList;
import java.util.Scanner;

import payroll.IMenu;
import payroll.Menu;
import payroll.commands.payoutschedule.CreateSchedule;
import payroll.commands.payoutschedule.PrintSchedules;
import payroll.commands.payoutschedule.ScheduleCommands;
import payroll.commands.rotatepayroll.PrintPayroll;
import payroll.payment.model.PaymentSchedule;
import payroll.utils.Utils;

public class MenuPayoutSchedule {

    public static void printAllSchedules(PaymentSchedule paySchedule) {
        System.out.println("--- Avaliable schedules ---");
        paySchedule.print();
        System.out.println("---------------------------");
    }

    public static void createSchedule(PaymentSchedule paySchedule, Scanner sc) {
        String schedule[] = {"monthly", "weekly"}, stringPaymentSchedule="", day="", tmp="", week="";
        String daysOfWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int i;
        System.out.println("Schedule will be monthly or weekly?");
        System.out.println("0 - Monthly");
        System.out.println("1 - Weekly");
        tmp = Utils.consoleReadInputIntegerWithOR(tmp, sc, 0, 1);
        i = Integer.parseInt(tmp);
        if(tmp.equals("0")){
            System.out.println("Enter the number of the day (1-9 or 10-31): (in case the last day of month, write $)");
            day = Utils.consoleReadInputIntegerSpecial(day, sc, 0);
            stringPaymentSchedule = schedule[i] + " " + day;
        } else{
            System.out.println("Enter how many weeks");
            System.out.println("1 - Every one week");
            System.out.println("2 - Every two weeks");
            week = Utils.consoleReadInputIntegerWithOR(week, sc, 1, 2);
            System.out.println("Select the day of the week");
            int j = 0;
            for (String aux : daysOfWeek) {
                System.out.println(j++ + " - " + aux);
            }
            day = Utils.consoleReadInputIntegerOptions(day, sc, 0,j);
            int dayInteger = Integer.parseInt(day);
            stringPaymentSchedule = schedule[i] + " " + week +" " + daysOfWeek[dayInteger];
        }
        paySchedule.setTypesSchedule(stringPaymentSchedule);
        System.out.println("Payment schedule successfully registered.");
    }

    public static void Menu(PaymentSchedule paySchedule) {
        int option;
        String tmp = "";
        Scanner op = new Scanner(System.in);
        Menu menu = new Menu();
        ScheduleCommands commands = new ScheduleCommands();
        ArrayList<IMenu> optionsMenu = new ArrayList<IMenu>();
        optionsMenu.add(new CreateSchedule(commands, paySchedule));
        optionsMenu.add(new PrintSchedules(commands, paySchedule));
        while (true) {
            System.out.println("Choose one:\n\n" + "[0]  Add new Payment Schedule\n" + "[1]  List All Payment Schedules\n" + "[2]  Back\n");
            tmp = Utils.consoleReadInputIntegerOptions(tmp, op, 0, 3);
            option = Integer.parseInt(tmp);
            if (option == 2) {
                break;
            }
            menu.setCommand(optionsMenu.get(option));
            menu.executeComand();
        }
    }
}
