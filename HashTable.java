// TODO: Auto-generated Javadoc
/**
 * The Class HashTable.
 * 
 * This class implements a hash table using separate chaining with a double-ended 
 * singly linked list to store country information including population and area. 
 * It supports insert, delete, search, and display methods and is populated from a CSV file.
 * 
 * 
 */
class HashTable {
    
    /** The Constant SIZE. */
    private static final int SIZE = 257;
    
    /** The table. */
    private Node[] table;

    /**
     * Instantiates a new hash table.
     */
    public HashTable() {
        table = new Node[SIZE];
    }

    /**
     * The Class Node.
     */
    private static class Node {
        
        /** The name. */
        String name;
        
        /** The population. */
        long population;
        
        /** The area. */
        long area;
        
        /** The next. */
        Node next;
        
        /** The tail. */
        Node tail;

        /**
         * Instantiates a new node.
         *
         * @param name the name
         * @param population the population
         * @param area the area
         */
        public Node(String name, long population, long area) {
            this.name = name;
            this.population = population;
            this.area = area;
        }

        /**
         * Prints the node.
         */
        public void printNode() {
            double density = (double) population / area;
            System.out.printf("%s %.4f\n", name, density);
        }
        
    }

    /**
     * Hash.
     *
     * @param name the name
     * @return the int
     */
    private int hash(String name) {
        int sum = 0;
        for (char c : name.toCharArray()) {
            sum += (int) c;
        }
        return sum % SIZE;
    }

    /**
     * Insert.
     *
     * @param country the country
     * @param population the population
     * @param area the area
     */
    public void insert(String country, long population, long area) {
        int index = hash(country);
        Node newNode = new Node(country, population, area);

        if (table[index] == null) {
            table[index] = newNode;
            table[index].tail = newNode;
        } else {
            table[index].tail.next = newNode;
            table[index].tail = newNode;
        }
    }

    /**
     * Find.
     *
     * @param country the country
     * @return the int
     */
    public int find(String country) {
        int index = hash(country);
        Node current = table[index];
        while (current != null) {
            if (current.name.equalsIgnoreCase(country)) {
                return index;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Delete.
     *
     * @param country the country
     */
    public void delete(String country) {
        int index = hash(country);
        Node current = table[index];
        Node prev = null;

        while (current != null) {
            if (current.name.equalsIgnoreCase(country)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                System.out.println(country + " is deleted from hash table");
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println(country + " is not a country");
    }

    /**
     * Display.
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
                }
            }
        }
    }

    /**
     * Prints the country.
     *
     * @param country the country
     */
    public void printCountry(String country) {
        int index = hash(country);
        Node current = table[index];
        while (current != null) {
            if (current.name.equalsIgnoreCase(country)) {
                current.printNode();
                return;
            }
            current = current.next;
        }
    }

    /**
     * Prints the empty and collided cells.
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
        System.out.println("There are " + empty + " empty cells and " + collided + " collisions in the hash table");
    }

	/**
	 * Gets the population density.
	 *
	 * @param country the country
	 * @return the population density
	 */
	public double getPopulationDensity(String country) {
		int index = hash(country);
		Node current = table[index];
		return (double) current.population / current.area;
	}
}
