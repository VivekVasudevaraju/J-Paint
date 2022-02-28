package model.mode;

import java.awt.*;
import java.util.List;

import model.command.ICommand;
import model.composite.Group;
import model.observer.JPaintObserver;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;

public class SelectMode implements ICommand {
    private final JPaintObserver paintObserver;
    int[] bounds = { 0, 0, 0, 0, 0, 0 };

    public SelectMode(Point startingPoint, Point endingPoint, JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;

        // Define the boundary of the selection
        bounds[0] = startingPoint.x;
        bounds[1] = startingPoint.y;
        bounds[2] = endingPoint.x;
        bounds[3] = endingPoint.y;

        int width = (bounds[2] >= bounds[0]) ? bounds[2] - bounds[0] : bounds[0] - bounds[2];
        int height = (bounds[3] >= bounds[1]) ? bounds[3] - bounds[1] : bounds[1] - bounds[3];

        bounds[4] = width;
        bounds[5] = height;
    }

    @Override
    public void execute() {
        System.out.println("Shapes selected");

        ShapesClassification.selectedShapes.clear();
        ShapesClassification.clipboardShapes.clear();

        List<IDraw> shapeList = ShapesClassification.shapesOnCanvas.getList();

        for (IDraw iDraw : shapeList) { // Loop
            if(detectCollision(iDraw, bounds)) { // Shape collision detection
                if (!iDraw.getShapeGroup().isEmpty()) { // If the collided shape is part of a group
                    addGroupedShapesToSelectList(shapeList, iDraw);
                } else {
                    ShapesClassification.selectedShapes.add(iDraw);
                }
            }
        }
        paintObserver.sendNotification();
    }

    /**
     * Get all the shapes from the current group
     * @param shapeList
     * @param iDraw
     */
    private void addGroupedShapesToSelectList(List<IDraw> shapeList, IDraw iDraw) {
        Group groupAssociation = iDraw.getShapeGroup().get(iDraw.getShapeGroup().size() - 1);
        for (IDraw iDrawDup : shapeList) {
            if (!iDrawDup.getShapeGroup().isEmpty()) {
                Group groupDupAssociation = iDrawDup.getShapeGroup().get(iDrawDup.getShapeGroup().size() - 1);
                if (groupDupAssociation.equals(groupAssociation)) {
                    ShapesClassification.selectedShapes.add(iDrawDup);
                }
            }
        }
    }

    /**
     * Check for collision detection of the selection and shapes on canvas
     * @param firstShape
     * @param secondShapeBoundary
     * @return
     */
    public static boolean detectCollision(IDraw firstShape, int[] secondShapeBoundary) {
        int[] firstShapeBoundary = firstShape.getBounds();

        return firstShapeBoundary[0] < secondShapeBoundary[0] + secondShapeBoundary[4] &&
                firstShapeBoundary[0] + firstShapeBoundary[4] > secondShapeBoundary[0] &&
                firstShapeBoundary[1] < secondShapeBoundary[1] + secondShapeBoundary[5] &&
                firstShapeBoundary[1] + firstShapeBoundary[5] > secondShapeBoundary[1];
    }
}
