package payroll.commands.editEmployee;

import java.util.List;

import payroll.IMenu;
import payroll.employee.model.Employee;
import payroll.payment.model.PaymentSchedule;

public class EditTypeEmployee implements IMenu {
    EditEmployeeCommands EECommand;
    Employee emp;
    List<Employee> list_employee;
    PaymentSchedule paySchedules;

    public EditTypeEmployee(Employee emp, EditEmployeeCommands EECommand, List<Employee> list_employee, PaymentSchedule paySchedules){
        this.emp = emp;
        this.EECommand = EECommand;
        this.list_employee = list_employee;
        this.paySchedules = paySchedules;
    }

    public void execute(){
        this.EECommand.AlterTypeEmployee(emp, list_employee, paySchedules);
    }
}
