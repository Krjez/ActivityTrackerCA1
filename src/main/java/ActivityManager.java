import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ActivityManager {

    private final ArrayList<Activity> activityList;

    public ActivityManager()
    {
        this.activityList = new ArrayList<>();
    }
    public void AddActivity(String stringType, String date, int duration, double distance, int averageHeartRate)
    {
        ActivityType activityType = ActivityType.valueOf(stringType.toUpperCase());
        activityList.add(new Activity(activityType, date, duration, distance, averageHeartRate));
    }
    public ArrayList<Activity> getFilteredAndSorted(Predicate<Activity> filter, ActivityOrder order)
    {
        return activityList.stream()
                .filter(filter)
                .sorted(order.getComparator())
                .collect(Collectors.toCollection(ArrayList<Activity>::new));

    }
}
