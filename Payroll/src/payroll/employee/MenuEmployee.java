package payroll.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import payroll.IMenu;
import payroll.Menu;
import payroll.StackUndoRedo;
import payroll.commands.editEmployee.EditAddr;
import payroll.commands.editEmployee.EditEmployeeCommands;
import payroll.commands.editEmployee.EditMonthlySyndicateFee;
import payroll.commands.editEmployee.EditName;
import payroll.commands.editEmployee.EditPaymentMethod;
import payroll.commands.editEmployee.EditPaymentSchedule;
import payroll.commands.editEmployee.EditSyndicalIdentification;
import payroll.commands.editEmployee.EditTypeEmployee;
import payroll.commands.editEmployee.JoinOrLeftSyndicate;
import payroll.employee.model.Commissioned;
import payroll.employee.model.Employee;
import payroll.employee.model.Hourly;
import payroll.employee.model.Salaried;
import payroll.payment.model.CheckByPostOffice;
import payroll.payment.model.DepositByBankAccount;
import payroll.payment.model.HandsCheck;
import payroll.payment.model.PaymentMethod;
import payroll.payment.model.PaymentSchedule;
import payroll.payment.model.Payslip;
import payroll.syndicate.Syndicate;
import payroll.utils.Utils;

public class MenuEmployee {
    public static int id = 20210000, idx; // será incrementado a cada novo funcionario;
    public static int idSyndicateInt = 0;
    private static String[] accountType = { "Corrente", "Poupança", "Fácil", "Conjunta" };

