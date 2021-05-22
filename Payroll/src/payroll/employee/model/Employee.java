package payroll.employee.model;

import java.util.ArrayList;
import java.util.List;

import payroll.payment.model.PaymentMethod;
import payroll.payment.model.Payslip;
import payroll.syndicate.Syndicate;

public class Employee {
    private int id;
    private String name;
    private String address;
    private PaymentMethod paymentMethod;
    private Syndicate sindicalist;
    private List<Payslip> payslipSheet;

    public Employee() {
    }

    public Employee(Employee s) {
        this(s.getId(), s.getName(), s.getAddress(), s.getPaymentMethod(), s.getSyndicate(), s.getPayslipSheet());
    }

    public Employee(int id, String name, String address, PaymentMethod paymentMethod, Syndicate sindicalist, List<Payslip> payslipSheet) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.sindicalist = sindicalist;
        this.payslipSheet = payslipSheet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Syndicate getSyndicate() {
        return this.sindicalist;
    }

    public void setSyndicate(Syndicate sindicalist) {
        this.sindicalist = sindicalist;
    }

    public List<Payslip> getPayslipSheet() {
        return payslipSheet;
    }

    public void setPayslipSheet(List<Payslip> payslipSheet) {
        this.payslipSheet = payslipSheet;
    }
    
    private String infoSyndicateConcat(){
        if (getSyndicate().getActive() == true) {
            return getSyndicate().toString();
        } else {
            return "\n--Syndicate info--\n Does not belong to syndicate.";
        }
    }
    
    @Override
    public String toString() {
        return "\nEmployee ID: " + getId() + "\nName: " + getName() + "\nAddress: " + getAddress()
                + "\n--Info Payment-- \n" + getPaymentMethod() + infoSyndicateConcat();
    }
}
