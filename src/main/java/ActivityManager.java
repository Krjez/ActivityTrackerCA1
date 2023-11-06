import java.util.ArrayList;

public class ActivityManager {

    private ArrayList<Activity> activityList;




    public void AddActivity(String stringType, String date, int duration, double distance, int averageHeartRate)
    {
        ActivityType activityType = ActivityType.valueOf(stringType);
        activityList.add(new Activity(activityType, date, duration, distance, averageHeartRate));
    }

    public ActivityManager()
    {
        this.activityList = new ArrayList<>();
    }
}
