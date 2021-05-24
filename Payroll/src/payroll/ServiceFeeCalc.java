package payroll;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import payroll.employee.MenuEmployee;
import payroll.employee.model.Employee;
import payroll.syndicate.AdditionalFee;
import payroll.syndicate.Syndicate;
import payroll.utils.Utils;

public class ServiceFeeCalc {
    public static void LaunchFee(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        String tmp = "";
        Double value;
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee.getSyndicate().getActive() == true) {
                Stack<List<Employee>> undo = stack_undo_redo.getUndo();
                Stack<List<Employee>> redo = stack_undo_redo.getRedo();
                undo.push(Utils.cloneList(list_employee));
                redo.clear();
                Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
                selectedEmployee.setSyndicate(s);
                List<AdditionalFee> aFX = Utils.cloneListAddFee(selectedEmployee.getSyndicate().getAdditionalFee());
                selectedEmployee.getSyndicate().setAdditionalFee(aFX);
                LocalDate date = Utils.validateDate(sc);
                System.out.println("Enter value");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                AdditionalFee aF = new AdditionalFee(date, value);
                selectedEmployee.getSyndicate().getAdditionalFee().add(aF);
                System.out.println("Additional Fee added successfully");
            } else {
                System.out.println(selectedEmployee.getName()
                        + " does not belongs to syndicate or is inactive to add service fee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}
