package payroll.commands.editEmployee;

import payroll.IMenu;
import payroll.employee.model.Employee;

public class EditAddr implements IMenu {
    EditEmployeeCommands EECommand;
    Employee emp;

    public EditAddr(Employee emp, EditEmployeeCommands EECommand){
        this.emp = emp;
        this.EECommand = EECommand;
    }

    public void execute(){
        this.EECommand.AlterAddr(emp);
    }
}
