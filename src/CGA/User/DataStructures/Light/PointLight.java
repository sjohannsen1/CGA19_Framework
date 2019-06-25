package CGA.User.DataStructures.Light;

import CGA.User.DataStructures.Geometry.Transformable;
import CGA.User.DataStructures.ShaderProgram;
import org.joml.Vector3f;

public class PointLight extends Transformable implements IPointLight {
    Vector3f position, lightColor;
    public PointLight(Vector3f position, Vector3f lightColor) {
        this.position=position;
        //TODO Position mit Modelmatrix auf transformable verknüpfen
        this.lightColor=lightColor;
    }

    @Override
    public void bind(ShaderProgram shaderProgram, String name) {
        shaderProgram.setUniform(name, lightColor);
        //TODO Implementierung überprüfen
    }

    @Override
    public void setLightColor(Vector3f lightColor) {
        this.lightColor=lightColor;
    }
}
