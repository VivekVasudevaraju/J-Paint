package model.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShapeClassificationDetails class to add and remove shapes 
 * from a predefined array selection
 */
public class ShapeClassificationDetails {
    private final List<IDraw> shapeList = new ArrayList<>();
    private final String listLabel;

    public ShapeClassificationDetails(String listLabel) {
        this.listLabel = listLabel;
    }

    public void add(IDraw item) {
        if (!shapeList.contains(item)) {
            shapeList.add(item);
        }
    }

    public void remove(IDraw item) {
        shapeList.remove(item);
    }

    public void clear() {
        shapeList.clear();
    }

    public List<IDraw> getList() {
        return shapeList;
    }

}
