import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ActivityFileReader
{
    private static final Pattern pattern = Pattern.compile("[A-Za-z]+,\s+[0-9]+/[0-9]+/[0-9]+,\s+[0-9]+,\s+[0-9]*\.[0-9]+,\s+[0-9]+");

    ActivityFileReader(String fileName, ActivityManager manager)
    {
        try(Scanner input = new Scanner(new File(fileName)))
        {
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                Pattern.matches(line, )
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Exception - file not found\n" + exception);
        }
    }

}