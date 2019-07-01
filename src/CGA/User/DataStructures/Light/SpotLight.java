package CGA.User.DataStructures.Light;

import CGA.User.DataStructures.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class SpotLight extends PointLight implements ISpotLight {
    private Vector3f direction;
    private float intensity, gamma, phi;
    public SpotLight(Vector3f position, Vector3f lightColor, Vector3f direction, float intensity, float phi) {
        super(position, lightColor,0.01f, 0.05f, 0.5f);
        this.direction=direction;
        this.gamma =direction.dot(super.getPosition().negate().normalize());
        this.phi=phi;
        this.intensity=intensity;
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
        super.bind(shaderProgram,"lightPos");
        shaderProgram.setUniform("view_matrix", viewMatrix, false);
        shaderProgram.setUniform("direction", direction);
        shaderProgram.setUniform("outerCutOff", (float) Math.cos(gamma));
        shaderProgram.setUniform("cutOff",(float )Math.cos(phi) );
        shaderProgram.setUniform("phi", phi);

    }
}
