package ro.teamnet.zerotohero.oop.graphicshape;
import static java.lang.Math.PI;

/**
 * Created by user on 6/30/2016.
 */
public class Circle extends Shape{
    private int xPos, yPos, radius;

    public Circle() {
        this.xPos = 0;
        this.yPos = 0;
        this.radius = 10;
    }

    public Circle(int xPos) {
        this.xPos = xPos;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public double area() {
        return PI*radius*radius;
    }

    @Override
    public String toString() {
        return "center = (" + xPos + "," + yPos + "and radius = " + radius;
    }

    public int fillColor() {
        return super.color;
    }

    public void fillColor(int x){
        super.color = x;
        System.out.println("The circle color is now 2.");
    }

    public void fillColor(float x) {
        super.saturation = x;
    }
}
