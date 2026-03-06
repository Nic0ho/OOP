___
# ООП практика - Завдання 5 - Єдалов Артем
# Поліморфізм. Форматований вивід
## Постановка задачі
1. За основу використовувати вихідний текст проекту попередньої лабораторної роботи Використовуючи шаблон проектування Factory Method (Virtual Constructor), розширити ієрархію похідними класами, реалізують методи для подання результатів у вигляді текстової таблиці. Параметри відображення таблиці мають визначатися користувачем.
2. Продемонструвати заміщення (перевизначення, overriding), поєднання (перевантаження, overloading), динамічне призначення методів (Пізнє зв'язування, поліморфізм, dynamic method dispatch).
3. Забезпечити діалоговий інтерфейс із користувачем.
4. Розробити клас для тестування основної функціональності.
5. Використати коментарі для автоматичної генерації документації засобами javadoc.
___
# Опис проєкту
### Структура
#### З пакетів ```ex01``` та ```ex02``` (які були створені у ході виконання попередніх практичних) було використано класи ```ex01.Item2d```, ```ex02.ViewResult```, ```ex02.ViewableResult``` та ```ex02.Main```
![project hierarchy](img/hierarchy.png)
### **src\ex01**
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

### **src\ex02**
#### **Main.java** - містить обчислення і відображення результатів та реалізацію статичного метода ```main()```. 2-га версія класу з пакета ```ex01```, що був створений у ході виконання попередньої практичної.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
package ex02;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/** Обчислення та відображення результатів.<br>
 * Містить реалізацію статичного методу main()
 * @author Артем Єдалов
 * @version 2.0
 * @see Main#main
 */
public class Main
{
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    /** Ініціалізує поле {@linkplain Main#view view}. */
    public Main(View view)
    { this.view = view; }

    /** Відображає меню. */
    protected void menu()
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
                { System.out.println("Error: " + e); System.exit(0); }
            }
            while (s.length() != 1);

            switch (s.charAt(0))
            {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'v':
                    System.out.println("View current.");
                    view.viewShow();
                    break;
                case 'g':
                    System.out.println("Random generation.");
                    view.viewInit();
                    view.viewShow();
                    break;
                case 's':
                    System.out.println("Save current.");
                    try { view.viewSave(); }
                    catch (IOException e) { System.out.println("Serialization error: " + e); }
                    view.viewShow();
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    try { view.viewRestore(); }
                    catch (Exception e) { System.out.println("Serialization error: " + e); }
                    view.viewShow();
                    break;
                default:
                    System.out.println("Wrong command.");
            }
        }
        while(s.charAt(0) != 'q');
    }

    /** Виконується при запуску програми;
     * викликає метод {@linkplain Main#menu() menu()}
     * @param args - параметри запуску програми.
     */
    public static void main(String[] args)
    {
        Main main = new Main(new ViewableResult().getView());
        main.menu();
    }
}
```
</details>

#### **ViewableResult.java** - Шаблон проєктування Factory Method.<br> ConcreteCreator: реалізує фабричний метод ```getView()```, що створює та повертає об'єкт ```ViewResult```.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
package ex02;

/** ConcreteCreator
 * (шаблон проектування Factory Method)<br>
 * Оголошує метод, що "фабрикує" об'єкти.
 * @author Артем Єдалов
 * @version 1.0
 * @see Viewable
 * @see ViewableResult#getView()
 */
public class ViewableResult implements Viewable
{
    /** Створює об'єкт відображення {@linkplain ViewResult} */
    @Override
    public View getView()
    { return new ViewResult(); }
}
```
</details>

#### **ViewResult.java** - реалізує логіку: рахує суми площ, зберігає у ```ArrayList<Item2d>```, серіалізує.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java
package ex02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ex01.Item2d;

/**
 * ConcreteProduct
 * (шаблон проектування Factory Method)<br>
 * Обчислення функції, збереження та відображення результатів.
 * @author Артем Єдалов
 * @version 1.0
 * @see View
 */
public class ViewResult implements View
{
    /** Ім'я файлу, що використовується при серіалізації */
    private static final String FNAME = "items.bin";

    /** Визначає кількість значень для обчислення за замовчуванням */
    private static final int DEFAULT_NUM = 10;

    /** Колекція аргументів та результатів обчислень */
    private ArrayList<Item2d> items = new ArrayList<Item2d>();

