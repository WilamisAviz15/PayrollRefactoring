package payroll.payment.model;

public class PaymentMethod {
    private String bankId;
    private String agency;
    private String accountNumber;
    private String paySchedule;

    public PaymentMethod(PaymentMethod pM){
        this(pM.getBankId(), pM.getAgency(), pM.getAccountNumber(), pM.getPaySchedule());
    }
    
    public PaymentMethod(String bankId, String agency, String accountNumber, String paySchedule) {
        this.bankId = bankId;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.paySchedule = paySchedule;
    }

    public String getBankId() {
        return bankId;
    }

    public String getPaySchedule() {
        return paySchedule;
    }

    public void setPaySchedule(String paySchedule) {
        this.paySchedule = paySchedule;
    }

    public String getAgency() {
        return agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "BankId: " + getBankId() + "\nAgency: " + getAgency() + "\nAccount Number: " + getAccountNumber() + "\nPayment Schedule: " + getPaySchedule();
    }
}
