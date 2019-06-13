package CGA.User.DataStructures.Geometry;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Transformable implements ITransformable {
    private Matrix4f localModelMatrix= new Matrix4f().identity();
    private Vector4f destination= new Vector4f();
    private Transformable parent;

    @Override
    public Matrix4f getLocalModelMatrix() {
        return localModelMatrix;
    }

    @Override
    public void rotateLocal(float pitch, float yaw, float roll) {
    localModelMatrix.rotateXYZ(pitch, yaw, roll);
    }

    @Override
    public void rotateAroundPoint(float pitch, float yaw, float roll, Vector3f altMidpoint) {
        //localModelMatrix.rotate(pitch,yaw,roll,altMidpoint.x, altMidpoint.y, altMidpoint.z);
       localModelMatrix= (new Matrix4f().translate(altMidpoint).rotateXYZ(pitch,yaw,roll).translate(altMidpoint.negate())).mul(localModelMatrix);

    }

    @Override
    public void translateLocal(Vector3f deltaPos) {
        localModelMatrix.translate(deltaPos);

    }

    @Override
    public void scaleLocal(Vector3f scale) {
        localModelMatrix.scale(scale);
        //localModelMatrix.scale(scale.x,scale.y,scale.z);

    }

    @Override
    public Vector3f getPosition() {
        localModelMatrix.getColumn(3, destination);
        //destination.normalize();
        return new Vector3f(destination.x, destination.y, destination.z);
    }

    @Override
    public Vector3f getXAxis() {
         localModelMatrix.getColumn(0, destination);
         destination.normalize();

         return new Vector3f(destination.x, destination.y, destination.z);
    }

    @Override
    public Vector3f getYAxis() {
        localModelMatrix.getColumn(1, destination);
        destination.normalize();

        return new Vector3f(destination.x, destination.y, destination.z);
    }

    @Override
    public Vector3f getZAxis() {
        localModelMatrix.getColumn(2, destination);
        destination.normalize();

        return new Vector3f(destination.x, destination.y, destination.z);
    }

    @Override
    public void setParent(Transformable parent) {
        this.parent=parent;

    }

    @Override //TODO getWorldModelMatrix überprüfen
    public Matrix4f getWorldModelMatrix() {
        Matrix4f temp= new Matrix4f();
        if(parent!=null) {
            (parent.getWorldModelMatrix()).mul(localModelMatrix, temp);
            return temp;
        }else
            return localModelMatrix;
    }

    @Override
    public Vector3f getWorldPosition() {
        getWorldModelMatrix().getColumn(3,destination);
        //destination.normalize();
        return new Vector3f(destination.x,destination.y,destination.z);
    }

    @Override
    public Vector3f getWorldXAxis() {
        getWorldModelMatrix().getColumn(0,destination);
        destination.normalize();
        return new Vector3f(destination.x,destination.y,destination.z);
    }

    @Override
    public Vector3f getWorldYAxis() {
        getWorldModelMatrix().getColumn(1,destination);
        destination.normalize();
        return new Vector3f(destination.x,destination.y,destination.z);
    }

    @Override
    public Vector3f getWorldZAxis() {
        getWorldModelMatrix().getColumn(2,destination);
        destination.normalize();
        return new Vector3f(destination.x,destination.y,destination.z);
    }

    @Override
    public void translateGlobal(Vector3f deltaPos) {

        //ElternKoordinatenVektor
        //Vector3f x=getWorldPosition();

        //Object um ElternKoordinatenVektor
        //x als center /um die Werte von deltaPos/ Rückführung auf alte Position
        localModelMatrix=(new Matrix4f().translate(deltaPos)).mul(localModelMatrix);

    }
}
