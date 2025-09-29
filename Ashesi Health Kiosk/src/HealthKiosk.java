//Adansie Boahene- 94312026
//OOP LAB 2

import java.util.Scanner;
import java.util.Random;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        // Task 0: Welcome
        System.out.println("Welcome to the Ashesi Health Kiosk");

        // Task 1: Service router
        System.out.print("Enter service code (P/L/T/C): ");
        char code = input.next().charAt(0);

        String service = "";
        switch (code) {
            case 'P':
            case 'p':
                service = "PHARMACY";
                System.out.println("Go to: Pharmacy Desk");
                break;
            case 'L':
            case 'l':
                service = "LAB";
                System.out.println("Go to: Lab Desk");
                break;
            case 'T':
            case 't':
                service = "TRIAGE";
                System.out.println("Go to: Triage Desk");
                break;
            case 'C':
            case 'c':
                service = "COUNSELING";
                System.out.println("Go to: Counseling Desk");
                break;
            default:
                System.out.println("Invalid service code");
                return; // stop program if wrong input
        }

        // Task 2: Health metrics
        System.out.println("\nEnter the health metric: ");
        System.out.println("1 for BMI, 2 for Dosage round-up, 3 for Simple trig helper");
        int option = input.nextInt();

        double metricValue = 0; // to store result for later use
        String metricInfo = ""; // to print in summary

        switch (option) {
            case 1: // BMI
                System.out.print("Enter your weight (kg): ");
                double weight = input.nextDouble();
                System.out.print("Enter your height (m): ");
                double height = input.nextDouble();

                double bmi = weight / Math.pow(height, 2);
                bmi = Math.round(bmi * 10) / 10.0;

                System.out.print("BMI: " + bmi);
                if (bmi < 18.5) {
                    System.out.println("  Category: Underweight");
                } else if (bmi <= 24.9) {
                    System.out.println("  Category: Normal");
                } else if (bmi <= 29.9) {
                    System.out.println("  Category: Overweight");
                } else {
                    System.out.println("  Category: Obese");
                }

                metricValue = bmi;
                metricInfo = "BMI=" + bmi;
                break;

            case 2: // Dosage round-up
                System.out.print("Enter the required dosage (mg): ");
                double dosage = input.nextDouble();

                int requiredTab = (int) Math.ceil(dosage / 250.0);
                System.out.println("Take " + requiredTab + " tablets");

                metricValue = requiredTab;
                metricInfo = "Dosage=" + requiredTab + " tabs";
                break;

            case 3: // Trig helper
                System.out.print("Enter an angle (degrees): ");
                double angle = input.nextDouble();

                double radians = Math.toRadians(angle);
                double angleSin = Math.round(Math.sin(radians) * 1000) / 1000.0;
                double angleCos = Math.round(Math.cos(radians) * 1000) / 1000.0;

                System.out.println("The sin of " + angle + "° is: " + angleSin);
                System.out.println("The cos of " + angle + "° is: " + angleCos);

                metricValue = Math.round(Math.sin(radians) * 100); // for code later
                metricInfo = "Trig angle=" + angle;
                break;

            default:
                System.out.println("Invalid metric choice");
                return;
        }

        // Task 3: Random ID
        char randomChar = (char) ('A' + rand.nextInt(26));
        String id = "" + randomChar;
        for (int i = 0; i < 4; i++) {
            int digit = rand.nextInt(7) + 3; // 3–9
            id += digit;
        }
        System.out.println("\nGenerated ID: " + id);

        // Validate ID
        if (id.length() != 5) {
            System.out.println("Invalid length");
            return;
        } else if (!Character.isLetter(id.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
            return;
        } else if (!Character.isDigit(id.charAt(1)) ||
                   !Character.isDigit(id.charAt(2)) ||
                   !Character.isDigit(id.charAt(3)) ||
                   !Character.isDigit(id.charAt(4))) {
            System.out.println("Invalid: last 4 must be digits");
            return;
        } else {
            System.out.println("ID OK");
        }

        // Task 4: Secure Display Code
        System.out.print("\nEnter your first name: ");
        String firstName = input.next();

        char baseChar = Character.toUpperCase(firstName.charAt(0));
        char shiftedChar = (char) ('A' + (baseChar - 'A' + 2) % 26);

        String lastTwo = id.substring(3); // last 2 digits
        int roundedMetric = (int) Math.round(metricValue);

        String displayCode = shiftedChar + lastTwo + "-" + roundedMetric;
        System.out.println("Display Code: " + displayCode);

        // Task 5: Service Summary
        System.out.print("\nSummary: " + service + " | ID=" + id + " | ");
        if (service.equals("TRIAGE")) {
            System.out.print(metricInfo + " | ");
        }
        System.out.println("Code=" + displayCode);
    }
}




