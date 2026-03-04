___
# ООП практика - Завдання 2 - Єдалов Артем
# Класи та об'єкти. Агрегування. Серіалізація
## Постановка задачі
### Визначити суму площ рівностороннього трикутника та рівностороннього прямокутника за заданою довжиною сторони у двійковій системі числення.
1. Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі.
2. Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів.
3. Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації.
___
# Опис проєкту
### Структура
![project hierarchy](img/hierarchy.png)
#### **Calc.java** - містить реалізацію методів для обчислення задачі і відображення результатів 
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
package ex01;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Містить реалізацію методів для обчислення та відображення результатів.
 * @author Артем Єдалов
 * @version 1.0
 */
public class Calc
{
    /** Ім'я файлу, що використовується при серіалізації. */
    private static final String FNAME = "Item2d.bin";

    /** Зберігає результат обчислень. Об'єкт класу {@linkplain Item2d} */
    private Item2d result;

    /** Ініціалізує {@linkplain Calc#result} */
    public Calc()
    { result = new Item2d(); }

    /**
     * Встановити значення {@linkplain Calc#result}
     * @param result - нове значення посилання на об'єкт {@linkplain Item2d}
     */
    public void setResult(Item2d result)
    { this.result = result; }

    /**
     * Отримати значення {@linkplain Calc#result}
     * @return поточне значення посилання на об'єкт {@linkplain Item2d}
     */
    public Item2d getResult()
    { return result; }

    /**
     * Обчислює площу рівностороннього трикутника.
     * @param side - довжина сторони
     * @return площа трикутника
     */
    private double triangleS(double side)
    { return (Math.pow(side, 2) * Math.sqrt(3) / 4.0); }

    /**
     * Обчислює площу рівностороннього прямокутника, або ж квадрата.
     * @param side - довжина сторони
     * @return площа квадрата
     */
    private double rectangleS(double side)
    { return Math.pow(side, 2); }

    /**
     * Обчислює суму площ та зберігає результат в {@linkplain Calc#result}
     * @param side - довжина сторони
     * @return результат обчислення
     */
    public double init(double side)
    {
        result.setX(side);
        return result.setY(triangleS(side) + rectangleS(side));
    }

    /** Виводить результат обчислень. */
    public void show()
    { System.out.println(result); }
    
    /**
     * Зберігає {@linkplain Calc#result} у файлі {@linkplain Calc#FNAME}
     * @throws IOException
     */
    public void save() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    /**
     * Відновлює {@linkplain Calc#result} з файлу {@linkplain Calc#FNAME}
     * @throws Exception
     */
    public void restore() throws Exception
    {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d) is.readObject();
        is.close();
    }
}
```

</details>

#### **Item2d.java** - містить вихідні дані та результати обчислень
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
package ex01;

import java.io.Serializable;

/**
 * Зберігає вхідні дані та результат обчислень.
 * @author Артем Єдалов
 * @version 1.0
 */
public class Item2d implements Serializable
{
    /** Аргумент обчислюваної функції. Не серіалізується через особливість transient. */
    private transient double x;

    /** Результат обчислення функції. */
    private double y;

    /** Автоматично згенерована константа */
    private static final long serialVersionUID = 1L;

    /** Ініціалізує поля {@linkplain Item2d#x}, {@linkplain Item2d#y} нулями */
    public Item2d()
    { x = .0; y = .0; }

    /**
     * Встановлює значення аргументу та результату.
     * @param x - значення для {@linkplain Item2d#x}
     * @param y - значення для {@linkplain Item2d#y}
     */
    public Item2d(double x, double y)
    { this.x = x; this.y = y; }

    /**
     * Встановлює значення поля {@linkplain Item2d#x}
     * @param x - нове значення
     * @return встановлене значення
     */
    public double setX (double x)
    { return this.x = x; }
    
    /**
     * Встановлює значення поля {@linkplain Item2d#y}
     * @param y - нове значення
     * @return встановлене значення
     */
    public double setY (double y)
    { return this.y = y; }

    /**
     * Отримати значення поля {@linkplain Item2d#x}
     * @return значення x
     */
    public double getX()
    { return this.x; }

    /**
     * Отримати значення поля {@linkplain Item2d#y}
     * @return значення y
     */
    public double getY()
    { return this.y; }

    /**
     * Встановлює обидва поля одночасно.
     * @param x - значення для {@linkplain Item2d#x}
     * @param y - значення для {@linkplain Item2d#y}
     * @return this
     */
    public Item2d setXY(double x, double y)
    { this.x = x; this.y = y; return this; }

    /** Автоматично згенерований метод.<br>{@inheritDoc} */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Item2d other = (Item2d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
        if (Math.abs(Math.abs(y) - Math.abs(other.y)) > .1e-10) return false;
        return true;
    }

    /** Представляє результат у вигляді рядка з двійковим поданням. {@inheritDoc} */
    @Override
    public String toString()
    {
        long intVal = (long) y;
        return "side = " + x + ", sum = " + y + ", binary = " + Long.toBinaryString(intVal);
    }
}
```

</details>


#### **Main.java** - містить обчислення і відображення результатів та реалізацію статичного метода main()
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
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
```
</details>

> **test\ex01**
#### **MainTest.java** - виконує тестування розроблених класів
details>
<summary>MainTest.java</summary>

```java
package test.ex01;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import ex01.Calc;

/**
 * Виконує тестування розроблених класів.
 * @author Артем Єдалов
 * @version 1.0
 */

public class MainTest
{
    /** Перевірка коректності обчислення суми площ. (головної функціональності класу {@linkplain Calc}) */
    @Test
    public void testCalc()
    {
        Calc calc = new Calc();

        calc.init(0.0);
        assertEquals(0.0, calc.getResult().getY(), .1e-10);

        calc.init(1.0);
        assertEquals(Math.sqrt(3) / 4.0 + 1.0, calc.getResult().getY(), .1e-10);

        calc.init(2.0);
        assertEquals(Math.sqrt(3) + 4.0, calc.getResult().getY(), .1e-10);
    }

    /** Перевірка серіалізації та демонстрація поведінки transient поля. */
    @Test
    public void testRestore()
    {
        Calc calc = new Calc();
        double x, y;
        for(int ctr = 0; ctr < 1000; ctr++)
        {
            x = Math.random() * 100.0;
            y = calc.init(x);

            try { calc.save(); }
            catch (IOException e) { Assert.fail(e.getMessage()); }

            calc.init(Math.random() * 100.0);

            try { calc.restore(); }
            catch (Exception e) { Assert.fail(e.getMessage()); }

            assertEquals(y, calc.getResult().getY(), .1e-10);
            /** x є transient тому після відновлення завжди 0.0 */
            assertEquals(0.0, calc.getResult().getX(), .1e-10);
        }
    }
}
```
</details>

___
# Приклад роботи
### При звичайному запуску:
![example1](img/example1.png)
### При запуску + дебаг:
![test example](img/testexample.png)
### Результати тесту через JUnit Test:
![test result 1](img/testres1.png)
![test result 2](img/testres2.png)
___