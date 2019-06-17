package CGA.User.DataStructures.Camera;

import CGA.User.DataStructures.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class TronCam extends Camera {


    private Vector3f center=new Vector3f();
    private Vector3f up= new Vector3f();

    private float fieldOfView;
    private float aspectRation;
    private float nearPlane;
    private float farPlane;

    //Konstruktor
    public TronCam(float fieldOfView, float aspectRation, float nearPlane, float farPlane) {
        super();                               //ja sonst existieren viewMatrix und projectionMatrix nicht
        this.fieldOfView = fieldOfView;
        this.aspectRation = aspectRation;
        this.nearPlane = nearPlane;
        this.farPlane = farPlane;
    }
    /*public TronCam(){
        super();
        this.aspectRation=0f;
        this.fieldOfView=0f;
        this.nearPlane=0f;
        this.farPlane=0f;
    }*/



    @Override
    public Matrix4f calculateViewMatrix() {

        Matrix4f temp= new Matrix4f().identity();
        Vector3f pos = getWorldPosition();
        //System.out.println(pos);
        //Matrix4f viewMat = temp.lookAt(eye,pos,up);
        Matrix4f viewMat= temp.lookAt(getWorldPosition(), getWorldPosition().sub(getWorldZAxis()), getWorldYAxis() );
        return viewMat;
    }

    @Override
    public Matrix4f calculateProjectionMatrix() {
        Matrix4f temp= new Matrix4f().identity();
        return temp.perspective(fieldOfView,aspectRation,nearPlane,farPlane);
    }


    public void setCenter(Vector3f center) {
        this.center = center;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    public Vector3f getCenter() {
        return center;
    }

    public Vector3f getUp() {
        return up;
    }

    @Override
    public void bind(ShaderProgram shaderProgram) {

        shaderProgram.setUniform("view_matrix",
                this.calculateViewMatrix(),false);

        shaderProgram.setUniform("proj_matrix",
                this.calculateProjectionMatrix(),false);
    }
}
