package ex05;

import java.util.Vector;
import ex04.Command;

/**
 * Створює обробник потоку, що виконує об'єкти з інтерфейсом {@linkplain Command}; шаблон Worker Thread
 * @author Артем Єдалов
 * @version 1.0
 * @see Command
 */
public class CommandQueue implements Queue
{
    /** Черга задач */
    private Vector<Command> tasks;

    /** Прапорець очікування */
    private boolean waiting;

    /** Прапорець завершення */
    private boolean shutdown;

    /** Встановлює прапорець завершення */
    public void shutdown()
    { shutdown = true; }

    /**
     * Ініціалізує {@linkplain CommandQueue#tasks},
     * {@linkplain CommandQueue#waiting},
     * {@linkplain CommandQueue#shutdown};<br>
     * створює потік для класу {@linkplain CommandQueue.Worker}
     */
    public CommandQueue()
    {
        tasks = new Vector<Command>();
        waiting = false;
        new Thread(new Worker()).start();
    }

    /**
     * Додає задачу до черги та сповіщає обробник якщо він перебував в очікуванні.<br>
     * {@inheritDoc}
     */
    @Override
    public void put(Command r)
    {
        tasks.add(r);
        if(waiting)
        {
            synchronized(this)
            { notifyAll(); }
        }
    }

    /**
     * Вилучає задачу з черги;
     * якщо черга порожня — чекає на нову задачу.<br>
     * {@inheritDoc}
     */
    @Override
    public Command take()
    {
        if(tasks.isEmpty())
        {
            synchronized(this)
            {
                waiting = true;
                try { wait(); }
                catch(InterruptedException ie)
                { waiting = false; }
            }
        }
        return(Command)tasks.remove(0);
    }

    /**
     * Обслуговує чергу задач; шаблон Worker Thread
     * @author Артем Єдалов
     * @version 1.0
     * @see Runnable
     */
    private class Worker implements Runnable
    {
        public void run()
        {
            while(!shutdown)
            {
                Command r = take();
                r.execute();
            }
        }
    }
}