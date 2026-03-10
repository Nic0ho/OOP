package ex04;

/**
 * Консольна команда Undo; шаблон Command.<br>
 * @author Артем Єдалов
 * @version 1.0
 * @see ChangeConsoleCommand
 */
public class UndoConsoleCommand implements ConsoleCommand
{
    /** Посилання на команду {@linkplain ChangeConsoleCommand}, операцію якої необхідно скасувати */
    private ChangeConsoleCommand change;

    /**
     * Ініціалізує поле {@linkplain UndoConsoleCommand#change}
     * @param change об'єкт команди {@linkplain ChangeConsoleCommand}
     */
    public UndoConsoleCommand(ChangeConsoleCommand change)
    { this.change = change; }

    @Override
    public char getKey()
    { return 'u'; }

    @Override
    public String toString()
    { return "'u'ndo"; }

    @Override
    public void execute()
    {
        System.out.println("Undo last change");
        change.undo();
    }
}
