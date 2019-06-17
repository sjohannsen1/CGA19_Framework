package CGA.User.Game;

import CGA.Framework.GameWindow;
import CGA.Framework.OBJLoader;
import CGA.Framework.Vertex;
import CGA.User.DataStructures.Camera.TronCam;
import CGA.User.DataStructures.Geometry.Material;
import CGA.User.DataStructures.Geometry.Mesh;
import CGA.User.DataStructures.Geometry.Renderable;
import CGA.User.DataStructures.Geometry.VertexAttribute;
import CGA.User.DataStructures.ShaderProgram;
import CGA.User.DataStructures.Texture2D;
import org.joml.Vector3f;
import org.joml.Vector2f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengles.GLES20.GL_FLOAT;

/**
 * Created by Fabian on 16.09.2017.
 *
 */
public class Scene {
    private ShaderProgram simpleShader, tronShader;
    private Mesh mesh, mesh2;
    //private Matrix4f modelG, modelS;
    private Renderable sphere, ground;
    private Texture2D tDiff, tEmit, tSpec;

    private GameWindow window;

    public Scene(GameWindow window) {
        this.window = window;
    }

   private TronCam cam1;
    private Vector3f deltaPos=new Vector3f(0.0f,2.0f,4.0f); //Translation

    //scene setup
    public boolean init(GameWindow window) {

        try {
            //Load staticShader
            simpleShader = new ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl");
            tronShader = new ShaderProgram("assets/shaders/tron_vert.glsl", "assets/shaders/tron_frag.glsl");

            tDiff =new Texture2D("assets/textures/ground_diff.png", false);
            tEmit =new Texture2D("assets/textures/ground_emit.png", false);
            tSpec =new Texture2D("assets/textures/ground_spec.png", false);

            Material mGround= new Material(tDiff, tEmit, tSpec, 60.0f, new Vector2f(64.0f, 64.0f));
            tDiff.setTexParams(1,1,0,1);
            tEmit.setTexParams(0,0,0,1);
            tSpec.setTexParams(1,0,1,1);

            /*Transformationen aus 3.1.1
            modelG=new Matrix4f().rotateX(90).scale(0.03f);
            modelS=new Matrix4f().scale(0.5f);
            FloatBuffer vert= BufferUtils.createFloatBuffer(15);
            vert.put(-0.5f);vert.put(-0.5f);vert.put(0.0f);
            vert.put(0.5f);vert.put(-0.5f);vert.put(0.0f);
            vert.put(0.5f);vert.put(0.5f);vert.put(0.0f);
            vert.put(0.0f);vert.put(1.0f);vert.put(0.0f);
            vert.put(-0.5f);vert.put(0.5f);vert.put(0.0f);

            int idV= glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER,idV);
            glBufferData(GL_ARRAY_BUFFER,vert, GL_STATIC_DRAW);
            glVertexAttribPointer(0,3,GL_FLOAT, false,0,0);
            //glEnableVertexAttribArray(0);

            FloatBuffer colour= BufferUtils.createFloatBuffer(15);
            colour.put(0.0f);colour.put(0.0f);colour.put(1.0f);
            colour.put(0.0f);colour.put(0.0f);colour.put(1.0f);
            colour.put(0.0f);colour.put(1.0f);colour.put(0.0f);
            colour.put(1.0f);colour.put(0.0f);colour.put(0.0f);
            colour.put(0.0f);colour.put(1.0f);colour.put(0.0f);

            int idC= glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER,idC);
            glBufferData(GL_ARRAY_BUFFER,colour, GL_STATIC_DRAW);
            glVertexAttribPointer(0,3,GL_FLOAT, false,0,0);
            //glEnableVertexAttribArray(0);

            IntBuffer pos= BufferUtils.createIntBuffer(9);
            pos.put(0); pos.put(1); pos.put(2);
            pos.put(0);pos.put(2); pos.put(4);
            pos.put(4);pos.put(2);pos.put(3);
            VertexAttribute v=new VertexAttribute(3, GL_FLOAT, 12,0);*/
            //Aufgabe 1
            /*float [] verticesIch =     {
                    -0.5f,-0.5f,0,    1,0,1,
                    -0.5f,0,0,        1,0,1,
                    0,0,0,            1,0,1,
                    0,0.5f,0,         1,0,0,
                    -0.5f,0.5f,0,     1,0,0,
                    -0.5f,1,0,        0,0,1,
                    0,1,0,            0,0,1,
                    0,-0.5f,0,        1,0,0,
                    0.5f,0,0,         1,0,0,
                    0.25f,0,0,        1,0,0,
                    0.25f,0.5f,0,     0,0,1,
                    0.5f,0.5f,0,      1,0,1,
                    0.5f,1,0,         0,0,1,
                    0.25f,1,0,        0,0,1
            };

            int[] indicesIch = {
                    0,1,2,
                    2,3,4,
                    4,5,6,
                    2,7,8,
                    8,9,10,
                    10,8,11,
                    10,11,13,
                    11,12,13};

            int[] indexB= new int[]{
                    0,1,2,
                    0,2,4,
                    4,2,3
            };

            float[] vertices= new float[]{-0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
                    0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
                    0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
                    0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                    -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f};

            VertexAttribute[] vertexAttributes= new VertexAttribute[]{new VertexAttribute(3,GL_FLOAT,24,0),
                    new VertexAttribute(3,GL_FLOAT, 24,12)};
            //mesh=new Mesh(vertices, indexB,vertexAttributes);
            mesh=new Mesh(verticesIch,indicesIch,vertexAttributes);

*/




            //Vertex Attributes der Objekte
            VertexAttribute aPos=new VertexAttribute(3,GL_FLOAT,8*4,0); //position
            VertexAttribute aCol=new VertexAttribute(2, GL_FLOAT,8*4,3*4);//textur
            VertexAttribute aNorm=new VertexAttribute(3,GL_FLOAT,8*4,5*4);//Normale
            VertexAttribute aTex= new VertexAttribute(2, GL_UNSIGNED_BYTE, 8*4, 8*4); //Texture



            VertexAttribute[] atArray= new VertexAttribute[]{aPos,aCol,aNorm};

            //Cam
            cam1=new TronCam((float)Math.toRadians(90),16/9f,0.01f,1000);
            cam1.translateGlobal(deltaPos);

            cam1.setUp(new Vector3f(0,1,0));

            //Sphere

            /*OBJLoader.OBJResult objSphere=OBJLoader.loadOBJ("assets/models/sphere.obj",false,false);
            ArrayList <OBJLoader.OBJMesh> objMeshes= objSphere.objects.get(0).meshes;

            ArrayList<Mesh> meshes=new ArrayList<>();

            for(OBJLoader.OBJMesh objM:objMeshes){
                meshes.add(new Mesh(objM.getVertexData(), objM.getIndexData(),atArray));
            }
            sphere= new Renderable(meshes);
            //cam1.setParent(sphere);
            //Transformation aus 3.2.3
            //sphere.scaleLocal(new Vector3f(0.5f,0.5f,0.5f));
            //sphere.translateGlobal(new Vector3f(0.5f, 0f, 0f));
*/

            //Ground
            OBJLoader.OBJResult objGround=OBJLoader.loadOBJ("assets/models/ground.obj",false,false);
            ArrayList <OBJLoader.OBJMesh>objMeshes= objGround.objects.get(0).meshes;
            ArrayList<Mesh> meshes2=new ArrayList<>();

            for(OBJLoader.OBJMesh objM:objMeshes){
               meshes2.add(new Mesh(objM.getVertexData(), objM.getIndexData(),new VertexAttribute[]{aPos,aCol,aNorm, aTex}, mGround));
            }
            ground = new Renderable(meshes2);
            //TODO: Daten einfügen für Boden- richtig in Scene?  Material muss rüber -> NEIN ist doch schon in Texture2D

            //Transformation aus 3.2.3
            /*gound.rotateLocal(90,0,0);
            ground.scaleLocal(new Vector3f(0.03f,0.03f,0.03f));*/

            //glDeleteBuffers(int bufferID);

            //initial opengl state
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            glDisable(GL_CULL_FACE);
            glFrontFace(GL_CCW);
            glCullFace(GL_BACK);
            glEnable(GL_DEPTH_TEST);
            glDepthFunc(GL_LESS);
            return true;
        } catch (Exception ex) {
            System.err.println("Scene initialization failed:\n" + ex.getMessage() + "\n");
            return false;
        }
    }

    public void render(float dt, float t) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        //simpleShader.use();
        tronShader.use();

        //Uniformieren aus 3.1.2
        /*tronShader.setUniform("model_matrix", modelG, false);
        ground.render();*/
        cam1.bind(tronShader);
        ground.render(tronShader);

        //Uniformieren aus 3.1.2
        /*tronShader.setUniform("model_matrix", modelS, false);
        sphere.render();
        sphere.render(cam1, tronShader);*/

        //mesh.render();



    }

    public void update(float dt, float t) {
        if(window.getKeyState(GLFW_KEY_W)){
            sphere.translateGlobal(new Vector3f(0f,0f,-dt));

        }if(window.getKeyState(GLFW_KEY_A)){
            sphere.rotateLocal(0f,dt,0f);

        }if(window.getKeyState(GLFW_KEY_S)){
            sphere.translateGlobal(new Vector3f(0f,0f,dt));
        }if (window.getKeyState(GLFW_KEY_D)){
            sphere.rotateLocal(0f,-dt,0f);
        }

    }

    public void onKey(int key, int scancode, int action, int mode) {

    }

    public void onMouseMove(double xpos, double ypos) {

    }


    public void cleanup() {}
}
