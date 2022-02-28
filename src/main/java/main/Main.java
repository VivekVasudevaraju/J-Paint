package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.KeyboardInterface;
import model.observer.IObserver;
import model.observer.JPaintObserver;
import model.observer.MouseObserver;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {

    public static void main(String[] args) {
        PaintCanvasBase paintCanvas = new PaintCanvas(); // Create a new Paint Canvas
        IGuiWindow guiWindow = new GuiWindow(paintCanvas); // Add Paint Canvas to the GUI
        IUiModule uiModule = new Gui(guiWindow); // Set up the GUI defaults
        ApplicationState appState = new ApplicationState(uiModule); // Track the current state of the application

        JPaintObserver paintObservable = new JPaintObserver(); // Create an Observer class
        paintObservable.addObserver((IObserver) paintCanvas); // Subscribe the Paint Canvas to the Observer

        IJPaintController controller = new JPaintController(uiModule, appState, paintCanvas, paintObservable);
        controller.setup(); // Initialize the JPaint controller UI

        KeyboardInterface keyBoardController = new KeyboardInterface(paintCanvas, appState);
        keyBoardController.setup(); // Set up keyboard shortcuts

        MouseObserver mouseAdapterObserver = new MouseObserver(paintCanvas, appState, paintObservable);
        paintCanvas.addMouseListener(mouseAdapterObserver); // Add a mouse event listener to Paint Canvas
        paintCanvas.addMouseMotionListener(mouseAdapterObserver); // Add a mouse motion event listener to Paint Canvas 
    }

}

