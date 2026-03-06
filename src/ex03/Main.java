package ex03;

import ex02.View;

public class Main extends ex02.Main
{
    public Main(View view)
    { super(view); }

    public static void main(String[] args)
    {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}