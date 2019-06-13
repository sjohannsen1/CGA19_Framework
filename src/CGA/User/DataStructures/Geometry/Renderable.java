package CGA.User.DataStructures.Geometry;

import CGA.User.DataStructures.Camera.Camera;
import CGA.User.DataStructures.ShaderProgram;

import java.sql.ShardingKey;
import java.util.ArrayList;

/**
 * Created by Fabian on 19.09.2017.
 */

/**
 * Renders Mesh objects
 */
public class Renderable extends Transformable implements IRenderable
{
    /**
     * List of meshes attached to this renderable object
     */
    public ArrayList<Mesh> meshes;

    /**
     * creates an empty renderable object with an empty mesh list
     */
    public Renderable()
    {
        super();
        meshes = new ArrayList<>();
    }

    public Renderable(ArrayList<Mesh> meshes)
    {
        super();
        this.meshes = new ArrayList<>();
        this.meshes.addAll(meshes);
    }

    /**
     * Renders all meshes attached to this Renderable, applying the transformation matrix to
     * each of them
     */
    public void render()
    {
        for(Mesh m : meshes)
        {
            m.render();
        }
    }

    public void render(Camera camera, ShaderProgram shaderProgram)
    {   shaderProgram.setUniform("model_matrix", this.getWorldModelMatrix(),false);
        camera.bind(shaderProgram);
        for(Mesh m : meshes)
        {
            m.render();
        }
    }

    @Override
    //3.2.3 Implementierung der weiteren Renderfunktion
    public void render(ShaderProgram shaderProgram) {
        shaderProgram.setUniform("model_matrix", this.getWorldModelMatrix(), false);
        for(Mesh m : meshes)
        {
            m.render();
        }
    }
}
