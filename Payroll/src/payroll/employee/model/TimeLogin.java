package payroll.employee.model;

import java.time.LocalTime;

public class TimeLogin {
    private LocalTime login, logout;

    public TimeLogin(LocalTime login, LocalTime logout){
        this.login = login;
        this.logout = logout;
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

    @Override
    public String toString() {
        return "Login: " + getLogin() +" | "+ "Logout: " + getLogout() + "\n";
    }

}
