package payroll.commands.payoutschedule;

import payroll.IMenu;
import payroll.payment.model.PaymentSchedule;

public class CreateSchedule implements IMenu {
    ScheduleCommands SCommands;
    PaymentSchedule paySchedule;

    public CreateSchedule(ScheduleCommands SCommands, PaymentSchedule paySchedule){
        this.SCommands = SCommands;
        this.paySchedule = paySchedule;
    }

    public void execute(){
        this.SCommands.create(paySchedule);
    }
}
