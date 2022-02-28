package model.singleton;

import model.shapes.ShapeClassificationDetails;

/**
 * ShapesClassification class uses singleton pattern
 * Keep track of different shape lists
 */
public class ShapesClassification {
    public static final ShapeClassificationDetails groupedShapes = new ShapeClassificationDetails("Grouped shapes");
    public static final ShapeClassificationDetails clipboardShapes = new ShapeClassificationDetails("Clipboard shapes");
    public static final ShapeClassificationDetails selectedShapes = new ShapeClassificationDetails("Selected shapes");
    public static final ShapeClassificationDetails shapesOnCanvas = new ShapeClassificationDetails("Shapes on canvas");
}
