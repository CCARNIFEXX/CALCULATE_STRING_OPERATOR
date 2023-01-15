import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluator
{

    /**
     * \s* - Могут быть пробелы от нуля до много раз)
     * (\"[^\"]{1,10}\") - Запоминает слово (от одного до десяти символов кроме кавычек)
     *      () - Внутри запомнено и выдано в матчере
     *      \" - Заэкранированный символ кавычек
     *      [^...] - любой символ кроме указанных(любой символ кроме кавычки)
     *      ...{a,b} - Предыдущая часть встречается не менее А раз и не более В раз
     * \s* Могут быть пробелы от нуля до много раз)
     * ([-+/*]) - Запоминаем попавшийся арифметический оператор
     *      [...] - любой символ из указанных(арифметические операции)
     * \s* Могут быть пробелы от нуля до много раз)
     * (\"[^\"]{1,10}\"|[1-9]|10) - Запоминает любой из вариантов строка из одного - десяти символов либо числа от 1 до 10
     *      Qwe|asd|zxc - Совпадает с любым из указанных кусков
     *      \"[^\"]{1,10}\" -  Запоминает слово (от одного до десяти символов кроме кавычек) или ...
     *      [1-9] любая цифра от одного до девяти или
     *      10 - десять
     * \s* Могут быть пробелы от нуля до много раз)
     * @see <a href="https://regexcrossword.com" > Регулярки пробовал тут </a>
     * @see <a href="https://javarush.com/groups/posts/regulyarnye-vyrazheniya-v-java"> Регулярки изучал здесь </a>
     * @see <a href="https://regex101.com/">Регулярки проверял тут </a>
     * @see <a href="https://www.baeldung.com/javadoc-see-vs-link"> Узнал как указывать ссылки тут </a>
     */
    private static final Pattern p = Pattern.compile("\s*(\"[^\"]{1,10}\")\s*([-+*/])\s*(\"[^\"]{1,10}\"|[1-9]|10)\s*");

    /**
     * Получает выражегие, вычисляет его и возвращает результат
     * @param input - строка с входным выражением
     * @return строка с результатом вычисления
     */
    public static String evaluate(String input)
    {
        String[] parts = extractStatement(input);
        String a = parts[0];
        String b = parts[2];
        String operator = parts[1];

        String rawResult = switch (operator)
                {
                    case "+" -> plus(a, b);
                    case "-" -> minus(a, b);
                    case "*" -> multiplication(a, b);
                    default -> divide(a, b);
                };
        return "\"" + rawResult + "\"";
    }

    /**
     * Конкатенация двух строк
     *
     * @param a Первая строка
     * @param b втроя строка
     * @return рез конкатенации
     */
    private static String plus(String a, String b)
    {
        return a + b;
    }

    /**
     * Убирает строку В из строки А
     *
     * @param a - строка из которой убирать
     * @param b - строка которую убирать
     * @return - строка А с убарнными совпалдениями строки В
     */
    private static String minus(String a, String b)
    {
        return a.replace(b, "");
    }

    /**
     * Метод умножения строк
     *
     * @param a - Строка для повторения
     * @param b - Строка с числом количества раз для повторения строки А
     * @return - Строка А, повторенная В раз
     */
    private static String multiplication(String a, String b)
    {
        return a.repeat(Integer.parseInt(b));
    }

    /**
     * Метод сокращения строк
     *
     * @param a - Строка для деления
     * @param b - Строка с числом количества раз для деления строки А
     * @return Сокращение строки А в В раз
     */
    private static String divide(String a, String b)
    {
        return a.substring(0, a.length() / Integer.parseInt(b));
    }

    /**
     * Выделяет из входного выражения три куска
     * Строка с первым операндом
     * Строка с оператором
     * Строка с вторым операндом
     *
     * @param input входное выражение
     * @return Массив из операторов и операндов
     */
    private static String[] extractStatement(String input)
    {
        Matcher m = p.matcher(input);
        if (!m.find()) // Запускает описанный в матчере поиск регулярки внутри строки
        {
            throw new IllegalArgumentException("Некорректный ввод");
        }
        return new String[]{
                m.group(1).replaceAll("\"", ""),
                m.group(2),
                m.group(3).replaceAll("\"", "")
        };
    }
}
