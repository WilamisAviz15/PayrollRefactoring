package payroll.commands.editEmployee;

import java.util.List;

import payroll.IMenu;
import payroll.employee.model.Employee;

public class EditSyndicalIdentification implements IMenu {
    EditEmployeeCommands EECommand;
    List<Employee> list_employee;
    Employee emp;

    public EditSyndicalIdentification(Employee emp, List<Employee> list_employee, EditEmployeeCommands EECommand){
        this.emp = emp;
        this.EECommand = EECommand;
        this.list_employee = list_employee;
    }

    public void execute(){
        this.EECommand.AlterSyndicalIdentification(emp, list_employee);
    }
}
