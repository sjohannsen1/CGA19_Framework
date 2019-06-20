package CGA.User.DataStructures.Geometry;

import CGA.User.DataStructures.ShaderProgram;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
//import static org.lwjgl.opengl.GL15.glDeleteBuffers;
//import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
//import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengles.GLES20.GL_TEXTURE0;


/**
 * Created by Fabian on 16.09.2017.
 */
public class Mesh {

    //private data
    private int vao,vbo, ibo,count;
    private Material material;

    /**
     * Creates a Mesh object from vertexdata, intexdata and a given set of vertex attributes
     *
     * @param vertexdata plain float array of vertex data
     * @param indexdata  index data
     * @param attributes vertex attributes contained in vertex data
     * @throws Exception If the creation of the required OpenGL objects fails, an exception is thrown
     */
    private Mesh(float[] vertexdata, int[] indexdata, VertexAttribute[] attributes) throws Exception {
        count=indexdata.length;
        vao= glGenVertexArrays();
        glBindVertexArray(vao);

        vbo= glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,vertexdata, GL_STATIC_DRAW);

        for(int i=0; i<attributes.length;i++){
            glVertexAttribPointer(i,attributes[i].n,attributes[i].type,false,attributes[i].stride,attributes[i].offset);
            glEnableVertexAttribArray(i);
        }

        //glVertexAttribPointer(0,3,GL_FLOAT,false,24,0);


        //glVertexAttribPointer(attributes[1].n,3,attributes[1].type,false,attributes[1].stride,attributes[1].offset);
        //glVertexAttribPointer(1,3,GL_FLOAT,false,24,12);
        //glEnableVertexAttribArray(1);

        ibo=glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,indexdata, GL_STATIC_DRAW);
    }
    public Mesh(float[] vertexdata, int[] indexdata, VertexAttribute[] attributes, Material material) throws Exception {
        this.material=material;

        count=indexdata.length;

        vao= glGenVertexArrays();
        glBindVertexArray(vao);

        vbo= glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vbo);
        glBufferData(GL_ARRAY_BUFFER,vertexdata, GL_STATIC_DRAW);

       /* if(material.diff!= null){
            int diffID=material.diff.getTexID();
            //glBindTexture(GL_TEXTURE_2D,diffID);
        }
        if(material.emit!=null){
            //glBindTexture(GL_TEXTURE_2D,emitID);
            material.emit.bind(GL_TEXTURE0);
        }
        if(material.specular!=null) {
            int specularID = material.specular.getTexID();
            //glBindTexture(GL_TEXTURE_2D, specularID);
        }*/


        for(int i=0; i<attributes.length;i++){
            glVertexAttribPointer(i,attributes[i].n,attributes[i].type,false,attributes[i].stride,attributes[i].offset);
            glEnableVertexAttribArray(i);
        }

        ibo=glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,indexdata, GL_STATIC_DRAW);
    }



    /**
     * renders the mesh
     */
    private void render() {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES,count,GL_UNSIGNED_INT,0);
        glBindVertexArray(0);

    }
    public void render(ShaderProgram shaderProgram){
        material.bind(shaderProgram);
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES,count,GL_UNSIGNED_INT,0);
        glBindVertexArray(0);
    }

    /**
     * Deletes the previously allocated OpenGL objects for this mesh
     */
    public void cleanup() {
        if (ibo != 0)
            glDeleteBuffers(ibo);
        if (vbo != 0)
            glDeleteBuffers(vbo);
        if (vao != 0)
            glDeleteVertexArrays(vao);
    }
}
