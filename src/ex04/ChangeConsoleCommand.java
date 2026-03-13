package ex04;

import ex01.Item2d;
import ex02.View;
import ex02.ViewResult;

/**
 * Консольна команда Change item; шаблон Command
 * @author Артем Єдалов
 * @version 1.0
 */
public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand
{
    /**
     * Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;

    /**
     * Повертає поле {@linkplain ChangeConsoleCommand#view}
     * @return значення {@linkplain ChangeConsoleCommand#view}
     */
    public View getView()
    { return view; }

    /**
     * Встановлює поле {@linkplain ChangeConsoleCommand#view}
     * @param view значення для {@linkplain ChangeConsoleCommand#view}
     * @return нове значення {@linkplain ChangeConsoleCommand#view}
     */
    public View setView(View view)
    { return this.view = view; }

    /**
     * Ініціалізує поле {@linkplain ChangeConsoleCommand#view}
     * @param view об'єкт, що реалізує інтерфейс {@linkplain View}
     */
    public ChangeConsoleCommand(View view)
    { this.view = view; }

    /**
     * Скасовує операцію масштабування, ділячи кожне значення на {@linkplain ChangeItemCommand#getOffset() offset}.<br>
     * Після скасування задає значення offset до {@code 1.0}.
     */
    public void undo()
    {
        if(getOffset() != 1)
        {
            System.out.println("Undo last change");
            for(Item2d item : ((ViewResult)view).getItems())
                item.setY(item.getY() / getOffset());
            setOffset(1.0);
            view.viewShow();
        }
        else System.out.println("There is nothing to undo");
    }

    @Override
    public char getKey()
    { return 'c'; }

    @Override
    public String toString()
    { return "'c'hange"; }

    @Override
    public void execute()
    {
        System.out.println("Change item: scale factor " + setOffset(Math.random() * 100.0));
        for (Item2d item : ((ViewResult)view).getItems())
        {
            super.setItem(item);
            super.execute();
        }
        view.viewShow();
    }
}