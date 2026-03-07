package test.ex04;

import static org.junit.Assert.*;
import org.junit.Test;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.ChangeItemCommand;
import ex04.ChangeConsoleCommand;

public class MainTest
{
    @Test
    public void testExecute()
    {
        ChangeItemCommand cmd = new ChangeItemCommand();
        cmd.setItem(new Item2d());
        double x, y, offset;

        for (int ctr = 0; ctr < 1000; ctr++)
        {
            cmd.getItem().setXY(x = Math.random() * 100.0, y = Math.random() * 100.0);
            cmd.setOffset(offset = Math.random() * 100.0);
            cmd.execute();
            assertEquals(x, cmd.getItem().getX(), 1e-10);
            assertEquals(y * offset, cmd.getItem().getY(), 1e-10);
        }
    }

    @Test
    public void testChangeConsoleCommand()
    {
        ChangeConsoleCommand cmd = new ChangeConsoleCommand(new ViewResult());
        cmd.getView().viewInit();
        cmd.execute();
        assertEquals("'c'hange", cmd.toString());
        assertEquals('c', cmd.getKey());
    }
}