    public static Employee addEmployee(PaymentSchedule paySchedule, List<Employee> lEmployees,
            StackUndoRedo stack_undo_redo, List<Payslip> payslipSheet) {
        Stack<List<Employee>> undo = stack_undo_redo.getUndo();
        Stack<List<Employee>> redo = stack_undo_redo.getRedo();
        undo.push(Utils.cloneList(lEmployees));
        redo.clear();
        PaymentMethod paymentMethod = null;
        Syndicate sindicalist = null;
        Employee newEmployees;
        String bankId, agency, accountNumber, tmp = "", payScheduleString = "";
        Double salary = 0.00;
        Scanner sc = new Scanner(System.in);
        int optionPaymentMethod, optionSindicalist;
        System.out.println("Type the name:");
        String name = sc.nextLine();
        System.out.println("Type the address:");
        String address = sc.nextLine();
        System.out.println("Type of employee (0 - hourly, 1 - salaried, 2 - commissioned)");
        tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 3);
        int type_employee = Integer.parseInt(tmp);
        payScheduleString = paySchedule.getTypesSchedule().get(type_employee);
        if (type_employee == 0) {
            System.out.println("Type the hourly wage:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            newEmployees = new Hourly(++id, name, address, paymentMethod, salary, sindicalist, payslipSheet);
        } else if (type_employee == 1) {
            System.out.println("Type the salary:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            newEmployees = new Salaried(++id, name, address, paymentMethod, salary, sindicalist, payslipSheet);
        } else if (type_employee == 2) {
            double percentage;
            System.out.println("Type the salary:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            System.out.println("Type the % to comisson:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            percentage = Double.parseDouble(tmp);
            newEmployees = new Commissioned(++id, name, address, paymentMethod, salary, percentage, sindicalist,
                    payslipSheet);
        } else {
            newEmployees = new Employee(++id, name, address, paymentMethod, sindicalist, payslipSheet);
        }
        System.out.println("Type Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
        tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 3);
        optionPaymentMethod = Integer.parseInt(tmp);
        System.out.println("Type the bank ID:");
        bankId = sc.nextLine();
        System.out.println("Type agency number:");
        agency = sc.nextLine();
        System.out.println("Type account number:");
        accountNumber = sc.nextLine();
        if (optionPaymentMethod == 0) {
            int numberCheck;
            System.out.println("Type number Check:");
            tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
            numberCheck = Integer.parseInt(tmp);
            paymentMethod = new CheckByPostOffice(bankId, agency, accountNumber, payScheduleString, numberCheck,
                    address);
        } else if (optionPaymentMethod == 1) {
            int numberCheck;
            System.out.println("Type number Check:");
            tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
            numberCheck = Integer.parseInt(tmp);
            paymentMethod = new HandsCheck(bankId, agency, accountNumber, payScheduleString, numberCheck);
        } else if (optionPaymentMethod == 2) {
            int index;
            System.out.println("Select type of account:");
            System.out.println("0 - " + accountType[0]);
            System.out.println("1 - " + accountType[1]);
            System.out.println("2 - " + accountType[2]);
            System.out.println("3 - " + accountType[3]);
            tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 4);
            index = Integer.parseInt(tmp);
            paymentMethod = new DepositByBankAccount(bankId, agency, accountNumber, payScheduleString,
                    accountType[index]);
        }
        newEmployees.setPaymentMethod(paymentMethod);

        System.out.printf("%s will be part of the Syndicate? 1- yes | 2- no\n", newEmployees.getName());
        tmp = Utils.consoleReadInputIntegerWithOR(tmp, sc, 1, 2);
        optionSindicalist = Integer.parseInt(tmp);
        if (optionSindicalist == 1) {
            Double tax;
            System.out.println("Type the monthly TAX:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            tax = Double.parseDouble(tmp);
            String aux = Character.toString(name.charAt(0));
            sindicalist = new Syndicate(aux + 2021 + "S000" + (++idSyndicateInt), tax, true);
            newEmployees.setSyndicate(sindicalist);
        } else {
            sindicalist = new Syndicate("0", 0.00, false);
            newEmployees.setSyndicate(sindicalist);
        }
        System.out.println("Successful registration.");
        System.out.println("ID employee is: \n" + id);
        return newEmployees;
    }

    public static void removeEmployee(List<Employee> list_employee, StackUndoRedo stack_undo_redo) {
        int index = findEmployee(list_employee);
        if (index != -1) {
            Stack<List<Employee>> undo = stack_undo_redo.getUndo();
            Stack<List<Employee>> redo = stack_undo_redo.getRedo();
            undo.push(Utils.cloneList(list_employee));
            redo.clear();
            list_employee.remove(index);
            System.out.println("Employee removed.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static int findEmployee(List<Employee> list_employee) {
        int id;
        int aux = 0;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
        id = Integer.parseInt(tmp);
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                return aux;
            }
            aux++;
        }
        return -1;
    }

    public static void ListAllEmployee(List<Employee> list_employee) {
        System.out.println("___________________________________________");
        for (Employee listE : list_employee) {
            System.out.println(listE);
            System.out.println("___________________________________________");
        }
    }

    public static void listEmployeeById(List<Employee> list_employee) {
        int id;
        String tmp = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type the ID:");
        tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
        id = Integer.parseInt(tmp);
        System.out.println("___________________________");
        for (Employee listE : list_employee) {
            if (listE.getId() == id) {
                System.out.println(listE);
            }
        }
        System.out.println("___________________________");
    }

    public static boolean findEmployeeSyndicate(List<Employee> list_employee, String renameSyndicalId) {
        for (Employee listE : list_employee) {
            if (listE.getSyndicate().getIdSyndicate().equals(renameSyndicalId)) {
                return true;
            }
        }
        return false;
    }

    public static void Rename(String attr, Scanner sc, Employee selectedEmployee) {
        System.out.println("Type the new name");
        attr = sc.nextLine();
        selectedEmployee.setName(attr);
        System.out.println("Successful changes.");
    }

    public static void EditAddress(String attr, Scanner sc, Employee selectedEmployee) {
        System.out.println("Type the new address");
        attr = sc.nextLine();
        selectedEmployee.setAddress(attr);
        System.out.println("Successful changes.");
    }

    public static void EditTypeEmployee(String attr, Scanner sc, Employee selectedEmployee,
            List<Employee> list_employee, PaymentSchedule paySchedule) {
        String payScheduleString = "", tmp = "";
        Double salary;
        System.out.println("Type the new type of employee: (0 - hourly, 1 - salaried, 2 - commissioned)");
        attr = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 3);
        PaymentMethod pM = Utils.cloneListPaymentMethod(selectedEmployee.getPaymentMethod());
        payScheduleString = paySchedule.getTypesSchedule().get(Integer.parseInt(attr));
        selectedEmployee.setPaymentMethod(pM);
        selectedEmployee.getPaymentMethod().setPaySchedule(payScheduleString);
        if (attr.equals("0")) {
            System.out.println("Type the hourly wage:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            selectedEmployee = new Hourly(selectedEmployee.getId(), selectedEmployee.getName(),
                    selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary,
                    selectedEmployee.getSyndicate(), selectedEmployee.getPayslipSheet());
        } else if (attr.equals("1")) {
            System.out.println("Type the salary:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            selectedEmployee = new Salaried(selectedEmployee.getId(), selectedEmployee.getName(),
                    selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary,
                    selectedEmployee.getSyndicate(), selectedEmployee.getPayslipSheet());
        } else if (attr.equals("2")) {
            Double percentage;
            System.out.println("Type the salary:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            salary = Double.parseDouble(tmp);
            System.out.println("Type the % to comisson:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            percentage = Double.parseDouble(tmp);
            selectedEmployee = new Commissioned(selectedEmployee.getId(), selectedEmployee.getName(),
                    selectedEmployee.getAddress(), selectedEmployee.getPaymentMethod(), salary, percentage,
                    selectedEmployee.getSyndicate(), selectedEmployee.getPayslipSheet());
        }
        list_employee.set(idx, selectedEmployee);
        System.out.println("Successful changes.");
    }

    public static void EditPaymentMethod(String attr, Scanner sc, Employee selectedEmployee,
            PaymentSchedule paySchedule) {
        String tmp = "";
        String bankId;
        String agency;
        String accountNumber;
        String payScheduleString = "";
        System.out.println(
                "Select the new Payment Method (0 - Check by the post office, 1 - Check in Person, 2 - Bank Account)");
        attr = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 3);
        System.out.println("Type the bank ID:");
        bankId = sc.nextLine();
        System.out.println("Type agency number:");
        agency = sc.nextLine();
        System.out.println("Type account number:");
        accountNumber = sc.nextLine();
        System.out.println("Do you want to alter payment schedule?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        tmp = Utils.consoleReadInputIntegerWithOR(tmp, sc, 1, 2);
        int answer = Integer.parseInt(tmp);
        if (answer == 1) {
            int i = 0;
            System.out.println("Select one:");
            for (String pS : paySchedule.getTypesSchedule()) {
                System.out.println(i++ + " - " + pS);
            }
            tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, i);
            payScheduleString = paySchedule.getTypesSchedule().get(Integer.parseInt(tmp));
        } else {
            payScheduleString = selectedEmployee.getPaymentMethod().getPaySchedule();
        }
        if (attr.equals("0")) {
            int numberCheck;
            System.out.println("Type number Check:");
            tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
            numberCheck = Integer.parseInt(tmp);
            selectedEmployee.setPaymentMethod(new CheckByPostOffice(bankId, agency, accountNumber, payScheduleString,
                    numberCheck, selectedEmployee.getAddress()));
        } else if (attr.equals("1")) {
            int numberCheck;
            System.out.println("Type number Check:");
            tmp = Utils.consoleReadInputIntegerNumber(tmp, sc);
            numberCheck = Integer.parseInt(tmp);
            selectedEmployee
                    .setPaymentMethod(new HandsCheck(bankId, agency, accountNumber, payScheduleString, numberCheck));
        } else if (attr.equals("2")) {
            int idx;
            System.out.println("Select type of account:");
            System.out.println("0 - " + accountType[0]);
            System.out.println("1 - " + accountType[1]);
            System.out.println("2 - " + accountType[2]);
            System.out.println("3 - " + accountType[3]);
            tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 4);
            idx = Integer.parseInt(tmp);
            selectedEmployee.setPaymentMethod(
                    new DepositByBankAccount(bankId, agency, accountNumber, payScheduleString, accountType[idx]));
        }
        System.out.println("Successful changes.");
    }

    public static void JoinOrLeftSyndicate(Scanner sc, Employee selectedEmployee) {
        int answer;
        String tmp = "";
        if (selectedEmployee.getSyndicate().getActive() == true) {
            System.out.println(selectedEmployee.getName() + " already belongs to syndicate. Do you want to leave?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            tmp = Utils.consoleReadInputIntegerWithOR(tmp, sc, 1, 2);
            answer = Integer.parseInt(tmp);
            if (answer == 1) {
                Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
                selectedEmployee.setSyndicate(s);
                selectedEmployee.getSyndicate().setActive(false);
                System.out.println("Successful changes.");
            }
        } else {
            System.out.println(
                    selectedEmployee.getName() + " does not belongs to syndicate or is inactive. Do you want to join?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            tmp = Utils.consoleReadInputIntegerWithOR(tmp, sc, 1, 2);
            answer = Integer.parseInt(tmp);
            if (answer == 1) {
                Double tax;
                System.out.println("Type the monthly TAX:");
                tmp = Utils.consoleReadInputDouble(tmp, sc);
                tax = Double.parseDouble(tmp);
                String aux = Character.toString(selectedEmployee.getName().charAt(0));
                Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
                selectedEmployee.setSyndicate(s);
                selectedEmployee.getSyndicate().setIdSyndicate(aux + 2021 + "S000" + (++idSyndicateInt));
                selectedEmployee.getSyndicate().setTax(tax);
                selectedEmployee.getSyndicate().setActive(true);
                System.out.println("Successful changes.");
            }
        }
    }

    public static void EditMonthlySyndicateFee(Scanner sc, Employee selectedEmployee) {
        String tmp = "";
        if (selectedEmployee.getSyndicate().getActive() == true) {
            Double tax;
            System.out.println("Type the new monthly TAX:");
            tmp = Utils.consoleReadInputDouble(tmp, sc);
            tax = Double.parseDouble(tmp);
            Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
            selectedEmployee.setSyndicate(s);
            selectedEmployee.getSyndicate().setTax(tax);
            System.out.println("Successful changes.");
        } else {
            System.out.println(selectedEmployee.getName() + " does not belongs to syndicate to edit fee");
            ;
        }
    }

    public static void EditSyndicalIdentification(Scanner sc, List<Employee> list_employee, Employee selectedEmployee) {
        if (selectedEmployee.getSyndicate().getActive() == true) {
            String nameID;
            System.out.println("Enter the new Identification");
            nameID = sc.nextLine();
            if (!findEmployeeSyndicate(list_employee, nameID)) {
                Syndicate s = Utils.cloneListSyndicate(selectedEmployee.getSyndicate());
                selectedEmployee.setSyndicate(s);
                selectedEmployee.getSyndicate().setIdSyndicate(nameID);
                System.out.println("Successful changes.");
            } else {
                System.out.println("This name already exists. Please choose another one.");
            }
        } else {
            System.out.println(selectedEmployee.getName() + " does not belongs to syndicate to edit ID");
            ;
        }
    }

    public static void EditPaymentSchedule(Scanner sc, Employee selectedEmployee, PaymentSchedule paySchedule) {
        int i = 0;
        String payScheduleString = "", tmp = "";
        System.out.println("Select the new payment schedule:");
        for (String pS : paySchedule.getTypesSchedule()) {
            System.out.println(i++ + " - " + pS);
        }
        tmp = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, i);
        PaymentMethod pM = Utils.cloneListPaymentMethod(selectedEmployee.getPaymentMethod());
        payScheduleString = paySchedule.getTypesSchedule().get(Integer.parseInt(tmp));
        selectedEmployee.setPaymentMethod(pM);
        selectedEmployee.getPaymentMethod().setPaySchedule(payScheduleString);
        System.out.println("Successful changes.");
    }

    public static void editEmployee(List<Employee> list_employee, PaymentSchedule paySchedule,
            StackUndoRedo stack_undo_redo, List<Payslip> payslipSheet) {
        String op, attr = "";
        Double salary = 0.00;
        String tmp = "", payScheduleString = "";
        Scanner sc = new Scanner(System.in);
        int index = findEmployee(list_employee);
        idx = index;
        if (index != -1) {
            Stack<List<Employee>> undo = stack_undo_redo.getUndo();
            Stack<List<Employee>> redo = stack_undo_redo.getRedo();
            undo.push(Utils.cloneList(list_employee));
            redo.clear();
            Employee selectedEmployee = list_employee.get(index);
            System.out.printf("Employed selected: %s. What do you want to edit?\n", selectedEmployee.getName());
            Menu menu = new Menu();
            EditEmployeeCommands commands = new EditEmployeeCommands();
            ArrayList<IMenu> optionsMenu = new ArrayList<IMenu>();
            optionsMenu.add(new EditName(selectedEmployee, commands));
            optionsMenu.add(new EditAddr(selectedEmployee, commands));
            optionsMenu.add(new EditTypeEmployee(selectedEmployee, commands, list_employee, paySchedule));
            optionsMenu.add(new EditPaymentMethod(selectedEmployee, commands, paySchedule));
            optionsMenu.add(new JoinOrLeftSyndicate(selectedEmployee, commands));
            optionsMenu.add(new EditMonthlySyndicateFee(selectedEmployee, commands));
            optionsMenu.add(new EditSyndicalIdentification(selectedEmployee, list_employee, commands));
            optionsMenu.add(new EditPaymentSchedule(selectedEmployee, commands, paySchedule));
            while (true) {
                System.out.println("Choose one:\n\n" + "[0]  Name\n" + "[1]  Address\n" + "[2]  Type of Employee\n"
                        + "[3]  Payment Method\n" + "[4]  Join/leave syndicate\n" + "[5]  Monthly Syndicate Fee\n"
                        + "[6]  Syndical Identification\n" + "[7]  Payment Schedule\n" + "[8]  Back\n");
                op = Utils.consoleReadInputIntegerOptions(tmp, sc, 0, 9);
                int opc = Integer.parseInt(op);
                if (op.equals("8")) {
                    break;
                }
                menu.setCommand(optionsMenu.get(opc));
                menu.executeComand();
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}