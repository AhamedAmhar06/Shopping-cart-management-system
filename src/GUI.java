
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JTable productColoumn;
    private DefaultTableModel tableModel;
    private JTextArea selectedProductDetails;
    private WestminsterShoppingManager manager;

    public GUI() {
        manager = new WestminsterShoppingManager();




        List<Product> productList = manager.loadProduct();

        JPanel navBar = new JPanel(new BorderLayout());
        // Create combo box with options
        categoryComboBox = new JComboBox<>(new String[]{"All", "Clothing", "Electronics"});
        categoryComboBox.addActionListener(e -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            updateProductTable(selectedCategory);

        });
        // Button of shopping cart in navbar
        JButton shoppingCartButton = new JButton("Shopping Cart");
        navBar.add(categoryComboBox, BorderLayout.CENTER);
        navBar.add(shoppingCartButton, BorderLayout.EAST);
        add(navBar, BorderLayout.NORTH);



        // The second panel to showcase all the products under the column
        JPanel displayPanel = new JPanel(new BorderLayout());
        selectedProductDetails = new JTextArea();
        selectedProductDetails.setEditable(false);
        selectedProductDetails.setPreferredSize(new Dimension(200, 220));

        // Initialize tableModel
        tableModel = new DefaultTableModel(new Object[]{"Product ID", "Name", "Category", "Price (£)", "Info"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This causes all cells to be non editable https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable
                return false;
            }
        };
        productColoumn = new JTable(tableModel);
        productColoumn.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productColoumn.getSelectedRow();
            if (selectedRow >= 0) {
                Product selectedProduct = (Product) tableModel.getValueAt(selectedRow, tableModel.getColumnCount() - 1);
                displayProductDetails(selectedProduct);
            }
        });
        // Create JTable and scroll pane
        productColoumn = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productColoumn);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // The second panel's add to cart button to add all the details
        JButton addToCartButton = new JButton("Add to cart Cart");
        buttonsPanel.add(addToCartButton);

        displayPanel.add(selectedProductDetails, BorderLayout.CENTER);
        displayPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.SOUTH);

        // Initialize the table with the "All" category
        updateProductTable("All");
    }


    // Updating the product table with details method
    private void updateProductTable(String category) {
        tableModel.setRowCount(0); // Clear existing rows

        List<Product> productList = manager.loadProduct();
        switch (category) {
            case "All" -> {
                for (Product product : productList) {
                    tableModel.addRow(new Object[]{
                            product.getProductId(),
                            product.getProductName(),
                            product instanceof Clothing ? "Clothing" : "Electronics",
                            product.getPrice(),
                            product // Store the actual product object in the last column for later retrieval
                    });
                }
            }
            case "Clothing" -> {
                for (Product product : productList) {
                    if (product instanceof Clothing) {
                        tableModel.addRow(new Object[]{
                                product.getProductId(),
                                product.getProductName(),
                                "Clothing",
                                product.getPrice(),
                                product
                        });
                    }
                }
            }
            case "Electronics" -> {
                for (Product product : productList) {
                    if (product instanceof Electronics) {
                        tableModel.addRow(new Object[]{
                                product.getProductId(),
                                product.getProductName(),
                                "Electronics",
                                product.getPrice(),
                                product
                        });
                    }
                }
            }
        }
    }

    public void displayProductDetails(Product product) {
        selectedProductDetails.setText("\n     Selected Product - Details\n\n");
        selectedProductDetails.append("     Product ID: " + product.getProductId() + "\n\n");
        selectedProductDetails.append("     Product Name: " + product.getProductName() + "\n\n");
        selectedProductDetails.append("     Price: " + product.getPrice() + "\n\n");

        if (product instanceof Clothing clothing) {
            selectedProductDetails.append("     Size: " + clothing.getSize() + "\n\n");
            selectedProductDetails.append("     Color: " + clothing.getColor() + "\n\n");
        } else if (product instanceof Electronics electronics) {
            selectedProductDetails.append("     Brand: " + electronics.getBrand() + "\n\n");
            selectedProductDetails.append("    Warranty Period: " + electronics.getWarrantyPeriod() + "\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setTitle("Shopping Management system");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

