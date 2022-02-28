package model.shapes;

import model.composite.Group;
import model.enums.ShapeType;
import model.singleton.ColorPattern;
import model.singleton.ShapesClassification;
import model.enums.ShapeColor;
import model.enums.ShapeShadingType;
import model.interfaces.IApplicationState;
import model.null_pattern.AbstractShape;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class to draw an Rectangle
 */
public class Rectangle extends AbstractShape implements IDraw {
    private Graphics2D graphics2d;
    private Point startingPoint;
    private Point endingPoint;
    private int XCoordinate;
    private int YCoordinate;
    private int width;
    private int height;
    private ShapeColor primaryColor;
    private ShapeColor secondaryColor;
    private ShapeType shapeType;
    private ShapeShadingType shapeShadingType;
    private Stroke stroke = new BasicStroke(5);
    private Color strokeColor;
    private final List<Group> groupAssociation = new ArrayList<>();

    public Rectangle(Point startingPoint, Point endingPoint, PaintCanvasBase paintCanvas, IApplicationState appState) {
        setGraphics2D(paintCanvas.getGraphics2D());
        setStartingPoint(startingPoint);
        setEndingPoint(endingPoint);
        setXCoordinate((int) startingPoint.getX());
        setYCoordinate((int) startingPoint.getY());
        setPrimaryColor(appState.getActivePrimaryColor());
        setSecondaryColor(appState.getActiveSecondaryColor());
        setShapeType(appState.getActiveShapeType());
        setShapeShadingType(appState.getActiveShapeShadingType());
        calculateWidthAndHeight();
        setShapeStroke();
        setStrokeColor(ColorPattern.getColor(getSecondaryColor()));
    }

    private void calculateWidthAndHeight() {
        int width = (endingPoint.x >= startingPoint.x) ? endingPoint.x - startingPoint.x : startingPoint.x - endingPoint.x;
        int height = (endingPoint.y >= startingPoint.y) ? endingPoint.y - startingPoint.y : startingPoint.y - endingPoint.y;

        setWidth(width);
        setHeight(height);
    }

    @Override
    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    @Override
    public void setEndingPoint(Point endingPoint) {
        this.endingPoint = endingPoint;
    }

    @Override
    public void setXCoordinate(int XCoordinate) {
        this.XCoordinate = XCoordinate;
    }

    @Override
    public void setYCoordinate(int YCoordinate) {
        this.YCoordinate = YCoordinate;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setPrimaryColor(ShapeColor primaryColor) {
        this.primaryColor = primaryColor;
    }

    @Override
    public void setSecondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @Override
    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    @Override
    public void setShapeShadingType(ShapeShadingType shapeShadingType) {
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void setShapeStroke() {
        if(getShapeShadingType().equals(ShapeShadingType.OUTLINE) ||
            getShapeShadingType().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
        {
            this.stroke = new BasicStroke(5);
        }
    }

    @Override
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public void setGraphics2D(Graphics2D graphics2d) {
        this.graphics2d = graphics2d;
    }

    @Override
    public Point getStartingPoint() {
        return startingPoint;
    }

    @Override
    public Point getEndingPoint() {
        return endingPoint;
    }

    @Override
    public int getXCoordinate() {
        return XCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return YCoordinate;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ShapeColor getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public ShapeColor getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public ShapeShadingType getShapeShadingType() {
        return shapeShadingType;
    }

    @Override
    public Stroke getShapeStroke() {
        return stroke;
    }

    @Override
    public Color getStrokeColor() {
        return strokeColor;
    }

    @Override
    public Graphics2D getGraphics2D() {
        return graphics2d;
    }

    @Override
    public void undo() {
        ShapesClassification.shapesOnCanvas.remove(this);
    }

    @Override
    public void redo() {
        drawShape();
    }

    @Override
    public void drawShape() {
        ShapesClassification.shapesOnCanvas.add(this);

        Graphics2D graphics2d = getGraphics2D();

        switch (getShapeShadingType()) {
            case FILLED_IN:
                graphics2d.setColor(ColorPattern.getColor(getPrimaryColor()));
                graphics2d.fillRect(XCoordinate, YCoordinate, width, height);
                break;

            case OUTLINE:
                graphics2d.setStroke(stroke);
                setStrokeColor(ColorPattern.getColor(getSecondaryColor()));
                graphics2d.setColor(strokeColor);
                graphics2d.drawRect(XCoordinate, YCoordinate, width, height);
                break;

            case OUTLINE_AND_FILLED_IN:
                graphics2d.setStroke(stroke);
                graphics2d.setColor(strokeColor);
                graphics2d.drawRect(XCoordinate-3, YCoordinate-3, width+5, height+5);
                graphics2d.setColor(ColorPattern.getColor(getPrimaryColor()));
                graphics2d.fillRect(XCoordinate, YCoordinate, width, height);
                break;
        }
    }

    @Override
    public void drawShapeFromCopy(ShapeColor primaryColor,
                                  ShapeColor secondaryColor,
                                  ShapeType shapeType,
                                  ShapeShadingType shapeShadingType,
                                  Color strokeColor) {
        setPrimaryColor(primaryColor);
        setSecondaryColor(secondaryColor);
        setShapeType(shapeType);
        setShapeShadingType(shapeShadingType);
        setStrokeColor(strokeColor);

        drawShape();
    }

    @Override
    public void highlightShape() {
        Graphics2D graphics2d = getGraphics2D();
        graphics2d.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(XCoordinate-3, YCoordinate-3, width+5, height+5);
    }

    @Override
    public int[] getBounds() {
        int[] bounds = { startingPoint.x, startingPoint.y, endingPoint.x, endingPoint.y, width, height };
        return bounds;
    }

    @Override
    public void moveShape(int transformOffsetX, int transformOffsetY) {
        Point newStartingPoint = new Point(startingPoint.x + transformOffsetX, startingPoint.y + transformOffsetY);
        Point newEndingPoint = new Point(endingPoint.x + transformOffsetX, endingPoint.y + transformOffsetY);
        setStartingPoint(newStartingPoint);
        setEndingPoint(newEndingPoint);

        setXCoordinate(this.XCoordinate + transformOffsetX);
        setYCoordinate(this.YCoordinate + transformOffsetY);
    }

    @Override
    public void addShapeGroup(Group group) {
        groupAssociation.add(group);
    }

    @Override
    public List<Group> getShapeGroup() {
        return groupAssociation;
    }

    @Override
    public void removeShapeGroup() {
        groupAssociation.remove(groupAssociation.size() - 1);
    }
}
