package CGA.User.DataStructures.Light;

import CGA.User.DataStructures.Geometry.Transformable;
import CGA.User.DataStructures.ShaderProgram;
import org.joml.Vector3f;

public class PointLight extends Transformable implements IPointLight {
    private Vector3f lightColor;
    private float kQ, kL, kC;

    public PointLight(Vector3f position, Vector3f lightColor) {
        translateGlobal(position);
        //TODO Position mit Modelmatrix auf transformable verknüpfen
        this.lightColor=lightColor;
        this.kC=1.0f;
        this.kL=0.5f;
        this.kQ=0.1f;
    }
    public PointLight(Vector3f position, Vector3f lightColor, float kQ, float kL, float kC) {
        translateGlobal(position);
        //TODO Position mit Modelmatrix auf transformable verknüpfen
        this.lightColor=lightColor;
        this.kC=kC;
        this.kL=kL;
        this.kQ=kQ;

    }


    @Override
    public void bind(ShaderProgram shaderProgram, String name) {
        shaderProgram.setUniform("kC", kC);
        shaderProgram.setUniform("kL", kL);
        shaderProgram.setUniform("kQ", kQ);
        shaderProgram.setUniform("lightColor", lightColor);
        shaderProgram.setUniform(name, getWorldPosition());

        //TODO Implementierung überprüfen, lightcolor fixen
    }

    @Override
    public void setLightColor(Vector3f lightColor) {
        this.lightColor=lightColor;
    }
}
