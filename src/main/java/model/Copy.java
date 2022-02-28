package model;

import model.command.ICommand;
import model.shapes.IDraw;
import model.singleton.ShapesClassification;

import java.util.List;

public class Copy implements ICommand {

    @Override
    public void execute() {
        boolean isSelectedShapesEmpty = !ShapesClassification.selectedShapes.getList().isEmpty();
        List<IDraw> selectedCollectionList = ShapesClassification.selectedShapes.getList();

        // Add copied shape to clipboardShapes list
        if(isSelectedShapesEmpty) {
            for (IDraw iDraw: selectedCollectionList) {
                ShapesClassification.clipboardShapes.add(iDraw);
            }
        }
    }
}
