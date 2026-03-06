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
