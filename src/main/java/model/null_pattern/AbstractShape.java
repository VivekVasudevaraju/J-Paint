package model.null_pattern;

import model.enums.ShapeColor;
import model.enums.ShapeShadingType;
import model.enums.ShapeType;

import java.awt.*;

public abstract class AbstractShape {
    
    public abstract void setStartingPoint(Point startingPoint);

    public abstract void setEndingPoint(Point endingPoint);

    public abstract void setXCoordinate(int XCoordinate);
    
    public abstract void setYCoordinate(int YCoordinate);

    public abstract void setWidth(int width);

    public abstract void setHeight(int height);
    
    public abstract void setPrimaryColor(ShapeColor primaryColor);

    public abstract void setSecondaryColor(ShapeColor secondaryColor);

    public abstract void setShapeType(ShapeType shapeType);
    
    public abstract void setShapeShadingType(ShapeShadingType shapeShadingType);
    
    public abstract void setShapeStroke();

    public abstract void setStrokeColor(Color strokeColor);
    
    public abstract void setGraphics2D(Graphics2D graphics2d);

    

    public abstract Point getStartingPoint();
    
    public abstract Point getEndingPoint();

    public abstract int getXCoordinate();

    public abstract int getYCoordinate();
 
    public abstract int getWidth();

    public abstract int getHeight();
    
    public abstract ShapeColor getPrimaryColor();

    public abstract ShapeColor getSecondaryColor();

    public abstract ShapeType getShapeType();
    
    public abstract ShapeShadingType getShapeShadingType();
    
    public abstract Stroke getShapeStroke();
    
    public abstract Color getStrokeColor();

    public abstract Graphics2D getGraphics2D();
    
    public abstract void drawShape();

    public abstract void drawShapeFromCopy(ShapeColor primaryColor, ShapeColor secondaryColor, ShapeType shapeType, ShapeShadingType shapeShadingType, Color strokeColor);

    public abstract void undo();

    public abstract void redo();
    
    public abstract void highlightShape();

    public abstract int[] getBounds();

    public abstract void moveShape(int transformOffsetX, int transformOffsetY);
}
