package payroll.commands.editEmployee;

import payroll.IMenu;
import payroll.employee.model.Employee;
import payroll.payment.model.PaymentSchedule;

public class EditPaymentMethod implements IMenu {
    EditEmployeeCommands EECommand;
    Employee emp;
    PaymentSchedule paySchedules;

    public EditPaymentMethod(Employee emp, EditEmployeeCommands EECommand, PaymentSchedule paySchedules){
        this.emp = emp;
        this.EECommand = EECommand;
        this.paySchedules = paySchedules;
    }

    public void execute(){
        this.EECommand.AlterPaymentMethod(emp, paySchedules);
    }
}
