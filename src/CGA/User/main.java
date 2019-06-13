package CGA.User;
/**
 * Created by Fabian on 16.09.2017.
 */
import CGA.User.Game.Game;

public class main
{
    public static void main(String[] args)
    {
        Game game = new Game(1920, 1080, false, false, "Testgame", 3, 3);
        game.run();

        /*//Aufgabe 1.1
        System.out.println("Aufgabe 1.1");
        Vector3d vector1 = new Vector3d(2,-3,-6);
        Vector3d vector2 = new Vector3d(Math.sqrt(2),-3,5);
        Vector3d vector1norm = new Vector3d();
        Vector3d vector2norm = new Vector3d();
        vector1.normalize(1,vector1norm);
        vector2.normalize(1,vector2norm);
        System.out.println("Vektor 1 normalisert: " + vector1norm);
        System.out.println("Vektor 2 normalisert: " + vector2norm);

        //Aufgabe 1.2
        System.out.println("\nAufgabe 1.2");
        Vector3d vector3 = new Vector3d(-2,2,Math.sqrt(8));
        Vector3d vector4 = new Vector3d(3,2,Math.sqrt(3));
        double winkelRadiant = vector3.angle(vector4);
        double winkelGrad = winkelRadiant*180/Math.PI;
        System.out.println("Winkel in Radiant: " + winkelRadiant);
        System.out.println("Winkel in Grad: " + winkelGrad );

        //Aufgabe 1.3
        System.out.println("\nAufgabe 1.3");
        int p=1;
        Vector3d vector5 = new Vector3d(1,-1,3);
        Vector3d vector6 = new Vector3d(p,p,0);
        Vector3d vector7 = new Vector3d();
        vector5.cross(vector6,vector7);
        System.out.println("Die drei orthogonal zueinander stehenden Vektoren sind: " + vector5 + vector6 + vector7);


        //Aufgabe 1.4
        System.out.println("\nAufgabe 1.4");
        double b = 40;
        double c = 50;
        double a = Math.sqrt(c*c-b*b);
        double alpha = Math.asin(a/c)*180/Math.PI;
        double beta = 180-90-alpha;
        System.out.println("Ergebnis von Satz des Pythagoras: " + a);
        System.out.println("Winkel alpha: " + alpha);
        System.out.println("Winkel beta: " + beta);

        //Aufgabe 1.5
        System.out.println("\nAufgabe 1.5");
        Vector3d punktA = new Vector3d(4,0,0);
        Vector3d punktB = new Vector3d(6,0,0);
        Vector3d punktC = new Vector3d(5,1,0);

        for(double gamma=0.25*Math.PI; gamma<=(2*Math.PI); gamma=gamma+0.25*Math.PI){
            Matrix3d matrix = new Matrix3d(Math.cos(gamma),-Math.sin(gamma),0,Math.sin(gamma),Math.cos(gamma),0,0,0,1);
            System.out.println("Punkt A mit Gamma = " + gamma + ": " + punktA.mul(matrix));
            System.out.println("Punkt B mit Gamma = " + gamma + ": " + punktB.mul(matrix));
            System.out.println("Punkt C mit Gamma = " + gamma + ": " + punktC.mul(matrix)+"\n");
        }

        //Aufgabe 1.6
        System.out.println("\nAufgabe 1.6");
        Vector3d viereckpunktA = new Vector3d(2,2,0);
        Vector3d viereckpunktB = new Vector3d(4,2,0);
        Vector3d viereckpunktC = new Vector3d(4,4,0);
        Vector3d viereckpunktD = new Vector3d(2,4,0);
        Vector3d temp = new Vector3d();

        double delta = 0.5;
        Matrix3d matrix2 = new Matrix3d(delta,0,0,0,delta,0,0,0,1);
        System.out.println(matrix2);
        System.out.println("Vektor für Punkt A:" + viereckpunktA.mul(matrix2,temp));
        System.out.println("Vektor für Punkt B:" + viereckpunktB.mul(matrix2,temp));
        System.out.println("Vektor für Punkt C:" + viereckpunktC.mul(matrix2,temp));
        System.out.println("Vektor für Punkt D:" + viereckpunktD.mul(matrix2,temp)+"\n");

        double epsilon = 2;
        Matrix3d matrix3 = new Matrix3d(epsilon,0,0,0,epsilon,0,0,0,1);
        System.out.println(matrix3);
        System.out.println("Vektor für Punkt A:" + viereckpunktA.mul(matrix3,temp));
        System.out.println("Vektor für Punkt B:" + viereckpunktB.mul(matrix3,temp));
        System.out.println("Vektor für Punkt C:" + viereckpunktC.mul(matrix3,temp));
        System.out.println("Vektor für Punkt D:" + viereckpunktD.mul(matrix3,temp)+"\n");

        double phi = 3;
        double lambda = 1;
        Matrix3d matrix4 = new Matrix3d(phi,0,0,0,lambda,0,0,0,1);
        System.out.println(matrix4);
        System.out.println("Vektor für Punkt A:" + viereckpunktA.mul(matrix4,temp));
        System.out.println("Vektor für Punkt B:" + viereckpunktB.mul(matrix4,temp));
        System.out.println("Vektor für Punkt C:" + viereckpunktC.mul(matrix4,temp));
        System.out.println("Vektor für Punkt D:" + viereckpunktD.mul(matrix4,temp)+"\n");
*/ //Aufgabe1
    }
}
