package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.oop.canvans.Canvans;
import ro.teamnet.zerotohero.oop.graphicshape.*;

/**
 * Created by user on 6/30/2016.
 */
public class RunApp {

    public static void main(String[] args) {
        Circles c = new Circles();
        double area = c.getAreaPub();
        System.out.println("The default circle area is " + area);
        c.getAreaDef();

        /*Canvans can = new Canvans();
        can.getArea => ?*/

        Shape x = new Circle(10);
        System.out.println("1)The area is: " + x.area());

        ShapeBehaviour y = new Square(10);
        System.out.println("2)The area is: " + y.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));
    }

}
