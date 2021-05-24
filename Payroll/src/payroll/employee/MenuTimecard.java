package payroll.employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import payroll.StackUndoRedo;
import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Timecard;
import payroll.utils.Utils;

public class MenuTimecard {
    public static void Login(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Stack<List<Employee>> undo = stack_undo_redo.getUndo();
                Stack<List<Employee>> redo = stack_undo_redo.getRedo();
                undo.push(Utils.cloneList(list_employee));
                redo.clear();
                Hourly empl = (Hourly) selectedEmployee;
                List<Timecard> t = Utils.cloneListTimecard(empl.getTimecard());
                empl.setTimecard(t);
                LocalDate date = Utils.validateDate(sc);
                LocalTime loginTime = Utils.validateTime(sc);
                Timecard tc = new Timecard(date, loginTime);
                empl.getTimecard().add(tc);
                System.out.println("Successful login.");
            } else {
                System.out.println("Employee is not hourist.");
            }
        } else {
            System.out.println("Employee not found.");
        }

    }

    public static int findTimecard(List<Timecard> list, LocalDate date) {
        int i = 0;
        for (Timecard listE : list) {
            if (listE.getDate().equals(date)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void Logout(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        int index = MenuEmployee.findEmployee(list_employee);
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        if (index != -1) {
            Employee selectedEmployee = list_employee.get(index);
            if (selectedEmployee instanceof Hourly) {
                Hourly empl = (Hourly) selectedEmployee;
                LocalDate date = Utils.validateDate(sc);
                int aux = findTimecard(empl.getTimecard(), date);
                if (aux != -1) {
                    Stack<List<Employee>> undo = stack_undo_redo.getUndo();
                    Stack<List<Employee>> redo = stack_undo_redo.getRedo();
                    undo.push(Utils.cloneList(list_employee));
                    redo.clear();
                    List<Timecard> t = Utils.cloneListTimecard(empl.getTimecard());
                    empl.setTimecard(t);
                    LocalTime logoutTime = Utils.validateTime(sc);
                    Timecard tc = new Timecard(empl.getTimecard().get(aux).getDate(),
                    empl.getTimecard().get(aux).getLogin(), logoutTime);
                    empl.getTimecard().set(aux, tc);
                    System.out.println("Successful logout.");
                } else {
                    System.out.println("You need to login first.");
                }
            } else {
                System.out.println("Employee is not hourist.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}