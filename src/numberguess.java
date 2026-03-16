
import java.util.Scanner;
import java.util.Random;

/**
 * Гра "Вгадай число".<br>
 * Програма генерує випадкове число від 0 до 10,
 * гравець має 3 спроби щоб його вгадати.
 * За кожну правильну відповідь нараховуються бали.
 */
public class numberguess
{
    /**
     * Виконується при запуску програми.<br>
     * Реалізує нескінченний ігровий цикл: генерує число, приймає відповіді гравця, оновлює рахунок.
     * @param args параметри запуску програми
     */
    public static void main(String[] args)
    {
        try (Scanner scanner = new Scanner(System.in)) {
            // Поточний рахунок гравця
            int score = 0;
            
            while(true)
            {
                System.out.println("==Guess the number!==\n--Current score: " + score + "--");
                
                Random random = new Random();
                // Загадане випадкове число від 0 до 10
                int x = random.nextInt(11);

                // Кількість спроб що залишилась
                int counter = 3;

                // Остання відповідь гравця
                int guess = -1;
                while(guess != x)
                {
                    guess = scanner.nextInt();
                    if (guess != x & counter == 0)
                    {
                        System.out.println("You loose and lost your score (10)!\n");
                        counter = -1;
                        break;
                    }
                    if (guess < x)
                    {
                        System.out.println("Try again (higher), you have " + counter + " tries left");
                        counter--;
                    }
                    if (guess > x)
                    {
                        System.out.println("Try again (smaller), you have " + counter + " tries left");
                        counter--;
                    }
                }
                score += counter * 10;
                if (counter != -1)
                    System.out.println("Congratulations, you guessed the number correctly!\n");
            }
        }
    }
}





