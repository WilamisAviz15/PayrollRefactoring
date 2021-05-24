package payroll.commands.rotatepayroll;

import java.util.List;

import payroll.IMenu;
import payroll.employee.model.Employee;

public class PrintPayroll implements IMenu {
    RotateCommands RCommands;
    List<Employee> list_employee;

    public PrintPayroll(RotateCommands RCommands, List<Employee> list_employee){
        this.RCommands = RCommands;
        this.list_employee = list_employee;
    }

    public void execute(){
        this.RCommands.printAll(list_employee);
    }
}
