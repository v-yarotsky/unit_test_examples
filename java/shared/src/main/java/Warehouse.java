public interface Warehouse {
    public void add(String name, int amount);
    public void remove(String name, int amount);
    public int getInventory(String name);
    public boolean hasInventory(String name, int amount);
}

