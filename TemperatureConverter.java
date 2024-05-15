import java.util.Scanner;
public class TemperatureConverter{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.println("Length Converter");
        System.out.println("Enter the length value");
        double length=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the original unit of measurement ");
        String originalUnit=sc.nextLine().toLowerCase();
        double lengthInMeters;
        switch (originalUnit) {
            case "meters":
                lengthInMeters = length;
                break;
            case "feet":
                lengthInMeters = feetToMeters(length);
                break;
            case "kilometers":
                lengthInMeters = kilometersToMeters(length);
                break;
            default:
                System.out.println("Invalid unit of measurement!");
                return;
        }

        // Convert meters to feet and kilometers
        double lengthInFeet = metersToFeet(lengthInMeters);
        double lengthInKilometers = metersToKilometers(lengthInMeters);

        // Display results
        System.out.println("Converted lengths:");
        System.out.println("Length in feet: " + lengthInFeet);
        System.out.println("Length in kilometers: " + lengthInKilometers);
    }

    public static double feetToMeters(double feet) {
        return feet * 0.3048;
    }

    public static double metersToFeet(double meters) {
        return meters / 0.3048;
    }

    public static double kilometersToMeters(double kilometers) {
        return kilometers * 1000;
    }

    public static double metersToKilometers(double meters) {
        return meters / 1000;
    }
}
