package model.mode;

import model.enums.ShapeType;
import model.command.CommandHistory;
import model.command.ICommand;
import model.command.IUndoable;
import model.interfaces.IApplicationState;
import model.null_pattern.AbstractShape;
import model.null_pattern.CreateShapeFactory;
import model.observer.JPaintObserver;
import model.proxy.ProxyDrawShapeCommand;
import model.shapes.*;
import model.singleton.ShapesClassification;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PasteMode implements ICommand, IUndoable {
    private final List<IDraw> clipboardCollectionList = new ArrayList<IDraw>();
    private final List<IDraw> pastedShapes = new ArrayList<IDraw>();
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState appState;
    private final JPaintObserver paintObserver;

    public PasteMode(PaintCanvasBase paintCanvas, IApplicationState appState, JPaintObserver paintObserver) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.paintObserver = paintObserver;
        this.clipboardCollectionList.addAll(ShapesClassification.clipboardShapes.getList());
    }

    /**
     * Create a new shape class based on the shape chosen
     * @param startingPoint
     * @param endingPoint
     * @param shapeType
     * @return IDraw shape
     */
    private IDraw getShape(Point startingPoint, Point endingPoint ,ShapeType shapeType) {
        // Null Object Pattern
        AbstractShape builtShape = CreateShapeFactory.create(startingPoint, endingPoint, paintCanvas, appState, shapeType);
        return (IDraw) builtShape;
    }

    @Override
    public void execute() {
        System.out.println("Pasting shapes");

        CommandHistory.add(this);

        /**
         * Loop via the copied shapes.
         * Offset it's Starting and Ending Point values by +100
         */
        for (IDraw iDraw: clipboardCollectionList) {
            Point startingPoint = (Point) iDraw.getStartingPoint().clone();
            Point endingPoint = (Point) iDraw.getEndingPoint().clone();

            startingPoint.x += 100;
            startingPoint.y += 100;

            endingPoint.x += 100;
            endingPoint.y += 100;

            IDraw builtShape = getShape(startingPoint, endingPoint, iDraw.getShapeType());
            pastedShapes.add(builtShape); // Add Offset shape to the pastedShapes Array

            // Draw shape with new Co-ordinates
            ProxyDrawShapeCommand drawProxy = new ProxyDrawShapeCommand(iDraw, builtShape);
            drawProxy.execute();
        }
    }

    /**
     * Undo pasted shapes command
     */
    @Override
    public void undo() {
        for (IDraw iDraw: pastedShapes) {
            ShapesClassification.shapesOnCanvas.remove(iDraw); // Remove shape from the shapesOnCanvas list
        }
        paintObserver.sendNotification(); // Send notification to Repaint Canvas
    }

    /**
     * Redo pasted shapes command
     */
    @Override
    public void redo() {
        for (IDraw iDraw: pastedShapes) {
            ShapesClassification.shapesOnCanvas.add(iDraw); // Add shape to the shapesOnCanvas list
        }
        paintObserver.sendNotification(); // Send notification to Repaint Canvas
    }
}
