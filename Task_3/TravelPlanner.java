import java.util.InputMismatchException;
import java.util.Scanner;

public class TravelPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Travel Itinerary Planner!");

        int numDestinations = 0;
        double totalBudget = 0;
        boolean validInput = false;

        // Keep asking for input until a valid integer is provided for the number of destinations
        while (!validInput) {
            try {
                System.out.print("Enter number of destinations: ");
                numDestinations = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Consume newline
        scanner.nextLine();

        String[] destinations = new String[numDestinations];
        for (int i = 0; i < numDestinations; i++) {
            System.out.print("Enter destination " + (i + 1) + ": ");
            destinations[i] = scanner.nextLine();
        }

        // Prompt the user to enter their total budget
        validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your total budget: $");
                totalBudget = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }

        // Generate travel plan
        generateTravelPlan(destinations);

        // Additional features
        // Weather information
        getWeatherInformation(destinations);

        // Budget calculations
        calculateBudget(destinations, totalBudget);

        // Close the scanner
        scanner.close();
    }

    private static void generateTravelPlan(String[] destinations) {
        System.out.println("\nYour travel plan:");

        for (int i = 0; i < destinations.length; i++) {
            System.out.println((i + 1) + ". " + destinations[i]);
        }
    }

    private static void getWeatherInformation(String[] destinations) {
        // Placeholder method for fetching weather information for each destination
        System.out.println("\nWeather information:");
        for (String destination : destinations) {
            System.out.println("Weather in " + destination + ": Sunny");
        }
    }

    private static void calculateBudget(String[] destinations, double totalBudget) {
        // Placeholder method for calculating budget
        System.out.println("\nBudget calculations:");
        // Add budget calculation logic here
        System.out.println("Total budget: $" + totalBudget);
        double averageBudgetPerDestination = totalBudget / destinations.length;
        System.out.println("Average budget per destination: $" + averageBudgetPerDestination);
    }
}
