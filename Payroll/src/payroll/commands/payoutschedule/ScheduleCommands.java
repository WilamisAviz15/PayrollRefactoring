package payroll.commands.payoutschedule;

import java.util.Scanner;

import payroll.payment.MenuPayoutSchedule;
import payroll.payment.model.PaymentSchedule;

public class ScheduleCommands {
    public ScheduleCommands(){}
    Scanner sc = new Scanner(System.in);

    public void create(PaymentSchedule paySchedule){
        MenuPayoutSchedule.createSchedule(paySchedule, sc);
    }

    public void printAll(PaymentSchedule paySchedule){
        MenuPayoutSchedule.printAllSchedules(paySchedule);
    }
}
