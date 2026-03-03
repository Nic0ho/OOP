package src;
import java.util.Scanner;
import java.util.Random;

public class numberguess
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int score = 0;
        
        while(true)
        {
            System.out.println("==Guess the number!==\n--Current score: " + score + "--");
            
            Random random = new Random();
            int x = random.nextInt(11);

            int counter = 3;

            int guess = -1;
            while(guess != x)
            {
                guess = scanner.nextInt();
                if (guess != x & counter == 0) { System.out.println("You loose and lost your score (10)!\n"); counter = -1; break; }
                if (guess < x) { System.out.println("Try again (higher), you have " + counter + " tries left"); counter--; }
                if (guess > x) { System.out.println("Try again (smaller), you have " + counter + " tries left"); counter--; }
            }
            score += counter * 10;
            if (counter != -1) System.out.println("Congratulations, you guessed the number correctly!\n");
        }
    }
}


