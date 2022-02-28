package model.command;

import model.observer.JPaintObserver;

public class RedoCommand implements ICommand {
    private final JPaintObserver paintObserver;

    public RedoCommand(JPaintObserver paintObserver) {
        this.paintObserver = paintObserver;
    }

    @Override
    public void execute() {
        System.out.println("Performing Redo. REDO Stack -> " + CommandHistory.getRedoStack());

        CommandHistory.redo(); // Command Pattern
        paintObserver.sendNotification(); // Send notification to Repaint Canvas
    }
}
