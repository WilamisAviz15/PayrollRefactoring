package payroll.commands.rotatepayroll;

import java.time.LocalDate;
import java.util.List;

import payroll.StackUndoRedo;
import payroll.employee.model.Employee;
import payroll.payment.MenuPayroll;

public class RotateCommands {
    public RotateCommands(){}
    
    public void rotateNow(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        MenuPayroll.rotate(list_employee, stack_undo_redo);
    }

    public void printAll(List<Employee> list_employee) {
        MenuPayroll.printAll(list_employee);
    }
}
