import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        Activity newActivity = new Activity(activityType, date, duration, distance, averageHeartRate);
        if(activityList.stream().noneMatch(a -> a.compareTo(newActivity) == 0))
        {
            activityList.add(newActivity);
        }
    }
    public ArrayList<Activity> getFilteredAndSorted(Predicate<Activity> filter, ActivityOrder order)
    {
        return activityList.stream()
                .filter(filter)
                .sorted(order.getComparator())
                .collect(Collectors.toCollection(ArrayList<Activity>::new));
    }

    public double getAverageDistance()
    {
        return activityList.stream()
                .mapToDouble(Activity::getDistance)
                .average()
                .getAsDouble();
    }

    public double getAverageCaloriesBurned()
    {
        return activityList.stream()
                .mapToDouble(Activity::getCaloriesBurned)
                .average()
                .getAsDouble();
    }

    public Activity findActivity(String date, double distance)
    {
        activityList.sort(Comparator.naturalOrder());
        int index = Arrays.binarySearch(activityList.toArray(), new Activity(date, distance));
        return index >= 0 ? activityList.get(index) : null;
    }

}
