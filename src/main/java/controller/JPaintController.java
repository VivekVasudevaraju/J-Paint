package controller;

import model.Copy;
import model.Delete;
import model.Paste;
import model.command.*;
import model.composite.Group;
import model.composite.Ungroup;
import model.interfaces.IApplicationState;
import model.observer.JPaintObserver;
import view.EventName;
import view.interfaces.IUiModule;
import view.interfaces.PaintCanvasBase;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final JPaintObserver paintObserver;
    private final PaintCanvasBase paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvasBase paintCanvas, JPaintObserver paintObserver) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintObserver = paintObserver;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    /**
     * Setup event callbacks on UI selection
     */
    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape(null));
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType(null));
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, () -> applicationState.setActiveStartAndEndPointMode(null));
        uiModule.addEvent(EventName.UNDO, this::Undo);
        uiModule.addEvent(EventName.REDO, this::Redo);
        uiModule.addEvent(EventName.COPY, this::Copy);
        uiModule.addEvent(EventName.PASTE, this::Paste);
        uiModule.addEvent(EventName.DELETE, this::Delete);
        uiModule.addEvent(EventName.GROUP, this::Group);
        uiModule.addEvent(EventName.UNGROUP, this::Ungroup);
    }

    /**
     * Undo the last command 
     */
    public void Undo() {
        UndoCommand undoCommand = new UndoCommand(paintObserver);
        undoCommand.execute();
    }

    /**
     * Redo the last command
     */
    public void Redo() {
        RedoCommand redoCommand = new RedoCommand(paintObserver);
        redoCommand.execute();
    }

    /**
     * Copy selected shapes
     */
    public void Copy() {
        Copy copy = new Copy();
        copy.execute();
    }

    /**
     * Paste selected shapes
     */
    public void Paste() {
        Paste paste = new Paste(paintCanvas, paintObserver, applicationState);
        paste.execute();
    }

    /**
     * Delete selected shapes
     */
    public void Delete() {
        Delete delete = new Delete(paintObserver);
        delete.execute();
    }

    /**
     * Group selected shapes
     */
    public void Group() {
        Group group = new Group(paintObserver);
        group.execute();
    }

    /**
     * UnGroup selected shapes
     */
    public void Ungroup() {
        Ungroup ungroup = new Ungroup(paintObserver);
        ungroup.execute();
    }
}
