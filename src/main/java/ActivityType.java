public enum ActivityType
{
    SWIMMING("Swimming"),
    RUNNING("Running"),
    CYCLING("Cycling");

    private String text;
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



