package payroll.commands.editEmployee;

import payroll.IMenu;
import payroll.employee.model.Employee;

public class JoinOrLeftSyndicate implements IMenu {
    EditEmployeeCommands EECommand;
    Employee emp;

    public JoinOrLeftSyndicate(Employee emp, EditEmployeeCommands EECommand){
        this.emp = emp;
        this.EECommand = EECommand;
    }

    public void execute(){
        this.EECommand.AlterJoinOrLeftSyndicate(emp);
    }
}
