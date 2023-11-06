
public class Main
{

    public static void main(String[] args)
    {
        ActivityManager manager = new ActivityManager();
        new ActivityFileReader("activity_data_10.csv",manager);
        new ActivityFileReader("activity_data_50.csv",manager);
        new ActivityFileReader("activity_data_100.csv",manager);
        new ActivityFileReader("activity_data_1000.csv",manager);


    }
}
