package view.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.composite.Group;
import model.observer.IObserver;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;
import view.interfaces.PaintCanvasBase;

public class PaintCanvas extends PaintCanvasBase implements IObserver {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        super.paint(g);

         System.out.println("Repainting Canvas");

        Graphics2D graphics2D = (Graphics2D) g;
        List<IDraw> shapesList = ShapesClassification.shapesOnCanvas.getList();
        List<IDraw> selectedShapesList = ShapesClassification.selectedShapes.getList().stream().distinct().collect(Collectors.toList());
        List<Group> groupsToHighlight = new ArrayList<>();

        // Draw shapes from shapes array
        for (IDraw shapeItem : shapesList) {
            shapeItem.setGraphics2D(graphics2D);

            // Draw shape
            shapeItem.drawShape();

            // Highlight shape
            if (selectedShapesList.contains(shapeItem) && shapeItem.getShapeGroup().isEmpty()) {
                System.out.println("Highlighting shape: " + shapeItem);
                shapeItem.highlightShape();
            } else if (selectedShapesList.contains(shapeItem) && !shapeItem.getShapeGroup().isEmpty()) {
                Group groupAssociation = shapeItem.getShapeGroup().get(shapeItem.getShapeGroup().size() - 1);
                groupsToHighlight.add(groupAssociation);
            }
        }

        groupsToHighlight = groupsToHighlight.stream().distinct().collect(Collectors.toList());

        for (Group group: groupsToHighlight) {
            List<IDraw> groupedShapesList = new ArrayList<>();
            for (IDraw shapeItem : shapesList) {
                if (!shapeItem.getShapeGroup().isEmpty()) {
                    Group groupAssociation = shapeItem.getShapeGroup().get(shapeItem.getShapeGroup().size() - 1);
                    if (group.equals(groupAssociation)) {
                        groupedShapesList.add(shapeItem);
                    }
                }
            }
            highlightGroup(graphics2D, groupedShapesList);
        }

    }

    /**
     * Highlight the group of shapes
     * @param graphics2D
     * @param groupedShapesList
     */
    private void highlightGroup(Graphics2D graphics2D, List<IDraw> groupedShapesList) {
        int startXCoordinate = 0;
        int startYCoordinate = 0;
        int endXCoordinate = 0;
        int endYCoordinate = 0;

        for (int i = 0; i < groupedShapesList.size(); i++) {
            if (i == 0) {
                startXCoordinate = groupedShapesList.get(i).getBounds()[0];
                startYCoordinate = groupedShapesList.get(i).getBounds()[1];
                endXCoordinate = groupedShapesList.get(i).getBounds()[2];
                endYCoordinate = groupedShapesList.get(i).getBounds()[3];
            } else {

                int currentStartXCoordinate = groupedShapesList.get(i).getBounds()[0];
                int currentStartYCoordinate = groupedShapesList.get(i).getBounds()[1];
                int currentEndXCoordinate = groupedShapesList.get(i).getBounds()[2];
                int currentEndYCoordinate = groupedShapesList.get(i).getBounds()[3];

                if (currentStartXCoordinate < startXCoordinate) {
                    startXCoordinate = currentStartXCoordinate;
                }

                if (currentStartYCoordinate < startYCoordinate) {
                    startYCoordinate = currentStartYCoordinate;
                }

                if (currentEndXCoordinate > endXCoordinate) {
                    endXCoordinate = currentEndXCoordinate;
                }

                if (currentEndYCoordinate > endYCoordinate) {
                    endYCoordinate = currentEndYCoordinate;
                }

            }
        }

        int width = Math.abs(startXCoordinate - endXCoordinate);
        int height = Math.abs(startYCoordinate - endYCoordinate);

        graphics2D.setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(startXCoordinate, startYCoordinate, width, height);
    }


    /**
     * Repaint canvas
     */
    @Override
    public void update() {
        this.repaint();
    }
}
