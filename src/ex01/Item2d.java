package ex01;

import java.io.Serializable;

public class Item2d implements Serializable
{
    private static final long serialVersionUID = 1L;

    private transient double x;
    private double y;

    public Item2d()
    { x = .0; y = .0; }

    public Item2d(double x, double y)
    { this.x = x; this.y = y; }

    public double setX (double x)
    { return this.x = x; }
    
    public double setY (double y)
    { return this.y = y; }

    public double getX()
    { return this.x; }

    public double getY()
    { return this.y; }

    public Item2d setXY(double x, double y)
    { this.x = x; this.y = y; return this; }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Item2d other = (Item2d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Math.abs(Math.abs(y) - Math.abs(other.y)) > .1e-10) return false;
        return true;
    }

    @Override
    public String toString()
    {
        long intVal = (long) y;
        return "side = " + x + ", sum = " + y + ", binary = " + Long.toBinaryString(intVal);
    }
}