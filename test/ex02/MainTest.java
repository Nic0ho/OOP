package test.ex02;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import java.io.IOException;
import ex01.Item2d;
import ex02.ViewResult;

public class MainTest
{
    @Test
    public void testCalc()
    {
        ViewResult view = new ViewResult(5);
        view.init(1.0);
        Item2d item = new Item2d();
        int ctr = 0;

        item.setXY(0.0, 0.0);
        assertTrue(view.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(1.0, Math.pow(1,2)*Math.sqrt(3)/4.0 + Math.pow(1,2));
        assertTrue(view.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(2.0, Math.pow(2,2)*Math.sqrt(3)/4.0 + Math.pow(2,2));
        assertTrue(view.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(3.0, Math.pow(3,2)*Math.sqrt(3)/4.0 + Math.pow(3,2));
        assertTrue(view.getItems().get(ctr).equals(item));
        ctr++;

        item.setXY(4.0, Math.pow(4,2)*Math.sqrt(3)/4.0 + Math.pow(4,2));
        assertTrue(view.getItems().get(ctr).equals(item));
    }

    @Test
    public void testRestore()
    {
        ViewResult view1 = new ViewResult(1000);
        ViewResult view2 = new ViewResult();
        
        view1.init(Math.random()*100.0);
        try
        { view1.viewSave(); }

        catch (IOException e)
        { Assert.fail(e.getMessage()); }

        try
        { view2.viewRestore(); }

        catch (Exception e)
        { Assert.fail(e.getMessage()); }

        assertEquals(view1.getItems().size(), view2.getItems().size());
        assertTrue("containsAll()", view1.getItems().containsAll(view2.getItems()));
    }
}
