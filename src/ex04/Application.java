package ex04;

import ex02.View;
import ex03.ViewableTable;

/**
 * Формує та відображає меню; реалізує шаблон Singleton
 * @author Артем Єдалов
 * @version 1.0
*/
public class Application
{
    /**
     * Посилання на екземпляр класу Application; шаблон Singleton
     * @see Application
    */
    private static Application instance = new Application();

    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d};
     * ініціалізується за допомогою Factory Method
    */
    private View view = new ViewableTable().getView();

    /**
     * Об'єкт класу {@linkplain Menu};
     * макрокоманда (шаблон Command)
    */
    private Menu menu = new Menu();

    /**
     * Закритий конструктор; шаблон Singleton
     * @see Application
    */
    private Application() { }
    
    /**
     * Повертає посилання на екземпляр класу Application; шаблон Singleton
     * @return єдиний екземпляр {@linkplain Application}
     * @see Application
    */
    public static Application getInstance()
    { return instance; }

    /**
     * Обробка команд користувача
     * @see Application
    */
    public void run()
    {
        ChangeConsoleCommand change = new ChangeConsoleCommand(view);
        
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(change);
        menu.add(new UndoConsoleCommand(change));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.execute();
    }
}