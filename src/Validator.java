import java.util.regex.Pattern;

public class Validator
{
    private static final Pattern p = Pattern.compile("\s*(\"[^\"]{1,10}\")\s*([-+]\s*\"[^\"]{1,10}\"|[*/]\s*[1-9]|10)\s*");

    public static void validate(String input)
    {
        if (!p.matcher(input).matches())
        {
            throw new IllegalArgumentException("Некорректная входная строка");
        }
    }

}
