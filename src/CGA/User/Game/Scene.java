package CGA.User.Game;

import CGA.Framework.GameWindow;
import CGA.Framework.ModelLoader;
import CGA.Framework.OBJLoader;
import CGA.Framework.Vertex;
import CGA.User.DataStructures.Camera.TronCam;
import CGA.User.DataStructures.Geometry.Material;
import CGA.User.DataStructures.Geometry.Mesh;
import CGA.User.DataStructures.Geometry.Renderable;
import CGA.User.DataStructures.Geometry.VertexAttribute;
import CGA.User.DataStructures.Light.PointLight;
import CGA.User.DataStructures.ShaderProgram;
import CGA.User.DataStructures.Texture2D;
import org.joml.Vector3f;
import org.joml.Vector2f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengles.GLES20.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengles.GLES20.GL_FLOAT;

/**
 * Created by Fabian on 16.09.2017.
 *
 */
public class Scene {
    private ShaderProgram simpleShader, tronShader;
    private Renderable sphere, ground,motorrad;
    private Texture2D tDiff, tEmit, tSpec;
    private PointLight light;
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

            tDiff =new Texture2D("assets/textures/ground_diff.png", true);
            tEmit =new Texture2D("assets/textures/ground_emit.png", true);
            tSpec =new Texture2D("assets/textures/ground_spec.png", true);

            Material mGround= new Material(tDiff, tEmit, tSpec, 60.0f, new Vector2f(64.0f, 64.0f));
            tDiff.setTexParams(GL_REPEAT,GL_REPEAT,GL_LINEAR_MIPMAP_LINEAR,GL_LINEAR);
            tEmit.setTexParams(GL_REPEAT,GL_REPEAT,GL_LINEAR_MIPMAP_LINEAR,GL_LINEAR);
            tSpec.setTexParams(GL_REPEAT,GL_REPEAT,GL_LINEAR,GL_LINEAR);

            light= new PointLight(new Vector3f(),new Vector3f(1.0f,0.0f,1.0f));
            light.translateGlobal(new Vector3f(1f, 1f,2f));

            //Vertex Attributes der Objekte
            VertexAttribute aPos=new VertexAttribute(3,GL_FLOAT,8*4,0); //position
            VertexAttribute aTex=new VertexAttribute(2, GL_FLOAT,8*4,3*4);//textur
            VertexAttribute aNorm=new VertexAttribute(3,GL_FLOAT,8*6,5*4);//Normale



            VertexAttribute[] atArray= new VertexAttribute[]{aPos,aTex,aNorm};

            //Cam
            cam1=new TronCam((float)Math.toRadians(90),16/9f,0.01f,1000);
            cam1.translateGlobal(new Vector3f(2.0f, 2.0f, 4.0f));
            cam1.rotateLocal(0, (float) Math.toRadians(10), 0);


            cam1.setUp(new Vector3f(0,1,0));



            //Ground
            OBJLoader.OBJResult objGround=OBJLoader.loadOBJ("assets/models/ground.obj",false,false);
            ArrayList <OBJLoader.OBJMesh>objMeshes= objGround.objects.get(0).meshes;
            ArrayList<Mesh> meshes2=new ArrayList<>();

            for(OBJLoader.OBJMesh objM:objMeshes){
               meshes2.add(new Mesh(objM.getVertexData(), objM.getIndexData(),atArray,mGround));
            }
            ground = new Renderable(meshes2);





            //Motorrad
            motorrad = ModelLoader.loadModel("assets/Light Cycle/Light Cycle/HQ_Movie cycle.obj", (float) Math.toRadians(-90), (float) Math.toRadians(90), 0);
            motorrad.scaleLocal(new Vector3f(0.8f,0.8f,0.8f));
            cam1.setParent(motorrad);
            light.setParent(motorrad);

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

        tronShader.use();
        tronShader.setUniform("viewPos", cam1.getWorldPosition());

        cam1.bind(tronShader);
        light.bind(tronShader, "lightColor");
        ground.render( tronShader);

        motorrad.render(tronShader);




    }

    public void update(float dt, float t) {
        if(window.getKeyState(GLFW_KEY_W)){
            motorrad.translateLocal(new Vector3f(0f,0f,-dt*10));

        }if(window.getKeyState(GLFW_KEY_A)){
            motorrad.rotateLocal(0f,dt*10,0f);

        }if(window.getKeyState(GLFW_KEY_S)){
            motorrad.translateLocal(new Vector3f(0f,0f,dt*10));

        }if (window.getKeyState(GLFW_KEY_D)){
            motorrad.rotateLocal(0f,-dt*10,0f);

        }

    }

    public void onKey(int key, int scancode, int action, int mode) {

    }

    public void onMouseMove(double xpos, double ypos) {

    }


    public void cleanup() {}
}
