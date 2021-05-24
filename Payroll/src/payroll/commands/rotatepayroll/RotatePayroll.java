package payroll.commands.rotatepayroll;

import java.util.List;

import payroll.IMenu;
import payroll.StackUndoRedo;
import payroll.employee.model.Employee;

public class RotatePayroll implements IMenu {
    RotateCommands RCommands;
    List<Employee> list_employee;
    StackUndoRedo stack_undo_redo;

    public RotatePayroll(RotateCommands RCommands, List<Employee> list_employee, StackUndoRedo stack_undo_redo){
        this.RCommands = RCommands;
        this.list_employee = list_employee;
        this.stack_undo_redo = stack_undo_redo;
    }

    public void execute(){
        this.RCommands.rotateNow(list_employee, stack_undo_redo);
    }
}
