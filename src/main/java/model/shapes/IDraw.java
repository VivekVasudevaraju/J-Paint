package model.shapes;

import model.composite.Group;
import model.enums.ShapeColor;
import model.enums.ShapeShadingType;
import model.enums.ShapeType;

import java.awt.*;
import java.util.List;

/**
 * Shapes class outline 
 */
public interface IDraw {
    /** SETTERS */
    void setStartingPoint(Point startingPoint);

    void setEndingPoint(Point endingPoint);

    void setXCoordinate(int XCoordinate);

    void setYCoordinate(int YCoordinate);

    void setWidth(int width);

    void setHeight(int height);

    void setPrimaryColor(ShapeColor primaryColor);

    void setSecondaryColor(ShapeColor secondaryColor);

    void setShapeType(ShapeType shapeType);

    void setShapeShadingType(ShapeShadingType shapeShadingType);

    void setShapeStroke();

    void setStrokeColor(Color strokeColor);

    void setGraphics2D(Graphics2D graphics2d);

    /** GETTERS */
    Point getStartingPoint();

    Point getEndingPoint();

    int getXCoordinate();

    int getYCoordinate();

    int getWidth();

    int getHeight();

    ShapeColor getPrimaryColor();

    ShapeColor getSecondaryColor();

    ShapeType getShapeType();

    ShapeShadingType getShapeShadingType();

    Stroke getShapeStroke();

    Color getStrokeColor();

    Graphics2D getGraphics2D();

    /** DRAW */
    void drawShape();

    void drawShapeFromCopy(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType, Color strokeColor);

    void undo();

    void redo();

    void highlightShape();

    int[] getBounds();

    void moveShape(int transformOffsetX, int transformOffsetY);

    void addShapeGroup(Group groupShape);

    List<Group> getShapeGroup();

    void removeShapeGroup();
}
