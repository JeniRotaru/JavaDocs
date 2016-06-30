package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by user on 6/30/2016.
 */
public class Shape extends AbstractShape implements ShapeBehaviour{

    public void setColor(int color) {
        this.color = color;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    protected int color;
    protected float saturation;

    public double area () {
        return 0;
    }
}
