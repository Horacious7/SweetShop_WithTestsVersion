# Sweetshop App (Terminal Version)

Sweetshop App is a terminal-based application designed to manage a sweetshop's inventory and orders without a graphical user interface (GUI). The application allows users to perform various operations such as adding, viewing, and filtering birthday cakes and orders. It also includes functionalities for calculating order counts and total amounts by customer.

This version no longer relies on a database and instead stores data in either binary or text format, which can be manually modified via a file.

## Features

- **Add and View Birthday Cakes**: Manage the inventory of birthday cakes with details such as name, flavor, and price.
- **Update Birthday Cakes**: Modify details of existing birthday cakes.
- **Filter Cakes by Flavor and Price Range**: Filter cakes based on their flavor or a specific price range.
- **Add and View Orders**: Manage customer orders with details such as customer name, address, order date, and delivery date.
- **Delete Orders**: Remove specific orders from the system.
- **Filter Orders**: Filter orders based on various criteria, including status, customer name, address, and order date.
- **Order Count by Customer**: View the number of orders placed by a specific customer.
- **Total Amount by Customer**: Calculate the total amount to be paid by a specific customer.
- **Order Count per Cake**: View the total number of orders placed for each type of cake.
- **Test Coverage**: The project includes tests for various features with coverage to ensure functionality.
- **Data Storage**: Data is stored either in binary or text format, and can be manually configured by modifying a file.
- **Terminal-based Interface**: The application now runs in the terminal, removing the graphical user interface (GUI).

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 23** or higher
- **Maven**
- **IntelliJ IDEA** or any other preferred IDE

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Horacious7/SweetShop_WithTestsVersion.git
   ```
2. Navigate to the project directory:
   ```bash
   cd sweetshop-app-terminal
   ```
3. Open the project in your IDE.
4. Add the required dependencies, including the testing framework used for coverage.
5. Modify the storage option:
   - Open the configuration file (located in the project directory).
   - Choose between binary or text storage by editing the configuration.

### Usage

1. Run the application from your terminal:
   ```bash
   java -jar SweetshopApp.jar
   ```
2. Use the terminal interface to:
   - Add new birthday cakes and manage their details.
   - Update or filter birthday cakes by flavor or price range.
   - Add, view, or delete customer orders.
   - Filter orders by status, customer name, address, or order date.
   - Calculate statistics, such as the total amount payable or the number of orders per customer or cake.
3. Data will be stored in the selected format (binary or text) and can be manually edited as needed.
4. Run the tests with coverage in the IDE

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add a new feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a Pull Request.

## Acknowledgments

- Open-source libraries and tools that made this project possible.
- The testing framework used for test coverage.
- Java's standard libraries for file handling and terminal interaction.
