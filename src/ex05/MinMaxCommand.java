package ex05;

import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;

/**
 * Задача, що використовується обробником потоку;<br>
 * знаходить мінімальне позитивне та максимальне негативне значення {@linkplain ex01.Item2d#getY() y}; шаблон Worker Thread
 * @author Артем Єдалов
 * @version 1.0
 * @see Command
 * @see CommandQueue
 */
public class MinMaxCommand implements Command
{
    /** Індекс елемента з мінімальним позитивним y; -1 якщо не знайдено */
    private int resultMin = -1;

    /** Індекс елемента з максимальним негативним y; -1 якщо не знайдено */
    private int resultMax = -1;

    /** Прапорець готовності результату (0-99 — виконується, 100 — готово) */
    private int progress = 0;

    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;

    /**
     * Повертає поле {@linkplain MinMaxCommand#viewResult}
     * @return значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult getViewResult()
    { return viewResult; }

    /**
     * Встановлює поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult значення для {@linkplain MinMaxCommand#viewResult}
     * @return нове значення {@linkplain MinMaxCommand#viewResult}
     */
    public ViewResult setViewResult(ViewResult viewResult)
    { return this.viewResult = viewResult; }

    /**
     * Ініціалізує поле {@linkplain MinMaxCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public MinMaxCommand(ViewResult viewResult)
    { this.viewResult = viewResult; }

    /**
     * Повертає індекс елемента з мінімальним позитивним y
     * @return поле {@linkplain MinMaxCommand#resultMin}
     */
    public int getResultMin()
    { return resultMin; }

    /**
     * Повертає індекс елемента з максимальним негативним y
     * @return поле {@linkplain MinMaxCommand#resultMax}
     */
    public int getResultMax()
    { return resultMax; }

    /**
     * Перевіряє готовність результату
     * @return {@code true} — якщо виконання ще не завершено
     */
    public boolean running()
    { return progress < 100; }

    /**
     * Знаходить мінімальне позитивне та максимальне негативне
     * значення {@linkplain ex01.Item2d#getY() y} у колекції;
     * використовується обробником потоку {@linkplain CommandQueue};<br>
     * шаблон Worker Thread
     */
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
                System.out.println("MinMax " + progress + "%");
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