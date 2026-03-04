package ex01;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Calc
{
    private static final String FNAME = "Item2d.bin";

    private Item2d result;

    public Calc()
    { result = new Item2d(); }

    public void setResult(Item2d result)
    { this.result = result; }

    public Item2d getResult()
    { return result; }

    private double triangleS(double side)
    { return (Math.pow(side, 2) * Math.sqrt(3) / 4.0); }

    private double rectangleS(double side)
    { return Math.pow(side, 2); }

    public double init(double side)
    {
        result.setX(side);
        return result.setY(triangleS(side) + rectangleS(side));
    }

    public void show()
    { System.out.println(result); }
  
    public void save() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    public void restore() throws Exception
    {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d) is.readObject();
        is.close();
    }
}