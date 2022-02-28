package model.null_pattern;

import model.enums.ShapeType;
import model.interfaces.IApplicationState;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.shapes.Triangle;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

/**
 * Reference https://www.tutorialspoint.com/design_pattern/null_object_pattern.htm
 */

 /**
  * CreateShapeFactory class uses Null Object Pattern.
  * Shapes that are undefined are handled here.
  */
public class CreateShapeFactory {
    
    public static AbstractShape create(Point startingPoint, Point endingPoint, PaintCanvasBase paintCanvas, IApplicationState appState, ShapeType shapeType) {
        AbstractShape builtShape;
        
        switch (shapeType) {
            case RECTANGLE:
                builtShape = new Rectangle(startingPoint, endingPoint, paintCanvas, appState);
                break;

            case ELLIPSE:
                builtShape =  new Ellipse(startingPoint, endingPoint, paintCanvas, appState);
                break;

            case TRIANGLE:
                builtShape =  new Triangle(startingPoint, endingPoint, paintCanvas, appState);
                break;

            default:
                builtShape = new NullObject();
                break;
        }

        return builtShape;
    }

}
