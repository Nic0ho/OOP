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