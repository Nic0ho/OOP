package ex05;

import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;

public class MinMaxCommand implements Command
{
    private int resultMin = -1;
    private int resultMax = -1;
    private int progress = 0;
    private ViewResult viewResult;

    public ViewResult getViewResult()
    { return viewResult; }

    public ViewResult setViewResult(ViewResult viewResult)
    { return this.viewResult = viewResult; }

    public MinMaxCommand(ViewResult viewResult)
    { this.viewResult = viewResult; }

    public int getResultMin()
    { return resultMin; }

    public int getResultMax()
    { return resultMax; }

    public boolean running()
    { return progress < 100; }

    @Override
    public void execute()
    {
        progress = 0;
        System.out.println("MinMax executed...");
        int idx = 0, size = viewResult.getItems().size();
        for(Item2d item : viewResult.getItems())
        {
            if(item.getY() < 0)
            {
                if ((resultMax == -1) || (viewResult.getItems().get(resultMax).getY() < item.getY()))
                    resultMax = idx;
            }
            else if((resultMin == -1) || (viewResult.getItems().get(resultMin).getY() > item.getY()))
                resultMin = idx;
            idx++;
            progress = idx * 100 / size;
            if(idx % (size / 5) == 0)
                System.out.println("MixMax " + progress + "%");
            try { TimeUnit.MILLISECONDS.sleep(5000 / size); }
            catch(InterruptedException e)
            { System.err.println(e); }
        }
        System.out.print("MinMax done. ");
        if(resultMin > -1)
            System.out.print("Min positive #" + resultMin + " found: " + String.format("%.2f.", viewResult.getItems().get(resultMin).getY()));
        else
            System.out.print("Min positive not found.");
        if(resultMax > -1)
            System.out.println(" Max negative #" + resultMax + " found: " + String.format("%.2f.", viewResult.getItems().get(resultMax).getY()));
        else
            System.out.println(" Max negative item not found.");
        progress = 100;
    }
}