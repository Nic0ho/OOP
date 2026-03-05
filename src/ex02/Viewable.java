package ex02;

/** Creator
 * (шаблон проектування Factory Method)<br>
 * Оголошує метод, що "фабрикує" об'єкти.
 * @author Артем Єдалов
 * @version 1.0
 * @see Viewable#getView()
 */
public interface Viewable
{
    /** Створює об'єкт, що реалізує {@linkplain View} */
    public View getView();
}
