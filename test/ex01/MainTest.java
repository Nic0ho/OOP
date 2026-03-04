package test.ex01;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import ex01.Calc;

/**
 * Виконує тестування розроблених класів.
 * @author Артем Єдалов
 * @version 1.0
 */

public class MainTest
{
    /** Перевірка коректності обчислення суми площ. (головної функціональності класу {@linkplain Calc}) */
    @Test
    public void testCalc()
    {
        Calc calc = new Calc();

        calc.init(0.0);
        assertEquals(0.0, calc.getResult().getY(), .1e-10);

        calc.init(1.0);
        assertEquals(Math.sqrt(3) / 4.0 + 1.0, calc.getResult().getY(), .1e-10);

        calc.init(2.0);
        assertEquals(Math.sqrt(3) + 4.0, calc.getResult().getY(), .1e-10);
    }

    /** Перевірка серіалізації та демонстрація поведінки transient поля. */
    @Test
    public void testRestore()
    {
        Calc calc = new Calc();
        double x, y;
        for(int ctr = 0; ctr < 1000; ctr++)
        {
            x = Math.random() * 100.0;
            y = calc.init(x);

            try { calc.save(); }
            catch (IOException e) { Assert.fail(e.getMessage()); }

            calc.init(Math.random() * 100.0);

            try { calc.restore(); }
            catch (Exception e) { Assert.fail(e.getMessage()); }

            assertEquals(y, calc.getResult().getY(), .1e-10);
            /** x є transient тому після відновлення завжди 0.0 */
            assertEquals(0.0, calc.getResult().getX(), .1e-10);
        }
    }
}