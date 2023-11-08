import java.util.ArrayList;
import java.util.Scanner;

public class ActivityMenu
{
    private final ArrayList<String> options;
    private final ArrayList<Response> responses;
    public ActivityMenu()
    {
        options = new ArrayList<>();
        responses = new ArrayList<>();
    }

    public static String getInput(String out, String pattern)
    {
        Scanner input = new Scanner(System.in);
        System.out.print(out);
        while(input.hasNextLine())
        {
            String line = input.nextLine();
            if(line.toLowerCase().matches(pattern.toLowerCase()))
            {
                return line;
            }
            else
            {
                System.out.println("Invalid input");
                input = new Scanner(System.in);
            }
        }
        return "Error";
    }

    public void addOption(String text, Response response)
    {
        options.add(options.size() + ": " + text);
        responses.add(response);
    }

    public void createMenu()
    {
        System.out.println();
        options.forEach(System.out::println);
        String index = getInput("Select option: ", "[0-" + (options.size()-1) + "]");
        responses.get(Integer.parseInt(index)).invoke();
    }
}
