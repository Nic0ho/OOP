package ex04;

/**
 * Обчислення та відображення результатів.<br>
 * Містить реалізацію статичного методу main()
 * @author Артем Єдалов
 * @version 4.0
 * @see Main#main
 */
public class Main
{
    /**
         * Виконується при запуску програми;
         * викликає метод {@linkplain Application#run()}
         * @param args - параметри запуску програми
        */
    public static void main(String[] args)
    {
        Application app = Application.getInstance();
        app.run();
    }
}