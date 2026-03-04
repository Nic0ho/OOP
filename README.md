___
# ООП практика - Завдання 3 - Єдалов Артем
# Спадкування. Інтерфейси. Колекції пакету java.util
## Постановка задачі
1. Як основа використовувати вихідний текст проекту попередньої лабораторної роботи. Забезпечити розміщення результатів обчислень уколекції з можливістю збереження/відновлення.
2. Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення рахунок додавання нових відображуваних класів.
3. Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.
4. Реалізувати ці методи виведення результатів у текстовому виде.
5. Розробити тареалізувати інтерфейс для "фабрикуючого" методу.
___
# Опис проєкту
### Структура
![project hierarchy](img/hierarchy.png)
### **src\ex01**
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

### **test\ex01**
#### **MainTest.java** - виконує тестування розроблених класів
<details>
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