package ex02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Item2d;

public class ViewResult implements View
{
    private static final String FNAME = "items.bin";
    private static final int DEFAULT_NUM = 10;
    private ArrayList<Item2d> items = new ArrayList<Item2d>();

    public ViewResult()
    { this(DEFAULT_NUM); }

    public ViewResult(int n)
    {
        for(int ctr = 0; ctr < n; ctr++)
            items.add(new Item2d());
    }

    public ArrayList<Item2d> getItems()
    { return items; }

    private double calc(double side)
    { return (Math.pow(side, 2) * Math.sqrt(3) / 4.0) + Math.pow(side, 2); }

    public void init(double stepSide)
    {
        double side = 0.0;
        for (Item2d item : items)
        {
            item.setXY(side, calc(side));
            side += stepSide;
        }
    }

    @Override
    public void viewInit()
    { init((Math.random() * 100.0) + 1); }

    @Override
    public void viewSave() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception
    {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    public void viewHeader()
    { System.out.println("Results:"); }

    @Override
    public void viewBody()
    {
        for(Item2d item : items)
            System.out.println(item);
    }

    @Override
    public void viewFooter()
    { System.out.println("End."); }

    @Override
    public void viewShow()
    {
        viewHeader();
        viewBody();
        viewFooter();
    }
}