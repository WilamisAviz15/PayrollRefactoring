package payroll;

import java.util.List;
import java.util.Stack;
import payroll.employee.model.Employee;

public class StackUndoRedo {
    Stack<List<Employee>> undo, redo;

    public StackUndoRedo(Stack<List<Employee>> undo, Stack<List<Employee>> redo){
        this.undo = undo;
        this.redo = redo;
    }

    public Stack<List<Employee>> getUndo() {
        return undo;
    }

    public void setUndo(Stack<List<Employee>> undo) {
        this.undo = undo;
    }

    public Stack<List<Employee>> getRedo() {
        return redo;
    }

    public void setRedo(Stack<List<Employee>> redo) {
        this.redo = redo;
    }
}
