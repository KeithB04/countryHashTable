[README.md](https://github.com/user-attachments/files/35336625/README.md)

# Country Hash Table

A Java command-line application that stores and manages country data using a custom hash table. The program implements separate chaining with double-ended singly linked lists to handle collisions efficiently without relying on Java's built-in HashMap class.

Users can load country records from a CSV file, insert and delete countries, search for specific countries, calculate population densities, and analyze hash table collisions.

## Features

- **CSV File Processing:** Loads country records from a CSV file and skips malformed or invalid records.
- **Custom Hash Table:** Implements a fixed-size hash table containing 257 buckets.
- **Collision Handling:** Uses separate chaining with double-ended singly linked lists.
- **Country Insertion:** Adds countries using their name, population, and area.
- **Country Deletion:** Removes countries while maintaining linked list integrity.
- **Country Search:** Performs case-insensitive searches and displays the country's hash table index and population density.
- **Hash Table Display:** Displays all buckets and their associated country records.
- **Collision Analysis:** Calculates the number of empty buckets and buckets containing multiple records.
- **Input Validation:** Handles invalid numerical inputs, negative populations, and invalid area values.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Hash Tables
- Singly Linked Lists
- Separate Chaining
- CSV File Processing
- Exception Handling

## Project Structure

```text
src/
├── countryHashTable.java
├── HashTable.java
├── README.md
data/
├── countries.csv
```

### File Descriptions

- `countryHashTable.java` — Contains the main application, menu interface, CSV processing, and user input handling.
- `HashTable.java` — Contains the custom hash table implementation, linked list nodes, hashing algorithm, and supported operations.
- `countries.csv` — Sample country dataset used to demonstrate and test the application.

## How It Works

The program uses a hash table with 257 buckets to store country records. Each record contains a country's name, population, and area.

The hashing algorithm normalizes the country name to lowercase, sums its character values, and applies modulo 257 to determine the appropriate bucket.

When multiple countries produce the same hash index, separate chaining is used to store them in a singly linked list. Each chain maintains a head and tail reference, allowing new records to be appended without traversing the entire list.

Population density is calculated using the following formula:

```text
Population Density = Population / Area
```

Country names are normalized during hashing and comparison, allowing searches and deletions to be performed regardless of capitalization.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- A terminal or Java-compatible IDE

### Compilation

Navigate to the project directory and compile the Java files:

```bash
javac countryHashTable.java HashTable.java
```

### Running the Program

```bash
java countryHashTable
```

When prompted, enter the name of the CSV file:

```text
Enter the file name: countries.csv
```

## CSV File Format

The program expects a CSV file containing a header row and at least six columns in the following order:

```csv
Name,Capital,Population,GDP,Area,HappinessIndex
Canada,Ottawa,38000000,2000000000000,9984670,7.0
Japan,Tokyo,125000000,4200000000000,377975,6.1
```

Only the country name, population, and area are used by the hash table.

The included dataset contains illustrative country statistics for demonstration purposes.

The CSV parser supports simple comma-separated records without embedded commas in quoted fields.

## Menu Options

```text
1) Print hash table
2) Delete a country
3) Insert a country
4) Search for a country and its population density
5) Print numbers of empty and collided cells
6) Exit
```

## Example Usage

### Searching for a Country

```text
Enter your choice: 4
Enter country name: Canada
Canada is found at index 86 with population density of 3.81
```

### Inserting a Country

```text
Enter your choice: 3
Enter country name: ExampleCountry
Enter country population: 1000000
Enter country area: 5000
ExampleCountry was inserted into the hash table.
```

### Deleting a Country

```text
Enter your choice: 2
Enter country name: ExampleCountry
ExampleCountry was deleted from the hash table.
```

## Time and Space Complexity

| Operation | Average Case | Worst Case |
|---|---|---|
| Insertion | O(1) | O(1) |
| Search | O(1) | O(n) |
| Deletion | O(1) | O(n) |
| Population Density Lookup | O(1) | O(n) |
| Display Hash Table | O(m + n) | O(m + n) |
| Collision Analysis | O(m) | O(m) |

Where:

- `n` is the number of country records.
- `m` is the number of hash table buckets (257).

The overall space complexity is **O(m + n)**.

Insertion is constant time because each linked list maintains a tail reference. Average-case search and deletion assume a reasonably distributed hash function and a bounded load factor.

These complexities assume bounded country name lengths.

## Testing

The application can be tested using the included CSV file and manually inserting additional country records.

Important test cases include:

- Loading valid and invalid CSV records.
- Searching for existing and nonexistent countries.
- Searching with different capitalization.
- Inserting countries with matching hash indices.
- Deleting the head, middle, and tail of a linked list.
- Inserting additional records after deletion.
- Retrieving population densities from collided buckets.
- Handling invalid population and area values.
- Handling nonexistent input files.

## Concepts Demonstrated

This project demonstrates practical knowledge of:

- Implementing data structures from scratch.
- Designing and implementing hashing algorithms.
- Resolving hash collisions through separate chaining.
- Managing linked list head and tail references.
- Analyzing algorithm time and space complexity.
- Reading and processing external data files.
- Validating user input and handling exceptions.
- Organizing Java applications into reusable classes and methods.

## Future Improvements

- Implement dynamic resizing based on the load factor.
- Introduce a more effective hashing algorithm to reduce collisions.
- Prevent duplicate country records.
- Support more complex CSV formats.
- Add automated unit tests using JUnit.
- Export modified country records to a CSV file.

## Author

Keith Brandenburg
