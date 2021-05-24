package payroll.commands.editEmployee;

import java.util.List;
import java.util.Scanner;

import payroll.employee.MenuEmployee;
import payroll.employee.model.Employee;
import payroll.payment.model.PaymentSchedule;

public class EditEmployeeCommands {
    public EditEmployeeCommands(){}
    String attr;
    Scanner sc = new Scanner(System.in);

    public void AlterName(Employee emp){
        MenuEmployee.Rename(attr, sc, emp);
    }

    public void AlterAddr(Employee emp){
        MenuEmployee.EditAddress(attr, sc, emp);
    }

    public void AlterTypeEmployee(Employee emp, List<Employee> list_employee, PaymentSchedule paySchedules){
        MenuEmployee.EditTypeEmployee(attr, sc, emp, list_employee, paySchedules);
    }

    public void AlterPaymentMethod(Employee emp,PaymentSchedule paySchedules){
        MenuEmployee.EditPaymentMethod(attr, sc, emp, paySchedules);
    }

    public void AlterJoinOrLeftSyndicate(Employee emp){
        MenuEmployee.JoinOrLeftSyndicate(sc, emp);
    }

    public void AlterMonthlySyndicateFee(Employee emp){
        MenuEmployee.EditMonthlySyndicateFee(sc, emp);
    }

    public void AlterSyndicalIdentification(Employee emp, List<Employee> list_employee){
        MenuEmployee.EditSyndicalIdentification(sc, list_employee, emp);
    }

    public void AlterPaymentSchedule(Employee emp,PaymentSchedule paySchedules){
        MenuEmployee.EditPaymentSchedule(sc, emp, paySchedules);
    }
}
