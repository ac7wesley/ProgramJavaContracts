package application;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class ProgramContracts {

    public static void main(String[] args) throws ParseException {

        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Enter department's name: ");
        String deptName = scan.nextLine();
        System.out.println("Enter worker data: ");
        System.out.print("Name: ");
        String workerName = scan.nextLine();
        String workerLevel = "";
        do {
            System.out.println("Levels available:");
            System.err.println("1 - Junior");
            System.out.println("2 - Mid_Level");
            System.out.println("3 - Senior");
            System.out.print("Chose level: ");
            workerLevel = scan.next();
            if (workerLevel.equals("1")) {
                workerLevel = "JUNIOR";
            } else if (workerLevel.equals("2")) {
                workerLevel = "MID_LEVEL";
            } else if (workerLevel.equals("3")) {
                workerLevel = "SENIOR";
            } else {
                System.out.println("\nInvalid level! Please enter again.\n");
            }
        } while (!workerLevel.equals("JUNIOR") && !workerLevel.equals("MID_LEVEL") && !workerLevel.equals("SENIOR"));

        System.out.print("Base salary: ");
        double baseSalary = scan.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(deptName));

        System.out.print("How many contracts to this worker? ");
        int numbersContracts = scan.nextInt();

        for (int i = 1; i <= numbersContracts; i++) {
            System.out.println("Enter contract #" + i + " data: ");
            System.out.print("Date (DD/MM/YYYY): ");
            LocalDate dateContract = LocalDate.parse(scan.next(), formatter);
            System.out.print("Value per hour: ");
            double valuePerHour = scan.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = scan.nextInt();
            HourContract contract = new HourContract(dateContract, valuePerHour, hours);
            worker.addContract(contract);
        }
        System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = scan.next();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());

        System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));
        scan.close();
    }

}
