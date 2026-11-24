
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A menu-driven application that stores country information in a custom
 * hash table using separate chaining.
 *
 * Supports loading countries from a CSV file, insertion, deletion,
 * searching, population density calculations, and collision analysis.
 *
 * @author Keith Brandenburg
 */
public class countryHashTable {

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            HashTable table = new HashTable();

            System.out.print("Enter the file name: ");
            String fileName = input.nextLine().trim();

            int countryCount;

            try {
                countryCount = loadCountries(fileName, table);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
                return;
            }

            System.out.println("\nThere were " + countryCount
                    + " country records read into the hash table.");

            while (true) {
                displayMenu();
                String choice = input.nextLine().trim();

                switch (choice) {
                    case "1":
                        table.display();
                        break;

                    case "2":
                        System.out.print("Enter country name: ");
                        String nameToDelete = input.nextLine().trim();
                        table.delete(nameToDelete);
                        break;

                    case "3":
                        insertCountry(input, table);
                        break;

                    case "4":
                        System.out.print("Enter country name: ");
                        String nameToSearch = input.nextLine().trim();

                        int index = table.find(nameToSearch);

                        if (index != -1) {
                            double density =
                                    table.getPopulationDensity(nameToSearch);

                            System.out.printf(
                                    "%s is found at index %d with "
                                    + "population density of %.2f%n",
                                    nameToSearch, index, density);
                        } else {
                            System.out.println(nameToSearch
                                    + " was not found in the hash table.");
                        }
                        break;

                    case "5":
                        table.printEmptyAndCollidedCells();
                        break;

                    case "6":
                        System.out.println("\nHave a good day!");
                        return;

                    default:
                        System.out.println("Invalid choice. Enter 1-6.");
                }
            }
        }
    }

    /**
     * Loads country records from a CSV file into the hash table.
     * Malformed or invalid records are skipped.
     *
     * @return the number of successfully loaded countries
     */
    private static int loadCountries(String fileName, HashTable table)
            throws IOException {

        int countryCount = 0;

        try (BufferedReader br =
                     new BufferedReader(new FileReader(fileName))) {

            // Skip the CSV header.
            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",", -1);

                if (data.length < 6) {
                    System.out.println("Skipping malformed record: " + line);
                    continue;
                }

                try {
                    String name = data[0].trim();
                    long population = Long.parseLong(data[2].trim());
                    long area = Long.parseLong(data[4].trim());

                    table.insert(name, population, area);
                    countryCount++;

                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }
        }

        return countryCount;
    }

    /**
     * Prompts the user for country information and inserts a new record.
     */
    private static void insertCountry(Scanner input, HashTable table) {
        try {
            System.out.print("Enter country name: ");
            String name = input.nextLine().trim();

            System.out.print("Enter country population: ");
            long population = Long.parseLong(input.nextLine().trim());

            System.out.print("Enter country area: ");
            long area = Long.parseLong(input.nextLine().trim());

            table.insert(name, population, area);

            System.out.println(name + " was inserted into the hash table.");

        } catch (NumberFormatException e) {
            System.out.println("Population and area must be valid integers.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays the available hash table operations.
     */
    private static void displayMenu() {
        System.out.println("\n1) Print hash table");
        System.out.println("2) Delete a country");
        System.out.println("3) Insert a country");
        System.out.println("4) Search for a country and its population density");
        System.out.println("5) Print numbers of empty and collided cells");
        System.out.println("6) Exit");
        System.out.print("Enter your choice: ");
    }
}
