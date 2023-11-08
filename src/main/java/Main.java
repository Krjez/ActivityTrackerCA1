
public class Main
{

    public static void main(String[] args)
    {
        ActivityManager manager = new ActivityManager();
        ActivityFileReader fileReader = new ActivityFileReader(manager);
        fileReader.LoadFile("activity_data_10.csv");
        fileReader.LoadFile("activity_data_50.csv");
        fileReader.LoadFile("activity_data_100.csv");
        fileReader.LoadFile("activity_data_1000.csv");

        manager.getFilteredAndSorted(
                a -> a.getType() == ActivityType.SWIMMING,
                ActivityOrder.CALORIES_DESC)
                .forEach(System.out::println);




    }
}
