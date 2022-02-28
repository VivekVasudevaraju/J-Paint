package model.composite;

import model.command.CommandHistory;
import model.command.ICommand;
import model.command.IUndoable;
import model.observer.JPaintObserver;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;

import java.util.ArrayList;
import java.util.List;

// Composite pattern
/**
 * Ungroup class uses ShapesClassification which in turn uses ShapeClassificationDetails class
 * Thus Ungroup class uses composite pattern
 */
public class Ungroup implements ICommand, IUndoable {
    private final JPaintObserver paintObserver;
    private final List<IDraw> ungroupedShapes = new ArrayList<>();
    private Group groupAssociation;

    public Ungroup(JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;
    }

    @Override
    public void execute() {
        System.out.println("UnGrouping shapes");

        ShapesClassification shapesClassification = new ShapesClassification();

        ungroupedShapes.addAll(shapesClassification.selectedShapes.getList());

        // Get the last shape from the list of selected shapes
        IDraw singleShape = ungroupedShapes.get(ungroupedShapes.size() - 1);
        // Get the last Group of the shape and save it for later
        groupAssociation = singleShape.getShapeGroup().get(singleShape.getShapeGroup().size() - 1);

        for (IDraw iDraw: ungroupedShapes) {
            iDraw.removeShapeGroup(); // Remove the last added group from the group tracker list
        }

        CommandHistory.add(this);

        paintObserver.sendNotification();
    }

    @Override
    public void undo() {
        for (IDraw iDraw: ungroupedShapes) {
            iDraw.addShapeGroup(groupAssociation); // Add shapes back into the group
            ShapesClassification.selectedShapes.add(iDraw); // Add shape to the selected shapes list to highlight on repaint
        }

        paintObserver.sendNotification();
    }

    @Override
    public void redo() {
        for (IDraw iDraw: ungroupedShapes) {
            iDraw.removeShapeGroup(); // Remove the shape from the last added Group
        }

        paintObserver.sendNotification();
    }
}
