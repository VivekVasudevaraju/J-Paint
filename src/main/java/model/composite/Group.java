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
 * Group class uses ShapesClassification which in turn uses ShapeClassificationDetails class
 * Thus Group class uses composite pattern
 */
public class Group implements ICommand, IUndoable {
    private final JPaintObserver paintObserver;
    private final List<IDraw> groupedShapes = new ArrayList<>();

    public Group(JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;
    }

    @Override
    public void execute() {
        System.out.println("Grouping shapes");

        ShapesClassification shapesClassification = new ShapesClassification();

        shapesClassification.groupedShapes.clear(); // Clear grouped shapes queue

        // Add selected shapes to "groupedShapes" List
        groupedShapes.addAll(shapesClassification.selectedShapes.getList());

        for (IDraw iDraw: groupedShapes) {
            iDraw.addShapeGroup(this); // Add Group to shape in order to know which shape belongs to which group
        }

        CommandHistory.add(this);

        paintObserver.sendNotification();
    }

    @Override
    public void undo() {
        for (IDraw iDraw: groupedShapes) {
            iDraw.removeShapeGroup(); // Remove the last Group from the list of groups the shape belongs to
        }

        paintObserver.sendNotification();
    }

    @Override
    public void redo() {
        for (IDraw iDraw: groupedShapes) {
            ShapesClassification.selectedShapes.add(iDraw); // Add the shapes to the selected list in order to highlight on repaint
            iDraw.addShapeGroup(this); // Add Group to the shape Group list
        }

        paintObserver.sendNotification();
    }
}
