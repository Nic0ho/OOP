package ex01;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Обчислення та відображення результатів.
 * Містить реалізацію статичного методу main().
 * @author Артем Єдалов
 * @version 1.0
 * @see Main#main
 */
public class Main
{
    /** Об'єкт класу {@linkplain Calc}. Вирішує задачу обчислити суму площ рівностороннього трикутника та рівностороннього прямокутника за заданою довжиною сторони у двійковій системі числення. */
    private Calc calc = new Calc();

    /** Відображає меню та обробляє команди користувача. */
    private void menu()
    {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do
        {
            do
            {
                System.out.println("Enter command...");
                System.out.print("'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: ");
                try { s = in.readLine(); } 
                catch(IOException e)
                {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            }
            while (s.length() != 1);
            switch (s.charAt(0))
            {
                case 'q':
                    System.out.println("Exit.");
                    calc.show();
                    break;
                case 'v':
                    System.out.println("View current.");
                    calc.show();
                    break;
                case 'g':
                    System.out.println("Random generation.");
                    calc.init((Math.random() * 100.0) + 1);
                    calc.show();
                    break;
                case 's':
                    System.out.println("Save current.");
                    try { calc.save(); }
                    catch (IOException e) { System.out.println("Serialization error: " + e); }
                    calc.show();
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    System.out.println("Before restore: side = " + calc.getResult().getX());
                    try { calc.restore(); }
                    catch (Exception e) { System.out.println("Serialization error: " + e); }
                    System.out.println("After restore (transient x lost): ");
                    calc.show();
                    break;
                default:
                    System.out.print("Wrong command.");
            }
        }
        while (s.charAt(0) != 'q');
    }

    /**
     * Виконується при запуску програми.
     * Вираховується значення функції для різних аргументів.
     * Результати обчислення виводяться на екран.
     * @param args - параметри запуску програми.
     */
    public static void main(String[] args)
    {
         Main main = new Main();
        main.menu();
    }
}