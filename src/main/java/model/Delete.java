package model;

import model.command.ICommand;
import model.command.IUndoable;
import model.observer.JPaintObserver;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;

import java.util.ArrayList;
import java.util.List;

public class Delete implements ICommand, IUndoable {
    private final JPaintObserver paintObserver;
    private final List<IDraw> deletedShapes = new ArrayList<IDraw>();

    public Delete(JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;
        deletedShapes.addAll(ShapesClassification.selectedShapes.getList());
    }

    @Override
    public void execute() {
        for (IDraw iDraw : deletedShapes) {
            ShapesClassification.shapesOnCanvas.remove(iDraw);
        }
        paintObserver.sendNotification();
    }

    /**
     * Add the deleted shapes to shapesOnCanvas list
     */
    @Override
    public void undo() {
        for (IDraw iDraw : deletedShapes) {
            ShapesClassification.shapesOnCanvas.add(iDraw);
        }
        paintObserver.sendNotification();
    }

    /**
     * Remove the deleted shapes to shapesOnCanvas list
     */
    @Override
    public void redo() {
        for (IDraw iDraw : deletedShapes) {
            ShapesClassification.shapesOnCanvas.remove(iDraw);
        }
        paintObserver.sendNotification();
    }
}