    /**
     * Викликає {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)}
     * з параметром {@linkplain ViewResult#DEFAULT_NUM DEFAULT_NUM}
     */
    public ViewResult()
    { this(DEFAULT_NUM); }

    /**
     * Ініціалізує колекцію {@linkplain ViewResult#items}
     * @param n початкова кількість елементів
     */
    public ViewResult(int n)
    {
        for(int ctr = 0; ctr < n; ctr++)
            items.add(new Item2d());
    }

    /**
     * Отримати значення {@linkplain ViewResult#items}
     * @return поточне значення посилання на об'єкт {@linkplain ArrayList}
     */
    public ArrayList<Item2d> getItems()
    { return items; }

    /**
     * Обчислює суму площ рівностороннього трикутника та квадрата.
     * @param side довжина сторони
     * @return результат обчислення
     */
    private double calc(double side)
    { return (Math.pow(side, 2) * Math.sqrt(3) / 4.0) + Math.pow(side, 2); }

    /**
     * Обчислює значення функції та зберігає
     * результат у колекції {@linkplain ViewResult#items}
     * @param stepSide крок приросту аргументу
     */
    public void init(double stepSide)
    {
        double side = 0.0;
        for (Item2d item : items)
        {
            item.setXY(side, calc(side));
            side += stepSide;
        }
    }

    /**
     * Викликає <b>init(double stepSide)</b> з випадковим значенням кроку.<br>
     * {@inheritDoc}
     */
    @Override
    public void viewInit()
    { init((Math.random() * 100.0) + 1); }

    /**
     * Реалізація методу {@linkplain View#viewSave()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewSave() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    /**
     * Реалізація методу {@linkplain View#viewRestore()}<br>
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception
    {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    /**
     * Реалізація методу {@linkplain View#viewHeader()}<br>
     * {@inheritDoc}
     */
    public void viewHeader()
    { System.out.println("Results:"); }

    /**
     * Реалізація методу {@linkplain View#viewBody()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewBody()
    {
        for(Item2d item : items)
            System.out.println(item);
    }

    /**
     * Реалізація методу {@linkplain View#viewFooter()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewFooter()
    { System.out.println("End."); }

    /**
     * Реалізація методу {@linkplain View#viewShow()}<br>
     * {@inheritDoc}
     */
    @Override
    public void viewShow()
    {
        viewHeader();
        viewBody();
        viewFooter();
    }
}
```
</details>

### **src\ex03**
#### **Main.java** - успадковує меню від ```ex02.Main``` та запускає програму з `ViewableTable`. 3-тя версія класу з пакета ```ex02```, що був створений у ході виконання попередньої практичної.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java>
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
```
</details>

#### **ViewableTable.java** - Шаблон проєктування Factory Method.<br> ConcreteCreator: реалізує фабричний метод ```getView()```, що створює та повертає об'єкт ```ViewTable```.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java>
package ex03;

import ex02.ViewableResult;
import ex02.View;

/**
 * ConcreteCreator
 * (шаблон проектування Factory Method)<br>
 * Реалізує фабричний метод {@linkplain ViewableTable#getView() getView()},
 * що створює та повертає об'єкт {@linkplain ViewTable}.
 * @author Артем Єдалов
 * @version 1.0
 * @see ViewableResult
 * @see ViewableTable#getView()
 */
public class ViewableTable extends ViewableResult
{
    /** Створює об'єкт відображення {@linkplain ViewTable} */
    @Override
    public View getView()
    { return new ViewTable(); }
}
```
</details>

#### **ViewTable.java** - Шаблон проєктування Factory Method.<br> Розширює ```ViewResult``` та виводить колекцію у вигляді форматованої текстової таблиці. Демонструє overriding, overloading та поліморфізм.
<details>
<summary>ПЕРЕГЛЯНУТИ</summary>

```java>
package ex03;

import java.util.Formatter;
import ex01.Item2d;
import ex02.ViewResult;

/**
 * ConcreteProduct
 * (шаблон проектування Factory Method)<br>
 * Вивід колекції у вигляді текстової таблиці.
 * @author Артем Єдалов
 * @version 1.0
 * @see ViewResult
 */
public class ViewTable extends ViewResult
{
    /** Визначає ширину таблиці за замовчуванням */
    public static final int DEFAULT_WIDTH = 20;

    /** Поточна ширина таблиці */
    private int width;

