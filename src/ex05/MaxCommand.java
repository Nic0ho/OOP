package ex05;

import java.util.concurrent.TimeUnit;
import ex02.ViewResult;
import ex04.Command;

/**
 * Задача, що використовується обробником потоку;<br>
 * знаходить індекс елемента з максимальним значенням {@linkplain ex01.Item2d#getY() y};<br>
 * шаблон Worker Thread
 * @author Артем Єдалов
 * @version 1.0
 * @see Command
 * @see CommandQueue
 */
public class MaxCommand implements Command
{
    /** Індекс елемента з максимальним значенням y; -1 якщо не знайдено */
    private int result = -1;

    /** Прапорець готовності результату (0-99 — виконується, 100 — готово) */
    private int progress = 0;

    /** Обслуговує колекцію об'єктів {@linkplain ex01.Item2d} */
    private ViewResult viewResult;

    /**
     * Повертає поле {@linkplain MaxCommand#viewResult}
     * @return значення {@linkplain MaxCommand#viewResult}
     */
    public ViewResult getViewResult()
    { return viewResult; }

    /**
     * Встановлює поле {@linkplain MaxCommand#viewResult}
     * @param viewResult значення для {@linkplain MaxCommand#viewResult}
     * @return нове значення {@linkplain MaxCommand#viewResult}
     */
    public ViewResult setViewResult(ViewResult viewResult)
    { return this.viewResult = viewResult; }

    /**
     * Ініціалізує поле {@linkplain MaxCommand#viewResult}
     * @param viewResult об'єкт класу {@linkplain ViewResult}
     */
    public MaxCommand(ViewResult viewResult)
    { this.viewResult = viewResult; }

    /**
     * Повертає індекс елемента з максимальним значенням y
     * @return поле {@linkplain MaxCommand#result}
     */
    public int getResult()
    { return result; }

    /**
     * Перевіряє готовність результату
     * @return {@code true} — якщо виконання ще не завершено
     */
    public boolean running()
    { return progress < 100; }

    /**
     * Виконує пошук максимального значення {@linkplain ex01.Item2d#getY() y}
     * у колекції; використовується обробником потоку {@linkplain CommandQueue};<br>
     * шаблон Worker Thread
     */
    @Override
    public void execute()
    {
        progress = 0;
        System.out.println("Max executed...");
        int size = viewResult.getItems().size();
        result = 0;
        for(int idx = 1; idx < size; idx++)
        {
            if(viewResult.getItems().get(result).getY() < viewResult.getItems().get(idx).getY())
                result = idx;
            progress = idx * 100 / size;
            if(idx % (size / 3) == 0) System.out.println("Max " + progress + "%");
            try { TimeUnit.MILLISECONDS.sleep(3000 / size); }
            catch(InterruptedException e)
            { System.err.println(e); }
        }
        System.out.println("Max done. Item #" + result + " found: " + viewResult.getItems().get(result));
        progress = 100;
    }
}