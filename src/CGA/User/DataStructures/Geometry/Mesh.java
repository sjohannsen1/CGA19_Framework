package CGA.User.DataStructures.Geometry;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
//import static org.lwjgl.opengl.GL15.glDeleteBuffers;
//import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
//import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;


/**
 * Created by Fabian on 16.09.2017.
 */
public class Mesh {

    //private data
    private int vao ;
    private int vbo ;
    private int ibo ;
    private int count;

    /**
     * Creates a Mesh object from vertexdata, intexdata and a given set of vertex attributes
     *
     * @param vertexdata plain float array of vertex data
     * @param indexdata  index data
     * @param attributes vertex attributes contained in vertex data
     * @throws Exception If the creation of the required OpenGL objects fails, an exception is thrown
     */
    public Mesh(float[] vertexdata, int[] indexdata, VertexAttribute[] attributes) throws Exception {
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



    /**
     * renders the mesh
     */
    public void render() {
        glBindVertexArray(vao);
        glDrawElements(GL_TRIANGLES,count,GL_UNSIGNED_INT,0);
        glBindVertexArray(0);
        //TODO: Place your code here. Call the rendering method every frame.

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
