package ex04;

import ex02.View;

/**
 * Консольна команда Restore; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
 */
public class RestoreConsoleCommand implements ConsoleCommand
{
    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    private ChangeConsoleCommand change;

    /**
     * Ініціалізує поле {@linkplain RestoreConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}
     */
    public RestoreConsoleCommand(View view, ChangeConsoleCommand change)
    { this.view = view; }

    @Override
    public char getKey()
    { return 'r'; }

    @Override
    public String toString()
    { return "'r'estore"; }

    @Override
    public void execute()
    {
        System.out.println("Restore last saved.");
        try
        {
            view.viewRestore();
            change.setOffset(1.0);
        }
        catch(Exception e) { System.err.println("Serialization error:" + e); }
        view.viewShow();
    }
}