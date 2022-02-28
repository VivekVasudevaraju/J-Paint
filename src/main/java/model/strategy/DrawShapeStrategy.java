package model.strategy;

import model.command.CommandHistory;
import model.command.ICommand;
import model.command.IUndoable;
import model.shapes.IDraw;

/**
 * DrawShapeStrategy class implements strategy pattern
 * Draws shape based on class provided to it
 */
public class DrawShapeStrategy implements ICommand, IUndoable {
    private final IDraw shape;

    public DrawShapeStrategy(IDraw shape) {
        this.shape = shape;
    }

    @Override
    public void execute() { // Strategy Pattern
         System.out.println("Drawing Shape: " + this.shape);

        CommandHistory.add(this);
        shape.drawShape();
    }

    @Override
    public void undo() {
        shape.undo();
    }

    @Override
    public void redo() {
        shape.redo();
    }
}
