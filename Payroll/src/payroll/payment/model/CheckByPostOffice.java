package payroll.payment.model;

public class CheckByPostOffice extends PaymentMethod{
    private int numberCheck;
    private String address;

    public CheckByPostOffice(String bankId, String agency, String accountNumber, String paySchedule, int numberCheck, String address) {
        super(bankId, agency, accountNumber,paySchedule);
        this.numberCheck = numberCheck;
        this.address = address;
    }

    public CheckByPostOffice(CheckByPostOffice hC){
        this(hC.getBankId(), hC.getAgency(), hC.getAccountNumber(), hC.getPaySchedule(), hC.getNumberCheck(), hC.getAddress());
    }

    public int getNumberCheck() {
        return numberCheck;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType of Payment: " + getClass().getSimpleName() + "\nNumber Check: " + getNumberCheck()+ "\nAddress: " + getAddress();
    }

}
