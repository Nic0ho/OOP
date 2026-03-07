package ex04;

/**
 * Інтерфейс консольної команди; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
*/
public interface ConsoleCommand extends Command
{
    /**
     * Гаряча клавіша команди; шаблон Command
     * @return символ гарячої клавіші
    */
    public char getKey();
}