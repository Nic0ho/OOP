package ex04;

import ex02.View;

/**
 * Консольна команда Generate; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
 */
public class GenerateConsoleCommand implements ConsoleCommand
{
    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    /**
     * Ініціалізує поле {@linkplain GenerateConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}
     */
    public GenerateConsoleCommand(View view)
    { this.view = view; }

    @Override
    public char getKey()
    { return 'g'; }

    @Override
    public String toString()
    { return "'g'enerate"; }

    @Override
    public void execute()
    {
        System.out.println("Random generation.");
        view.viewInit();
        view.viewShow();
    }
}