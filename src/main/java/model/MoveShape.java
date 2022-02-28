package model;

import model.command.IUndoable;
import model.observer.JPaintObserver;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class MoveShape implements IUndoable {
    JPaintObserver paintObserver;
    private final Point startingPoint;
    private Point endingPoint = new Point();
    private final Point transformPosition;
    private final List<IDraw> selectedShapes = new ArrayList<IDraw>();

    public MoveShape(Point startingPoint, Point transformPosition, JPaintObserver paintObserver) {
        this.transformPosition = transformPosition;
        this.startingPoint = startingPoint;
        this.paintObserver = paintObserver;
        this.selectedShapes.addAll(ShapesClassification.selectedShapes.getList());
    }

    /**
     * Move shape
     */
    public void move() {
        translate(transformPosition.x, transformPosition.y);
    }

    /**
     * Move shape in real time
     */
    private void translate(int x, int y) {
        AffineTransform transform = new AffineTransform(); // Affine transform to get shape offset
        transform.translate(x, y);

        for (IDraw iDraw : selectedShapes) {
            iDraw.moveShape(x, y);
        }
        paintObserver.sendNotification();
    }

    /**
     * Set the ending point once the mouse stopped moving
     * @param endingPoint
     */
    public void setEndingPoint(Point endingPoint) {
        this.endingPoint = endingPoint;
    }

    /**
     * Move shape back to the last position
     */
    @Override
    public void undo() {
        int offsetXCoordinate = startingPoint.x - endingPoint.x;
        int offsetYCoordinate = startingPoint.y - endingPoint.y;

        translate(offsetXCoordinate, offsetYCoordinate);
    }

    /**
     * Move shape to the most recent position
     */
    @Override
    public void redo() {
        int translateXCoordinate = endingPoint.x - startingPoint.x;
        int translateYCoordinate = endingPoint.y - startingPoint.y;

        translate(translateXCoordinate, translateYCoordinate);
    }
}
