package CGA.User.DataStructures.Light;

import CGA.User.DataStructures.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class SpotLight extends PointLight implements ISpotLight {
    private Vector3f direction;
    private float intesity, theta, phi;
    public SpotLight(Vector3f position, Vector3f lightColor, Vector3f direction, float intesity, float phi) {
        super(position, lightColor);
        this.direction=direction;
        this.theta=direction.dot(super.getPosition().negate().normalize());
        this.phi=phi;
        this.intesity=intesity;
    }

    @Override
    public void bind(ShaderProgram shaderProgram, String name) {
        super.bind(shaderProgram, name);
    }

    @Override
    public void setLightColor(Vector3f lightColor) {
        super.setLightColor(lightColor);
    }

    @Override
    public void bind(ShaderProgram shaderProgram, String name, Matrix4f viewMatrix) {
        super.bind(shaderProgram,"lightColor");
        shaderProgram.setUniform("kC", 0.5f);
        shaderProgram.setUniform("kL", 0.05f);
        shaderProgram.setUniform("kQ", 0.01f);
        shaderProgram.setUniform("view_matrix", viewMatrix, false);
        shaderProgram.setUniform(name, direction);
        shaderProgram.setUniform("theta", theta);
        shaderProgram.setUniform("phi", phi);

    }
}
