package test.ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import ex01.Item2d;
import ex03.ViewTable;

public class MainTest
{
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
    
     @Test
    public void testRestore()
    {
        ViewTable tbl1 = new ViewTable(40, 1000);
        ViewTable tbl2 = new ViewTable();

        tbl1.init(30, Math.random()*100.0);

        try
        { tbl1.viewSave(); }
        catch (IOException e)
        { fail(e.getMessage()); }

        try
        { tbl2.viewRestore(); }
        catch (Exception e)
        { fail(e.getMessage()); }

        assertEquals(tbl1.getItems().size(), tbl2.getItems().size());

        for (int i = 0; i < tbl1.getItems().size(); i++)
            assertEquals("y mismatch at index " + i, tbl1.getItems().get(i).getY(), tbl2.getItems().get(i).getY(), 1e-10);
    }
}
