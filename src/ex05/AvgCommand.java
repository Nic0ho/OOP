package ex05;

import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;

public class AvgCommand implements Command
{
    private double result = 0.0;
    private int progress = 0;
    private ViewResult viewResult;

    public ViewResult getViewResult()
    {  return viewResult; }

    public ViewResult setViewResult(ViewResult viewResult)
    { return this.viewResult = viewResult;}

    public AvgCommand(ViewResult viewResult)
    { this.viewResult = viewResult; }

    public double getResult()
    { return result; }

    public boolean running()
    { return  progress < 100; }

    @Override
    public void execute()
    {
        progress = 0;
        System.out.println("Average executed...");
        result = 0.0;
        int idx = 1, size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems())
        {
            result += item.getY();
            progress = idx * 100 / size;
            if(idx++ % (size / 2) == 0)
            { System.out.println("Average " + progress + "%"); }
            try { TimeUnit.MILLISECONDS.sleep(2000 / size); }
            catch(InterruptedException e) { System.err.println(e); }
        }
        result /= size;
        System.out.println("Average done. Result = " + String.format("%.2f", result));
        progress = 100;
    }
}