public enum ActivityType implements Comparable<ActivityType>
{
    CYCLING("Cycling"),
    RUNNING("Running"),
    SWIMMING("Swimming");

    private final String text;
    ActivityType(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }
}



