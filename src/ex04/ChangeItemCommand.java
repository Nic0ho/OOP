package ex04;

import ex01.Item2d;

/**
 * Команда масштабування елемента; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
 */
public class ChangeItemCommand implements Command
{
    /** Об'єкт що обробляється; шаблон Command */
    private Item2d item;

    /** Параметр команди; шаблон Command */
    private double offset;

    /**
     * Встановлює поле {@linkplain ChangeItemCommand#item}
     * @param item значення для {@linkplain ChangeItemCommand#item}
     * @return нове значення {@linkplain ChangeItemCommand#item}
     */
    public Item2d setItem(Item2d item)
    { return this.item = item; }

    /**
     * Повертає поле {@linkplain ChangeItemCommand#item}
     * @return значення {@linkplain ChangeItemCommand#item}
     */
    public Item2d getItem()
    { return item; }

    /**
     * Встановлює поле {@linkplain ChangeItemCommand#offset}
     * @param offset значення для {@linkplain ChangeItemCommand#offset}
     * @return нове значення {@linkplain ChangeItemCommand#offset}
     */
    public double setOffset(double offset)
    { return this.offset = offset; }

    /**
     * Повертає поле {@linkplain ChangeItemCommand#offset}
     * @return значення {@linkplain ChangeItemCommand#offset}
     */
    public double getOffset()
    { return offset; }

    @Override
    public void execute()
    { item.setY(item.getY() * offset); }
}