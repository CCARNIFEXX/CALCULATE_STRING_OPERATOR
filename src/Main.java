import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

public class Main
{
    public static void main(String[] args)
    {
        // Запрос и нормализация
        Scanner scn = new Scanner(System.in);
        out.print("Введите выражение: ");
        String input = scn.nextLine();

        try
        {
            Validator.validate(input);
            String output = Evaluator.evaluate(input);
            out.print("Ваше желание исполнено: " + output);
        } catch (IllegalArgumentException exception)
        {
            err.println("Появилась проблема: " + exception.getMessage());
        }



    }
}