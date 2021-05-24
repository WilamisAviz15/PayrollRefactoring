package payroll.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import payroll.SalesCalc;
import payroll.ServiceFeeCalc;
import payroll.StackUndoRedo;
import payroll.employee.MenuEmployee;
import payroll.employee.MenuTimecard;
import payroll.employee.model.Employee;
import payroll.payment.MenuPayoutSchedule;
import payroll.payment.MenuPayroll;
import payroll.payment.model.PaymentSchedule;
import payroll.payment.model.Payslip;
import payroll.utils.Utils;

public class Commands {

    public Commands() {
    }

    public List<Employee> list_employee = new ArrayList<Employee>();
    PaymentSchedule paySchedules = new PaymentSchedule();
    public List<Payslip> payslipSheet = new ArrayList<Payslip>();
    public static int idSyndicateInt = 0;

    Stack<List<Employee>> undo = new Stack<>();
    Stack<List<Employee>> redo = new Stack<>();
    StackUndoRedo stack_undo_redo = new StackUndoRedo(undo, redo);

    public void createEmployee() {
        list_employee.add(MenuEmployee.addEmployee(paySchedules, list_employee, stack_undo_redo, payslipSheet));
    }

    public void ListAllEmployee() {
        MenuEmployee.ListAllEmployee(list_employee);
    }

    public void ListEmployeeById() {
        MenuEmployee.listEmployeeById(list_employee);
    }

    public void RemoveEmployee(){
        MenuEmployee.removeEmployee(list_employee, stack_undo_redo);
    }

    public void EditEmployee(){
        MenuEmployee.editEmployee(list_employee, paySchedules, stack_undo_redo, payslipSheet);
    }

    public void AddTimecardLogin(){
        MenuTimecard.Login(list_employee, stack_undo_redo);
    }

    public void AddTimecardLogout(){
        MenuTimecard.Logout(list_employee, stack_undo_redo);
    }

    public void AddSales(){
        SalesCalc.LaunchSales(list_employee, stack_undo_redo);
    }

    public void AddServiceFee(){
        ServiceFeeCalc.LaunchFee(list_employee, stack_undo_redo);
    }

    public void RotateP(){
        MenuPayroll.Menu(list_employee, stack_undo_redo);
    }

    public void Schedules(){
        MenuPayoutSchedule.Menu(paySchedules);
    }

    public void Undo() {
        if (undo.size() > 0) {
            Stack<List<Employee>> undo = stack_undo_redo.getUndo();
            Stack<List<Employee>> redo = stack_undo_redo.getRedo();
            List<Employee> aux = undo.pop();
            redo.push(Utils.cloneList(list_employee));
            list_employee = aux;
            System.out.println("Changes undone successfully.");
        } else {
            System.out.println("There are nothing to undo.");
        }
    }

    public void Redo() {
        if (redo.size() > 0) {
            Stack<List<Employee>> undo = stack_undo_redo.getUndo();
            Stack<List<Employee>> redo = stack_undo_redo.getRedo();
            List<Employee> aux = redo.pop();
            undo.push(Utils.cloneList(list_employee));
            list_employee = aux;
            System.out.println("Changes redone successfully.");
        } else {
            System.out.println("There are nothing to redo.");
        }
    }

    public void Exit(){
        System.exit(0);
    }
}
