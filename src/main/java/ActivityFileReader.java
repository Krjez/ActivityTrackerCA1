import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ActivityFileReader
{
    private static final Pattern pattern = Pattern.compile("\s*[A-Za-z]+,\s*[0-9]+/[0-9]+/[0-9]+,\s*[0-9]+,\s*[0-9]*.[0-9]+,\s*[0-9]+\s*");

    ActivityFileReader(String fileName, ActivityManager manager)
    {
        try(Scanner input = new Scanner(new File(fileName)))
        {
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                if(!line.matches(pattern.pattern()))
                {
                    System.out.println("invalid line - " + line);
                    continue;
                }

                String[] split = line.split("\s*,\s*");
                manager.AddActivity(
                        split[0],
                        split[1],
                        Integer.parseInt(split[2]),
                        Double.parseDouble(split[3]),
                        Integer.parseInt(split[4]));
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Exception - file not found\n" + exception);
        }
    }

}