    /**
     * Встановлює {@linkplain ViewTable#width width}
     * значенням {@linkplain ViewTable#DEFAULT_WIDTH DEFAULT_WIDTH}.<br>
     * Викликається конструктор суперкласу {@linkplain ViewResult#ViewResult() ViewResult()}
     */
    public ViewTable()
    { width = DEFAULT_WIDTH; }

    /**
     * Встановлює {@linkplain ViewTable#width} значенням <b>width</b>.<br>
     * Викликається конструктор суперкласу {@linkplain ViewResult#ViewResult() ViewResult()}
     * @param width визначає ширину таблиці
     */
    public ViewTable(int width)
    { this.width = width; }

    /**
     * Встановлює {@linkplain ViewTable#width} значенням <b>width</b>.<br>
     * Викликається конструктор суперкласу {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)}
     * @param width визначає ширину таблиці
     * @param n кількість елементів колекції; передається суперконструктору
     */
    public ViewTable(int width, int n)
    {
        super(n);
        this.width = width;
    }

    /**
     * Встановлює поле {@linkplain ViewTable#width} значенням <b>width</b>
     * @param width нова ширина таблиці
     * @return задана параметром <b>width</b> ширина таблиці
     */
    public int setWidth(int width)
    { return this.width = width; }

    /**
     * Повертає значення поля {@linkplain ViewTable#width}
     * @return поточна ширина таблиці
     */
    public int getWidth()
    { return width; }

    /** Виводить горизонтальний розділювач шириною {@linkplain ViewTable#width} символів з переносом рядка */
    private void outLineLn()
    {
        for(int i = width; i > 0; i--)
            System.out.print('-');
        System.out.print("\n");
    }

    /** Виводить заголовок таблиці шириною {@linkplain ViewTable#width} символів */
    private void outHeader()
    {
        try (Formatter fmt = new Formatter())
        {
            fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, "s | %", "s\n");
            System.out.printf(fmt.toString(), "side ", "arrea sum ");
        }
    }

    /** Виводить тіло таблиці шириною {@linkplain ViewTable#width} символів */
    private void outBody()
    {
        try (Formatter fmt = new Formatter())
        {
            fmt.format("%s%d%s%2$d%s", "%", (width-3)/2, ".0f | %", ".3f\n");
            for(Item2d item : getItems())
                System.out.printf(fmt.toString(), item.getX(), item.getY());
        }
    }

    /**
     * Перевантаження (overloading) методу суперкласу;
     * встановлює поле {@linkplain ViewTable#width} значенням <b>width</b>.<br>
     * Викликає метод {@linkplain ViewResult#viewInit() viewInit()}
     * @param width нова ширина таблиці
     */
    public final void init(int width)
    {
        this.width = width;
        viewInit();
    }

    /**
     * Перевантаження (overloading) методу суперкласу;
     * встановлює поле {@linkplain ViewTable#width} значенням <b>width</b>.<br>
     * Викликає метод {@linkplain ViewTable#init(double) init(double)}
     * @param width нова ширина таблиці
     * @param stepSide передається методу <b>init(double)</b>
     */
    public final void init(int width, double stepSide)
    {
        this.width = width;
        init(stepSide);
    }

    /**
     * Перевизначення (overriding) методу суперкласу;
     * виводить інформаційне повідомлення і викликає метод суперкласу
     * {@linkplain ViewResult#init(double) init(double)}.<br>
     * {@inheritDoc}
     */
    @Override
    public void init(double stepSide)
    {
        System.out.print("Initialization...");
        super.init(stepSide);
        System.out.println("done.  ");
    }

    /** Вивід елементу заголовку таблиці.<br>{@inheritDoc} */
    @Override
    public void viewHeader()
    {
        outHeader();
        outLineLn();
    }

    /** Вивід елементу тіла таблиці.<br>{@inheritDoc} */
    @Override
    public void viewBody()
    { outBody(); }

    /** Вивід елементу закінчення таблиці.<br>{@inheritDoc} */
    @Override
    public void viewFooter()
    { outLineLn(); }
}
```
</details>

### **test\ex03**
#### **MainTest.java** - виконує тестування розроблених класів. 3-га версія класу з пакета ```ex01```, що був створений у ході виконання попередньої практичної.
<details>
<summary>MainTest.java</summary>

```java
package test.ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import ex01.Item2d;
import ex03.ViewTable;

/**
 * Виконує тестування розроблених класів.
 * @author Артем Єдалов
 * @version 3.0
 */
