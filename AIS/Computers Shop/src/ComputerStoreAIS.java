import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Сущность "Заказ"
class Order {
    private Map<Product, Integer> items;
    private double total;

    public Order() {
        this.items = new HashMap<>();
        this.total = 0;
    }

    public void addItem(Product product, int quantity) {
        items.put(product, quantity);
        total += product.getPrice() * quantity;
    }

    public void removeItem(Product product) {
        Integer quantity = items.get(product);
        if (quantity != null) {
            items.remove(product);
            total -= product.getPrice() * quantity;
        }
    }

    public double getTotal() {
        return total;
    }
}

// Сущность "Клиент"
class Customer {
    private String name;
    private ArrayList<Order> orders;

    public Customer(String name) {
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public String getName() {
        return name;
    }
}

// Основной класс АИС
public class ComputerStoreAIS {
    private ArrayList<Product> products;
    private ArrayList<Customer> customers;

    public ComputerStoreAIS() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int index) {
        products.remove(index);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }



    // Методы для выполнения основных операций
    public static void main(String[] args) {
        // Создаем магазин
        ComputerStoreAIS store = new ComputerStoreAIS();

    }
}