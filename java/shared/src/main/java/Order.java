public class Order {
    private String itemName;
    private int amount;
    private boolean filled = false;

    public Order(String itemName, int amount) {
        this.itemName = itemName;
        this.amount = amount;
    }

    public void fill(Warehouse w) {
        if (w.hasInventory(itemName, amount)) {
            w.remove(itemName, amount);
            this.filled = true;
        }
    }

    public boolean isFilled() {
        return filled;
    }
}

