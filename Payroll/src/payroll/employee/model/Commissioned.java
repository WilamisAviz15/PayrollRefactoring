package payroll.employee.model;

import java.util.ArrayList;
import java.util.List;

import payroll.payment.model.PaymentMethod;
import payroll.payment.model.Payslip;
import payroll.syndicate.Syndicate;

public class Commissioned extends Employee {
    private Double salary;
    private Double comission;
    private List<Sales> sales;

    public Commissioned(Commissioned s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getSalary(), s.getComission(),
                s.getSyndicate(), s.getPayslipSheet());
        this.setSales(s.getSales());
    }

    public Commissioned(int id, String name, String address, PaymentMethod paymentMethod, Double salary,
            Double comission, Syndicate sindicalist, List<Payslip> payslipSheet) {
        super(id, name, address, paymentMethod, sindicalist, payslipSheet);
        this.salary = salary;
        this.comission = comission;
        this.sales = new ArrayList<Sales>();
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getComission() {
        return comission;
    }

    public List<Sales> getSales() {
        return this.sales;
    }

    public void setSales(List<Sales> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "\nType of Employee:" + getClass().getSimpleName() + "\nSalary: " + getSalary().toString()
                + "\nComission (%): " + getComission().toString() + "\nSales: " + getSales().toString()
                + super.toString();
    }
}
