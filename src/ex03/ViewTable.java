package ex03;

import java.util.Formatter;
import ex01.Item2d;
import ex02.ViewResult;

public class ViewTable extends ViewResult
{
    public static final int DEFAULT_WIDTH = 20;

    private int width;

    public ViewTable()
    { width = DEFAULT_WIDTH; }

    public ViewTable(int width)
    { this.width = width; }

    public ViewTable(int width, int n)
    {
        super(n);
        this.width = width;
    }

    public int setWidth(int width)
    { return this.width = width; }

    public int getWidth()
    { return width; }

    private void outLineLn()
    {
        for(int i = width; i > 0; i--)
            System.out.print('-');
        System.out.print("\n");
    }

    private void outHeader()
    {
        try (Formatter fmt = new Formatter())
        {
            fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, "s | %", "s\n");
            System.out.printf(fmt.toString(), "side ", "arrea sum ");
        }
        
    }

    private void outBody()
    {
        try (Formatter fmt = new Formatter())
        {
            fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, ".0f | %", ".3f\n");
            for(Item2d item : getItems())
                System.out.printf(fmt.toString(), item.getX(), item.getY());
        }
        
    }

    public final void init(int width)
    {
        this.width = width;
        viewInit();
    }

    public final void init(int width, double stepSide)
    {
        this.width = width;
        init(stepSide);
    }

    @Override
    public void init(double stepSide)
    {
        System.out.print("Initialization...");
        super.init(stepSide);
        System.out.println("done.  ");
    }

    @Override
    public void viewHeader()
    {
        outHeader();
        outLineLn();
    }

    @Override
    public void viewBody()
    { outBody(); }

    @Override
    public void viewFooter()
    { outLineLn(); }
}
