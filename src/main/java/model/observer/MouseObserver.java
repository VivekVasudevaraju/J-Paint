package model.observer;

import java.awt.*;
import java.awt.event.*;

import model.MoveShape;
import model.interfaces.IApplicationState;
import model.mode.DrawMode;
import model.mode.MoveMode;
import model.mode.SelectMode;
import model.enums.MouseMode;
import view.interfaces.PaintCanvasBase;

/**
 * Reference from https://www.geeksforgeeks.org/mouselistener-mousemotionlistener-java/
 */

 /**
  * MouseObserver class listens to Mouse Events and Mouse motion events
  */
public class MouseObserver extends Frame implements MouseListener, MouseMotionListener {
    private final PaintCanvasBase paintCanvas;
    private final JPaintObserver paintObserver;
    private final IApplicationState appState;
    private Point startingPoint;
    private Point endingPoint;
    private int dragStartXCoordinates = 0;
    private int dragStartYCoordinates = 0;
    private boolean isMouseInMotion = false;
    MoveShape moveShape = null;

    public MouseObserver(PaintCanvasBase paintCanvas, IApplicationState appState, JPaintObserver paintObserver) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.paintObserver = paintObserver;
    }

    /**
     * Mouse Listeners
     */
    public void mousePressed(MouseEvent e) {
        // System.out.println("mouse pressed at point:" + e.getX() + " " + e.getY());
        startingPoint = e.getPoint();
        endingPoint = e.getPoint();

        dragStartXCoordinates = e.getX();
        dragStartYCoordinates = e.getY();

        isMouseInMotion = false;
    }

    public void mouseReleased(MouseEvent e) {
        // System.out.println("mouse released at point:" + e.getX() + " " + e.getY());
        endingPoint = e.getPoint();

        switch (appState.getActiveMouseMode()) {
            case DRAW:
                DrawMode draw = new DrawMode(startingPoint, endingPoint, paintCanvas, appState);
                draw.execute();
                break;

            case SELECT:
                SelectMode select = new SelectMode(startingPoint, endingPoint, paintObserver);
                select.execute();
                break;

            case MOVE:
                if (isMouseInMotion && moveShape != null) {
                    moveShape.setEndingPoint(endingPoint);
                    MoveMode move = new MoveMode(moveShape);
                    move.execute();
                }
                break;
        }
    }

    public void mouseExited(MouseEvent e) {
        // System.out.println("mouse exited through point:" + e.getX() + " " + e.getY());
    }

    public void mouseEntered(MouseEvent e) {
        // System.out.println("mouse entered at point:" + e.getX() + " " + e.getY());
    }

    public void mouseClicked(MouseEvent e) {
        // System.out.println("mouse clicked at point:" + e.getX() + " " + e.getY() + " mouse clicked :" + e.getClickCount());
    }

    /**
     * Mouse Motion Listeners
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("mouse moved");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.println("mouse dragged");
        isMouseInMotion = true;

        int currentXCoordinates = e.getX();
        int currentYCoordinates = e.getY();

        if (appState.getActiveMouseMode().equals(MouseMode.MOVE)) {
            int translateXX = currentXCoordinates - dragStartXCoordinates;
            int translateYY = currentYCoordinates - dragStartYCoordinates;
            Point transformPosition = new Point(translateXX, translateYY);

            dragStartXCoordinates = currentXCoordinates;
            dragStartYCoordinates = currentYCoordinates;

            moveShape = new MoveShape(startingPoint, transformPosition, paintObserver);
            moveShape.move();
        }
    }

}
