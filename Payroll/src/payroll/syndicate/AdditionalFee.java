package payroll.syndicate;

import java.time.LocalDate;

public class AdditionalFee {
    private LocalDate date;
    private Double value;

    public AdditionalFee(LocalDate date, Double value){
        this.date = date;
        this.value = value;
    }

    public AdditionalFee(AdditionalFee aF){
        this(aF.getDate(), aF.getValue());
    }
    public LocalDate getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Value: " + getValue()+"\n";
    }
}
