package payroll.employee.model;

import java.time.LocalDate;

public class Timecard{
    private TimeLogin time;
    private LocalDate date;

    public Timecard(LocalDate date, TimeLogin time){
        this.date = date;
        this.time.setLogin(time.getLogin());
        this.time.setLogout(time.getLogout());
    }

    public Timecard(Timecard t){
        this( t.getDate(),t.getTime());
    }

    public TimeLogin getTime() {
        return time;
    }

    public void setTime(TimeLogin time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Login: " + getTime().toString();
    }
}
