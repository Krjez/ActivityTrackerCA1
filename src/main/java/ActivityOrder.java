import java.util.Comparator;

public enum ActivityOrder {
    CALORIES_ASC(Comparator.comparing(Activity::getCaloriesBurned)),
    CALORIES_DESC(CALORIES_ASC.comparator.reversed()),
    DATE_ASC(Comparator.naturalOrder()),
    DATE_DESC(DATE_ASC.comparator.reversed()),
    DURATION_ASC (Comparator.comparing(Activity::getDuration)),
    DURATION_DESC (DURATION_ASC.comparator.reversed()),
    TYPE_ASC(Comparator.comparing(Activity::getType)),
    TYPE_DESC(TYPE_ASC.comparator.reversed()),
    DISTANCE_ASC(Comparator.comparing(Activity::getDistance)),
    DISTANCE_DESC(DISTANCE_ASC.comparator.reversed());

    private final Comparator<Activity> comparator;
    public Comparator<Activity> getComparator() { return comparator; }
    ActivityOrder(Comparator<Activity> comparator)
    {
        this.comparator = comparator;
    }
}
