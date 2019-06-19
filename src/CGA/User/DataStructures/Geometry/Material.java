package CGA.User.DataStructures.Geometry;

import CGA.User.DataStructures.ShaderProgram;
import CGA.User.DataStructures.Texture2D;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengles.GLES20.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengles.GLES20.*;

public class Material
{
    public Texture2D diff;
    public Texture2D emit;
    public Texture2D specular;

    public float shininess;
    public Vector2f tcMultiplier;

    public Material(Texture2D diff, Texture2D emit, Texture2D specular, float shininess, Vector2f tcmul)
    {
        this.diff = diff;
        this.emit = emit;
        this.specular = specular;
        this.shininess = shininess;
        this.tcMultiplier = tcmul;
    }

    public Material()
    {
        this.diff = null;
        this.emit = null;
        this.specular = null;
        this.shininess = 50.0f;
        this.tcMultiplier = new Vector2f(1.0f);
    }

    public void bind(ShaderProgram shaderProgram)
    {
        this.emit.bind(0);
        this.diff.bind(1);
        this.specular.bind(2);
        shaderProgram.setUniform("tcMultiplier", tcMultiplier);
        shaderProgram.setUniform("texEmit",0);
        shaderProgram.setUniform("texDiff",1);
        shaderProgram.setUniform("texSpec",2);
        shaderProgram.setUniform("shininess",shininess);
    }
}