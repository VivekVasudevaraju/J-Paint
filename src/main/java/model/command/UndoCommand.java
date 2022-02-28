package model.command;

import model.observer.JPaintObserver;

public class UndoCommand implements ICommand {
    private final JPaintObserver paintObserver;

    public UndoCommand(JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;
    }

    @Override
    public void execute() {
        System.out.println("Performing Undo. UNDO Stack -> " + CommandHistory.getUndoStack());

        CommandHistory.undo(); // Command Pattern
        paintObserver.sendNotification(); // Send notification to Repaint Canvas
    }
}
