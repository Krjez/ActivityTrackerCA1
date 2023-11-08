import java.util.Comparator;

public enum ActivityOrder {
    CALORIES_ASC(Comparator.comparing(Activity::getCaloriesBurned)),
    CALORIES_DESC(CALORIES_ASC.comparator.reversed()),
    DATE_ASC(Comparator.naturalOrder()),
    DATE_DESC(DATE_ASC.comparator.reversed()),
    DURATION_ASC (Comparator.comparing(Activity::getDuration)),
    DURATION_DESC (DURATION_ASC.comparator.reversed()),
    TYPE_ASC(new TypeComparator()), // Example use of Comparator class
    TYPE_DESC(TYPE_ASC.comparator.reversed()),
    DISTANCE_ASC(
            // Example of anonymous inner class
            new Comparator<>()
            {
                public int compare(Activity a, Activity b)
                {
                    if(a.getDistance() < b.getDistance())
                        return -1;
                    else if(a.getDistance() > b.getDistance())
                        return 1;
                    else
                        return 0;
                }
            }),
    DISTANCE_DESC(DISTANCE_ASC.comparator.reversed());

    private final Comparator<Activity> comparator;
    public Comparator<Activity> getComparator() { return comparator; }
    ActivityOrder(Comparator<Activity> comparator)
    {
        this.comparator = comparator;
    }


    // Example Comparator class
    private static class TypeComparator implements Comparator<Activity>
    {
        public int compare(Activity a, Activity b)
        {
            return a.getType().compareTo(b.getType());
        }
    }

}
