
import java.util.Locale;

/**
 * Implements a hash table using separate chaining with double-ended
 * singly linked lists.
 *
 * Each country record stores its name, population, and area.
 * Country names are matched without regard to capitalization.
 *
 * @author Keith Brandenburg
 */
public class HashTable {

    private static final int SIZE = 257;
    private final Node[] table;

    public HashTable() {
        table = new Node[SIZE];
    }

    /**
     * Represents a country stored in a linked list.
     * The head node maintains a reference to the tail of the chain.
     */
    private static class Node {

        private final String name;
        private final long population;
        private final long area;

        private Node next;
        private Node tail;

        public Node(String name, long population, long area) {
            this.name = name;
            this.population = population;
            this.area = area;
        }

        public double getDensity() {
            return (double) population / area;
        }

        public void printNode() {
            System.out.printf("%s %.4f%n", name, getDensity());
        }
    }

    /**
     * Normalizes a country name for case-insensitive operations.
     */
    private String normalize(String name) {
        return name.trim().toLowerCase(Locale.ROOT);
    }

    /**
     * Calculates a hash index using the sum of the character values
     * in the normalized country name.
     */
    private int hash(String name) {
        int sum = 0;

        for (char c : normalize(name).toCharArray()) {
            sum = (sum + c) % SIZE;
        }

        return sum;
    }

    /**
     * Inserts a country at the end of its corresponding chain.
     */
    public void insert(String country, long population, long area) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Country name cannot be empty.");
        }

        if (population < 0) {
            throw new IllegalArgumentException(
                    "Population cannot be negative.");
        }

        if (area <= 0) {
            throw new IllegalArgumentException(
                    "Area must be greater than zero.");
        }

        int index = hash(country);

        Node newNode = new Node(country.trim(), population, area);

        if (table[index] == null) {
            table[index] = newNode;
            table[index].tail = newNode;
        } else {
            table[index].tail.next = newNode;
            table[index].tail = newNode;
        }
    }

    /**
     * Searches the appropriate chain for a country.
     *
     * @return the matching node, or null if not found
     */
    private Node findNode(String country) {
        int index = hash(country);
        String target = normalize(country);

        Node current = table[index];

        while (current != null) {
            if (normalize(current.name).equals(target)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Returns the hash table index of a country, or -1 if not found.
     */
    public int find(String country) {
        return findNode(country) != null ? hash(country) : -1;
    }

    /**
     * Deletes a country while maintaining the head and tail references
     * of its linked list.
     */
    public void delete(String country) {
        int index = hash(country);
        String target = normalize(country);

        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (normalize(current.name).equals(target)) {

                if (prev == null) {
                    // Remove the head node.
                    table[index] = current.next;

                    // Preserve the tail reference in the new head.
                    if (table[index] != null) {
                        table[index].tail = current.tail;
                    }

                } else {
                    // Remove a middle or tail node.
                    prev.next = current.next;

                    // Update the tail if the last node was removed.
                    if (current.next == null) {
                        table[index].tail = prev;
                    }
                }

                System.out.println(country
                        + " was deleted from the hash table.");
                return;
            }

            prev = current;
            current = current.next;
        }

        System.out.println(country
                + " was not found in the hash table.");
    }

    /**
     * Displays every bucket and its associated country records.
     */
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + ". ");

            Node current = table[i];

            if (current == null) {
                System.out.println("Empty");
            } else {
                while (current != null) {
                    current.printNode();
                    current = current.next;

                    if (current != null) {
                        System.out.print("     ");
                    }
                }
            }
        }
    }

    /**
     * Displays a country's name and population density.
     */
    public void printCountry(String country) {
        Node current = findNode(country);

        if (current != null) {
            current.printNode();
        } else {
            System.out.println(country
                    + " was not found in the hash table.");
        }
    }

    /**
     * Counts empty buckets and buckets containing multiple records.
     */
    public void printEmptyAndCollidedCells() {
        int empty = 0;
        int collided = 0;

        for (Node head : table) {
            if (head == null) {
                empty++;
            } else if (head.next != null) {
                collided++;
            }
        }

        System.out.println("There are " + empty + " empty cells and "
                + collided + " collided cells in the hash table.");
    }

    /**
     * Returns the population density of the specified country.
     *
     * @throws IllegalArgumentException if the country is not found
     */
    public double getPopulationDensity(String country) {
        Node current = findNode(country);

        if (current == null) {
            throw new IllegalArgumentException(
                    "Country was not found in the hash table.");
        }

        return current.getDensity();
    }
}
