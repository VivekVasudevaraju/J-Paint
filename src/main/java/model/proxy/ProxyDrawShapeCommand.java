package model.proxy;

import model.command.ICommand;
import model.shapes.IDraw;

/**
 * ProxyDrawShapeStrategy class uses Proxy Pattern
 * Same as DrawShapeStrategy class but invokes drawShapeFromCopy() method
 */
public class ProxyDrawShapeCommand implements ICommand  {
    private final IDraw shape;
    private final IDraw previousShape;

    public ProxyDrawShapeCommand(IDraw previousShape, IDraw shape) {
        this.previousShape = previousShape;
        this.shape = shape;
    }

    @Override
    public void execute() { // Proxy Pattern
         System.out.println("Proxy Drawing Shape: " + this.shape);

        shape.drawShapeFromCopy(
                previousShape.getPrimaryColor(),
                previousShape.getSecondaryColor(),
                previousShape.getShapeType(),
                previousShape.getShapeShadingType(),
                previousShape.getStrokeColor()
        );
    }
}
