package payroll.employee.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timecard{
    private LocalDate date;
    private LocalTime login;
    private LocalTime logout;

    public Timecard(LocalDate date, LocalTime login){
        this.date = date;
        this.login= login;
        //this.time.setLogout(time.getLogout());
    }

    public Timecard(LocalDate date, LocalTime login, LocalTime logout){
        this.date = date;
        this.login= login;
        this.logout = logout;
    }

    public Timecard(Timecard t){
        this( t.getDate(),t.getLogin(), t.getLogout());
    }

    public LocalTime getLogin() {
        return login;
    }

    public void setLogin(LocalTime login) {
        this.login = login;
    }

    public LocalTime getLogout() {
        return logout;
    }

    public void setLogout(LocalTime logout) {
        this.logout = logout;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date: " + getDate() +" | "+ "Login: " + getLogin() +" | "+ "Logout: " + getLogout() + "\n";
    }
}
