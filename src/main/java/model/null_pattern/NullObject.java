package model.null_pattern;

import model.composite.Group;
import model.enums.ShapeColor;
import model.enums.ShapeShadingType;
import model.enums.ShapeType;
import model.shapes.IDraw;

import java.awt.*;
import java.util.List;

/**
 * NullObject class to handle Objects drawn that are not in the List
 */
public class NullObject extends AbstractShape implements IDraw {
    @Override
    public void setStartingPoint(Point startingPoint) {
        System.out.println("Null Object: Starting Point is not set");
    }

    @Override
    public void setEndingPoint(Point endingPoint) {
        System.out.println("Null Object: Ending Point is not set");
    }

    @Override
    public void setXCoordinate(int XCoordinate) {
        System.out.println("Null Object: X-Coordinate is not set");
    }

    @Override
    public void setYCoordinate(int YCoordinate) {
        System.out.println("Null Object: Y-Coordinate is not set");
    }

    @Override
    public void setWidth(int width) {
        System.out.println("Null Object: Width is not set");
    }

    @Override
    public void setHeight(int height) {
        System.out.println("Null Object: Height is not set");
    }

    @Override
    public void setPrimaryColor(ShapeColor primaryColor) {
        System.out.println("Null Object: Primary Color is not set");
    }

    @Override
    public void setSecondaryColor(ShapeColor secondaryColor) {
        System.out.println("Null Object: Secondary Color is not set");
    }

    @Override
    public void setShapeType(ShapeType shapeType) {
        System.out.println("Null Object: Shape Type is not set");
    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        System.out.println("Null Object: Shape Shading Type is not set");
    }

    @Override
    public void setShapeStroke() {
        System.out.println("Null Object: Stroke is not set");
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        System.out.println("Null Object: Stroke Color is not set");
    }

    @Override
    public void setGraphics2D(Graphics2D graphics2d) {
        System.out.println("Null Object: Graphics2D is not set");
    }

    @Override
    public Point getStartingPoint() {
        return null;
    }

    @Override
    public Point getEndingPoint() {
        return null;
    }

    @Override
    public int getXCoordinate() {
        return 0;
    }

    @Override
    public int getYCoordinate() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public ShapeColor getPrimaryColor() {
        return null;
    }

    @Override
    public ShapeColor getSecondaryColor() {
        return null;
    }

    @Override
    public ShapeType getShapeType() {
        return null;
    }

    @Override
    public ShapeShadingType getShapeShadingType() {
        return null;
    }

    @Override
    public Stroke getShapeStroke() {
        return null;
    }

    @Override
    public Color getStrokeColor() {
        return null;
    }

    @Override
    public Graphics2D getGraphics2D() {
        return null;
    }

    @Override
    public void drawShape() {
        System.out.println("Null Object: Shape is not set");
    }

    @Override
    public void drawShapeFromCopy(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType, Color strokeColor) {
        System.out.println("Null Object: Shape is not set");
    }

    @Override
    public void undo() {
        System.out.println("Null Object: Undo is not initialized");
    }

    @Override
    public void redo() {
        System.out.println("Null Object: Redo is not initialized");
    }

    @Override
    public void highlightShape() {
        System.out.println("Null Object: Cannot highlight cause there is no shape set");
    }

    @Override
    public int[] getBounds() {
        return new int[0];
    }

    @Override
    public void moveShape(int transformOffsetX, int transformOffsetY) {
        System.out.println("Null Object: Cannot move cause there is no shape set");
    }

    @Override
    public void addShapeGroup(Group groupShape) {
        System.out.println("Null Object: Cannot add group cause there is no shape set");
    }

    @Override
    public List<Group> getShapeGroup() {
        return null;
    }

    @Override
    public void removeShapeGroup() {
        System.out.println("Null Object: Cannot remove group cause there is no shape group set");
    }
}