public class MainTest
{
    /** Перевірка основної функціональності класу {@linkplain ViewTable} */
    @Test
    public void testCalc()
    {
        ViewTable tbl = new ViewTable(40, 5);
        assertEquals(40, tbl.getWidth());
        assertEquals(5, tbl.getItems().size());
        tbl.init(40, 1.0);

        Item2d item = new Item2d();
        int ctr = 0;

        item.setXY(0.0, 0.0);
        assertTrue("expected:<" + item + "> but was:<" + tbl.getItems().get(ctr) + ">", tbl.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(1.0, Math.pow(1,2)*Math.sqrt(3)/4.0 + Math.pow(1,2));
        assertTrue("expected:<" + item + "> but was:<" + tbl.getItems().get(ctr) + ">", tbl.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(2.0, Math.pow(2,2)*Math.sqrt(3)/4.0 + Math.pow(2,2));
        assertTrue("expected:<" + item + "> but was:<" + tbl.getItems().get(ctr) + ">", tbl.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(3.0, Math.pow(3,2)*Math.sqrt(3)/4.0 + Math.pow(3,2));
        assertTrue("expected:<" + item + "> but was:<" + tbl.getItems().get(ctr) + ">", tbl.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(4.0, Math.pow(4,2)*Math.sqrt(3)/4.0 + Math.pow(4,2));
        assertTrue("expected:<" + item + "> but was:<" + tbl.getItems().get(ctr) + ">", tbl.getItems().get(ctr).equals(item));
    }

    /** Перевірка серіалізації. Коректність відновлення даних. */
    @Test
    public void testRestore()
    {
        ViewTable tbl1 = new ViewTable(40, 1000);
        ViewTable tbl2 = new ViewTable();
        // Обчислимо значення функції з випадковим кроком приросту аргументу
        tbl1.init(30, Math.random()*100.0);
        // Збережемо колекцію tbl1.items
        try
        { tbl1.viewSave(); }
        catch (IOException e)
        { fail(e.getMessage()); }
        // Завантажимо колекцію tbl2.items
        try
        { tbl2.viewRestore(); }
        catch (Exception e)
        { fail(e.getMessage()); }
        // Повинні завантажити стільки ж елементів, скільки зберегли
        assertEquals(tbl1.getItems().size(), tbl2.getItems().size());
        // x є transient — після десеріалізації x = 0.0, тому порівнюємо лише y
        for (int i = 0; i < tbl1.getItems().size(); i++)
            assertEquals("y mismatch at index " + i, tbl1.getItems().get(i).getY(), tbl2.getItems().get(i).getY(), 1e-10);
    }
}
```
</details>

___
# Приклад роботи
### При звичайному запуску:
```
Enter command...
'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: v
View current.
   side  | arrea sum 
--------------------
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
       0 |    0,000
--------------------
Enter command...
'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: g
Random generation.
Initialization...done.  
   side  | arrea sum 
--------------------
       0 |    0,000
      38 | 2055,544
      76 | 8222,178
     114 | 18499,899
     151 | 32888,710
     189 | 51388,610
     227 | 73999,598
     265 | 100721,675
     303 | 131554,840
     341 | 166499,095
--------------------
Enter command...
'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: s
Save current.
   side  | arrea sum
--------------------
       0 |    0,000
      38 | 2055,544
      76 | 8222,178
     114 | 18499,899
     151 | 32888,710
     189 | 51388,610
     227 | 73999,598
     265 | 100721,675
     303 | 131554,840
     341 | 166499,095
--------------------
Enter command...
'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: g
Random generation.
Initialization...done.
   side  | arrea sum
--------------------
       0 |    0,000
      21 |  647,589
      43 | 2590,357
      64 | 5828,304
      85 | 10361,429
     106 | 16189,733
     128 | 23313,215
     149 | 31731,876
     170 | 41445,716
     191 | 52454,734
--------------------
Enter command...
'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore: r
Restore last saved.
   side  | arrea sum
--------------------
       0 |    0,000
       0 | 2055,544
       0 | 8222,178
       0 | 18499,899
       0 | 32888,710
       0 | 51388,610
       0 | 73999,598
       0 | 100721,675
       0 | 131554,840
       0 | 166499,095
--------------------
```
### При запуску + дебаг (для прикладу демонстрована спроба відновити  не існуюче збереження при увімкнених примусових зупинках неочікуваних виключень):
![test example1](img/debug1.png)
![test example2](img/debug2.png)
### Результати тесту через JUnit Test:
![test result 1](img/junit1.png)
![test result 2](img/junit2.png)
___