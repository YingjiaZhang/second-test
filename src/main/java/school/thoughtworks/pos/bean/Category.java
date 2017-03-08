package school.thoughtworks.pos.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Category {
    private int id;
    private String name;
    private List<Item> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();

        result.put("id", getId());
        result.put("name", getName());
        result.put("categoryUri", "categories/" + getId());

        List<Map> items = getItems()
                .stream()
                .map(item -> item.toMap())
                .collect(Collectors.toList());
        result.put("items", items);

        return result;
    }
}
