import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ComputerStoreUI extends JFrame {
    private ComputerStoreAIS store;
    private DefaultTableModel productsTableModel;
    private int selectedProductIndex = -1;

    public ComputerStoreUI() {
        super("Магазин компьютерной техники");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(910, 600);
        setLocationRelativeTo(null);

        store = new ComputerStoreAIS();

        // Панель управления
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel productNameLabel = new JLabel("Название товара:");
        JTextField productNameField = new JTextField(20);
        JLabel productPriceLabel = new JLabel("Цена:");
        JTextField productPriceField = new JTextField(10);
        JLabel productQuantityLabel = new JLabel("Количество:");
        JTextField productQuantityField = new JTextField(5);
        JButton addProductButton = new JButton("Добавить товар");
        JButton deleteProductButton = new JButton("Удалить товар");

        // Создаем кнопку сортировки и выпадающее меню
        JButton sortButton = new JButton("Сортировать");
        JPopupMenu sortMenu = new JPopupMenu();
        JMenuItem sortByName = new JMenuItem("По названию");
        JMenuItem sortByPrice = new JMenuItem("По цене");
        JMenuItem sortByQuantity = new JMenuItem("По количеству");

        // Добавляем пункты меню в выпадающий список
        sortMenu.add(sortByName);
        sortMenu.add(sortByPrice);
        sortMenu.add(sortByQuantity);

        // Добавляем обработчики для пунктов меню
        sortByName.addActionListener(e -> sortProducts("name"));
        sortByPrice.addActionListener(e -> sortProducts("price"));
        sortByQuantity.addActionListener(e -> sortProducts("quantity"));

        // Привязываем выпадающее меню к кнопке
        sortButton.addActionListener(e -> sortMenu.show(sortButton, 0, sortButton.getHeight()));

        addProductButton.addActionListener(e -> addProduct(
                productNameField.getText(),
                Double.parseDouble(productPriceField.getText()),
                Integer.parseInt(productQuantityField.getText())
        ));
        deleteProductButton.addActionListener(e -> deleteSelectedProduct());

        controlPanel.add(productNameLabel);
        controlPanel.add(productNameField);
        controlPanel.add(productPriceLabel);
        controlPanel.add(productPriceField);
        controlPanel.add(productQuantityLabel);
        controlPanel.add(productQuantityField);
        controlPanel.add(addProductButton);
        controlPanel.add(deleteProductButton);
        controlPanel.add(sortButton);

        // Таблица товаров
        String[] productColumns = {"Название", "Цена", "Количество"};
        productsTableModel = new DefaultTableModel(productColumns, 0);
        JTable productsTable = new JTable(productsTableModel);
        productsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedProductIndex = productsTable.getSelectedRow();
            }
        });
        JScrollPane productsScrollPane = new JScrollPane(productsTable);

        // Панель для таблицы товаров
        JPanel tablesPanel = new JPanel(new GridLayout(1, 1, 20, 0));
        tablesPanel.add(productsScrollPane);

        // Компоновка элементов интерфейса
        add(controlPanel, BorderLayout.NORTH);
        add(tablesPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Метод для сортировки продуктов
    private void sortProducts(String criterion) {
        ArrayList<Product> products = store.getProducts();

        switch (criterion) {
            case "name":
                products.sort(Comparator.comparing(Product::getName));
                break;
            case "price":
                products.sort(Comparator.comparing(Product::getPrice));
                break;
            case "quantity":
                products.sort(Comparator.comparing(Product::getQuantity));
                break;
        }

        // Обновляем таблицу
        updateProductsTable();
    }

    private void addProduct(String name, double price, int quantity) {
        Product newProduct = new Product(name, price, quantity);
        store.addProduct(newProduct);
        updateProductsTable();
    }

    private void deleteSelectedProduct() {
        if (selectedProductIndex >= 0 && selectedProductIndex < store.getProducts().size()) {
            store.removeProduct(selectedProductIndex);
            updateProductsTable();
        }
    }

    private void updateProductsTable() {
        productsTableModel.setRowCount(0);
        for (Product product : store.getProducts()) {
            productsTableModel.addRow(new Object[]{
                    product.getName(), product.getPrice(), product.getQuantity()
            });
        }
    }

    public static void main(String[] args) {
        new ComputerStoreUI();
    }
}