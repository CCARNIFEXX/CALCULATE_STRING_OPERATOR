import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator
{
    private static final Pattern p = Pattern.compile("\s*(\"[^\"]{1,10}\")\s*([-+*/])\s*(\"[^\"]{1,10}\"|[1-9]|10)\s*");

    public static String evaluate(String input)
    {
      String[] parts =  extractStatement(input);
      String a = parts[1];
      String b = parts[3];
      String operator = parts[2];

        String rawResult = switch (operator)
                {
                    case "+" -> plus(a, b);
                    case "-" -> minus(a, b);
                    case "*" -> multiplication(a, b);
                    default -> divide(a, b);
                };
        return "\"" + rawResult + "\"";
    }

    private static String plus(String a, String b)
    {
        return a + b;
    }

    private static String minus(String a, String b)
    {
        return a.replaceAll(b, "");
    }

    private static String multiplication(String a, String b)
    {
        return a.repeat(Integer.parseInt(b));
    }

    private static String divide(String a, String b)
    {
        return a.substring(0, a.length() / Integer.parseInt(b));
    }

    private static String[] extractStatement(String input)
    {
        Matcher m = p.matcher(input);
        if(!m.find())
        {
            throw new  IllegalArgumentException ("Некорректный ввод");
        }
        return new String[]{
                m.group(1).replaceAll("\"", ""),
                m.group(2),
                m.group(3).replaceAll("\"", "")
        };
    }
}
