package ex05;

import java.util.concurrent.TimeUnit;
import ex02.View;
import ex02.ViewResult;
import ex04.ConsoleCommand;

/**
 * Консольна команда Execute all threads; шаблон Command.<br>
 * Створює дві черги задач та очікує завершення їх паралельного виконання.
 * @author Артем Єдалов
 * @version 1.0
 */
public class ExecuteConsoleCommand implements ConsoleCommand
{
    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    /**
     * Повертає поле {@linkplain ExecuteConsoleCommand#view}
     * @return значення {@linkplain ExecuteConsoleCommand#view}
     */
    public View getView()
    { return view; }

    /**
     * Встановлює поле {@linkplain ExecuteConsoleCommand#view}
     * @param view значення для {@linkplain ExecuteConsoleCommand#view}
     * @return нове значення {@linkplain ExecuteConsoleCommand#view}
     */
    public View setView(View view)
    { return this.view = view;}

    /**
     * Ініціалізує поле {@linkplain ExecuteConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}
     */
    public ExecuteConsoleCommand(View view)
    { this.view = view; }

    @Override
    public char getKey()
    { return 'e'; }

    @Override
    public String toString()
    { return "'e'xecute"; }

    /**
     * Створює дві черги {@linkplain CommandQueue},
     * розміщує в них задачі {@linkplain MinMaxCommand}, {@linkplain MaxCommand}, {@linkplain AvgCommand} та очікує завершення їх паралельного виконання.<br>
     * {@inheritDoc}
     */
    @Override
    public void execute()
    {
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();

        MaxCommand maxCommand = new MaxCommand((ViewResult)view);
        AvgCommand avgCommand = new AvgCommand((ViewResult)view);
        MinMaxCommand minMaxCommand = new MinMaxCommand((ViewResult)view);
        System.out.println("Execute all threads...");

        queue1.put(minMaxCommand);
        queue2.put(maxCommand);
        queue2.put(avgCommand);

        try
        {
            while(avgCommand.running() || maxCommand.running() || minMaxCommand.running())
            { TimeUnit.MILLISECONDS.sleep(100); }

            queue1.shutdown();
            queue2.shutdown();
            
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e)
        { System.err.println(e); }

        System.out.println("All done.");
    }
}