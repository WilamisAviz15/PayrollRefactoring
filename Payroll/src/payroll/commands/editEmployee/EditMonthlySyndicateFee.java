package payroll.commands.editEmployee;

import payroll.IMenu;
import payroll.employee.model.Employee;

public class EditMonthlySyndicateFee implements IMenu {
    EditEmployeeCommands EECommand;
    Employee emp;

    public EditMonthlySyndicateFee(Employee emp, EditEmployeeCommands EECommand){
        this.emp = emp;
        this.EECommand = EECommand;
    }

    public void execute(){
        this.EECommand.AlterMonthlySyndicateFee(emp);
    }
}
