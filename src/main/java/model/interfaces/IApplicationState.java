package model.interfaces;

import model.enums.ShapeColor;
import model.enums.ShapeShadingType;
import model.enums.ShapeType;
import model.enums.MouseMode;

public interface IApplicationState {
    void setActiveShape(ShapeType shape);

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType(ShapeShadingType shapeShade);

    void setActiveStartAndEndPointMode(MouseMode mode);

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    MouseMode getActiveMouseMode();
}
