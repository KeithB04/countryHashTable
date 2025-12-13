import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * 
 * This class prompts the user for a file then creates a hash table using separate chaining using double ended 
 * singly linked list. Then the program will prompt the user with a menu that offers the ability to print the 
 * hash table with the names and population density of each country, delete, insert and search a country from the 
 * hash table. The insert method takes in area along with the countries name and population. Then there is a method 
 * to print the number of empty and collided cells, where collided cells are cells with multiple countries.
 *
 * @author Keith Brandenburg
 * @version 24 April 2025
 */


public class countryHashTable {
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = input.nextLine();
        System.out.println();

        HashTable table = new HashTable();
        int countryCount = 0;

        try {
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String name = data[0];
                    long population = Long.parseLong(data[2]);
                    long area = Long.parseLong(data[4]);
                    table.insert(name, population, area);
                    countryCount++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            input.close();
            return;
        }

        System.out.println("There were " + countryCount + " country records read into the hash table.");

        while (true) {
            System.out.println("\n1) Print hash table");
            System.out.println("2) Delete a country of a given name");
            System.out.println("3) Insert a country of its name, population, and area");
            System.out.println("4) Search and print a country and its population density for a given name");
            System.out.println("5) Print numbers of empty cells and collided cells");
            System.out.println("6) Exit");
            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    table.display();
                    break;
                case "2":
                    System.out.print("Enter country name: ");
                    String nameToDelete = input.nextLine();
                    table.delete(nameToDelete);
                    break;
                case "3":
                    System.out.print("Enter country name: ");
                    String name = input.nextLine();
                    System.out.print("Enter country population: ");
                    long population = Long.parseLong(input.nextLine());
                    System.out.print("Enter country area: ");
                    long area = Long.parseLong(input.nextLine());
                    table.insert(name, population, area);
                    System.out.println(name + " is inserted to hash table");
                    break;
                case "4":
                	System.out.print("Enter country name: ");
                    String nameToSearch = input.nextLine();
                    int index = table.find(nameToSearch);
                    if (index != -1) {
                        double density = table.getPopulationDensity(nameToSearch);
                        System.out.printf("%s is found at index %d with population density of %.2f\n", nameToSearch, index, density);
                    } else {
                        System.out.println(nameToSearch + " is not a country");
                    }
                    break;
                case "5":
                    table.printEmptyAndCollidedCells();
                    break;
                case "6":
                    System.out.println("\nHave a good day!");
                    return;
                default:
                    System.out.println("Invalid choice enter 1-6");
            }
        }
    }
}

