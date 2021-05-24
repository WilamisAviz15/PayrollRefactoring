package payroll;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


import payroll.employee.MenuEmployee;
import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Sales;
import payroll.utils.Utils;

public class SalesCalc {
    public static void LaunchSales(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        Double value;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Commissioned) {
                Stack<List<Employee>> undo = stack_undo_redo.getUndo();
                Stack<List<Employee>> redo = stack_undo_redo.getRedo();
                undo.push(Utils.cloneList(list_employee));
                redo.clear();
                Commissioned empl = (Commissioned) selectedEmployee;
                List<Sales> t = Utils.cloneListSales(empl.getSales());
                empl.setSales(t);
                System.out.println("Enter sale value");
                tmp = payroll.utils.Utils.consoleReadInputDouble(tmp, sc);
                value = Double.parseDouble(tmp);
                LocalDate date = Utils.validateDate(sc);
                Sales sl = new Sales(date, value);
                empl.getSales().add(sl);
                System.out.println("sale added successfully");
            } else {
                System.out.println("Employee is not comissioned.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}
