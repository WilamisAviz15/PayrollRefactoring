package payroll.commands.payoutschedule;

import payroll.IMenu;
import payroll.payment.model.PaymentSchedule;

public class PrintSchedules implements IMenu {
    ScheduleCommands SCommands;
    PaymentSchedule paySchedule;

    public PrintSchedules(ScheduleCommands SCommands, PaymentSchedule paySchedule){
        this.SCommands = SCommands;
        this.paySchedule = paySchedule;
    }

    public void execute(){
        this.SCommands.printAll(paySchedule);
    }
}
