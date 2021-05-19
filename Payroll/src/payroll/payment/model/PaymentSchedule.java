package payroll.payment.model;

import java.util.ArrayList;
import java.util.List;

public class PaymentSchedule{

    private List<String> typesSchedule;

    public PaymentSchedule() {
        this.typesSchedule = new ArrayList<String>();
        this.typesSchedule.add("weekly 1 Friday");
        this.typesSchedule.add("monthly $");
        this.typesSchedule.add("weekly 2 Friday");
    }

    public PaymentSchedule(List<String> typesSchedule){
        this.typesSchedule = typesSchedule;
    }

    public PaymentSchedule(PaymentSchedule pS){
        this(pS.getTypesSchedule());
    }

    public List<String> getTypesSchedule() {
        return this.typesSchedule;
    }

    public void setTypesSchedule(String typesSchedule) {
        this.typesSchedule.add(typesSchedule);
    }

    public void print(){
        for (String pS : typesSchedule) {
            System.out.println(" - " + pS);
        }
    }
}
