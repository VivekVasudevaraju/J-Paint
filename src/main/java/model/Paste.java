package model;

import model.command.ICommand;
import model.interfaces.IApplicationState;
import model.mode.PasteMode;
import model.observer.JPaintObserver;
import view.interfaces.PaintCanvasBase;

public class Paste implements ICommand {
    private final JPaintObserver paintObserver;
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState appState;

    public Paste(PaintCanvasBase paintCanvas, JPaintObserver paintObserver, IApplicationState appState) {
        this.paintObserver = paintObserver;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }

    /**
     * Paste shape
     */
    @Override
    public void execute() {
        PasteMode paste = new PasteMode(paintCanvas, appState, paintObserver);
        paste.execute();
    }
}
