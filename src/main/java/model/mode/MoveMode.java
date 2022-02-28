package model.mode;

import model.MoveShape;
import model.command.CommandHistory;
import model.command.ICommand;
import model.command.IUndoable;
import model.singleton.ShapesClassification;

public class MoveMode implements ICommand, IUndoable {
    MoveShape moveShape;

    /**
     * Constructor that takes the Attributes of a Shape
     * @param moveShape
     */
    public MoveMode(MoveShape moveShape) {
        this.moveShape = moveShape;
    }

    @Override
    public void execute() {
        boolean isSelectedShapesNotEmpty = !ShapesClassification.selectedShapes.getList().isEmpty();
        if (isSelectedShapesNotEmpty) {
            System.out.println("Moved shape");

            this.moveShape.move(); // Move the current shape
            CommandHistory.add(this); // Add Move command to Command History
        }
    }

    @Override
    public void undo() { moveShape.undo(); }

    @Override
    public void redo() { moveShape.redo(); }
}
