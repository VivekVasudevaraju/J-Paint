package view.interfaces;

import model.enums.ShapeShadingType;
import model.enums.ShapeType;

public interface ShapeShading {
        void draw_ShadedShape();
        ShapeShadingType shape_ShadingType();
        ShapeType get_ShapeType();
}
