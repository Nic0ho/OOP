package ex03;

import ex02.View;

/**
 * Обчислення та відображення результатів.<br>
 * Містить реалізацію статичного методу main()
 * @author Артем Єдалов
 * @version 3.0
 * @see Main#main
 */
public class Main extends ex02.Main
{
    /** Ініціалізує поле {@linkplain ex02.Main#view view} */
    public Main(View view)
    { super(view); }

    /**
     * Виконується при запуску програми;
     * викликає метод {@linkplain ex02.Main#menu menu()}
     * @param args - параметри запуску програми
     */
    public static void main(String[] args)
    {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}