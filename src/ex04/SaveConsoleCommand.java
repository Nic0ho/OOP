package ex04;

import java.io.IOException;
import ex02.View;

/**
 * Консольна команда Save; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
 */
public class SaveConsoleCommand implements ConsoleCommand
{
    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    /**
     * Ініціалізує поле {@linkplain SaveConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}
     */
    public SaveConsoleCommand(View view)
    { this.view = view; }

    @Override
    public char getKey()
    { return 's'; }

    @Override
    public String toString()
    { return "'s'ave"; }

    @Override
    public void execute()
    {
        System.out.println("Save current.");
        try { view.viewSave(); }
        catch(IOException e)
        { System.err.println("Serialization error: " + e); }
        view.viewShow();
    }
}