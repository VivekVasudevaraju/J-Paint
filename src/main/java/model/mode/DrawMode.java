package model.mode;

import model.command.ICommand;
import model.interfaces.IApplicationState;
import model.null_pattern.AbstractShape;
import model.null_pattern.CreateShapeFactory;
import model.shapes.IDraw;
import model.strategy.DrawShapeStrategy;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class DrawMode implements ICommand {
    private final PaintCanvasBase paintCanvas;
    private final IApplicationState appState;
    private final Point startingPoint;
    private final Point endingPoint;

    public DrawMode(Point startingPoint, Point endingPoint, PaintCanvasBase paintCanvas, IApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    @Override
    public void execute() {
        AbstractShape builtShapeRectangle = CreateShapeFactory.create(startingPoint, endingPoint, paintCanvas, appState, appState.getActiveShapeType());
        DrawShapeStrategy drawRectangle = new DrawShapeStrategy((IDraw) builtShapeRectangle);
        drawRectangle.execute();
    }
}
