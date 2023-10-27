public enum Intensity
{
    VERY_LIGHT("Very Light"),
    LIGHT("Light"),
    MODERATE("Moderate"),
    VIGOROUS("Vigorous"),
    VERY_VIGOROUS("Very Vigorous"),
    EXTREME("Extreme");

    private String text;
    Intensity(String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }
}


