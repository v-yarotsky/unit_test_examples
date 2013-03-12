import java.util.Map;
import java.util.HashMap;

public class WarehouseImpl implements Warehouse {
    private Map<String, Integer> items = new HashMap<String, Integer>();

    public void add(String name, int amount) {
        int currentAmount = 0;
        if (items.containsKey(name)) {
            currentAmount = items.get(name);
        }
        items.put(name, currentAmount + amount);
    }

    public void remove(String name, int amount) {
        int currentAmount = 0;
        if (items.containsKey(name)) {
            currentAmount = items.get(name);
        }
        items.put(name, currentAmount - amount);
    }

    public int getInventory(String name) {
        if (items.containsKey(name)) {
            return items.get(name);
        }
        else {
            return 0;
        }
    }

    public boolean hasInventory(String name, int amount) {
        return items.containsKey(name) && items.get(name) >= amount;
    }
}

