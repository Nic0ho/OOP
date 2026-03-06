package ex04;

import ex01.Item2d;
import ex02.View;
import ex02.ViewResult;

public class ChangeConsoleCommand extends ChangeItemCommand implements ConsoleCommand
{
    private View view;

    public View getView()
    { return view; }

    public View setView(View view)
    { return this.view = view; }

    public ChangeConsoleCommand(View view)
    { this.view = view; }

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