import java.time.format.DateTimeFormatter;

public class Activity implements Comparable<Activity>
{

    private  ActivityType type; //type of known activity - running/swimming/cycling - enum
    private String date; //when the activity was performed in dd/mm/yyyy
    //TODO if needed use DateTimeFormatter with patter "dd/MM/yyyy"
    private int duration; //time taken to perform the activity in minutes
    private double distance; //distance travelled in kilometers
    private int averageHeartRate; //average heart rate during the activity
    private Intensity intensity;
    private double caloriesBurned;


    //Getters and setters
    public ActivityType getType()
    {
        return type;
    }
    public String getDate() { return date; }
    public int getDuration()
    {
        return duration;
    }
    public double getDistance()
    {
        return distance;
    }
    public int getAverageHeartRate() { return averageHeartRate; }
    public Intensity getIntensity() { return intensity; }
    public double getCaloriesBurned() { return caloriesBurned; }

    //Full constructor
    public Activity(ActivityType type, String date, int duration, double distance, int averageHeartRate)
    {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
        this.intensity = calculateIntensity();
        this.caloriesBurned = calculateCaloriesBurned();
    }
    public Intensity calculateIntensity()
    {
        Intensity intensity;
        double kmph = this.distance/(this.duration/60d);

        double[] intensityType = switch(type)
        {
            case CYCLING -> new double[] {8, 16, 17, 25, 33};
            case RUNNING -> new double[] {4, 8, 12, 16, 24};
            case SWIMMING -> new double[] {0.5, 1.25, 2, 2.75, 3.5};
        };

        intensity = (kmph < intensityType[0]) ? Intensity.VERY_LIGHT :
                    (kmph < intensityType[1]) ? Intensity.LIGHT :
                    (kmph < intensityType[2]) ? Intensity.MODERATE :
                    (kmph < intensityType[3]) ? Intensity.VIGOROUS :
                    (kmph < intensityType[4]) ? Intensity.VERY_VIGOROUS :
                                                Intensity.EXTREME;

        return intensity;
    }

    public double calculateCaloriesBurned()
    {
        double caloriesBurned;

        double[] intensityCalories = switch(type)
        {
            case CYCLING -> new double[] {2, 5, 7, 13, 15, 20};
            case RUNNING -> new double[] {4.1, 7.2, 10, 15.4, 20.8, 27};
            case SWIMMING -> new double[] {5, 6.3, 7.6, 8.9, 10.2, 11.5};
        };

        caloriesBurned = intensityCalories[getIntensity().ordinal()];
        caloriesBurned *= duration;

        return caloriesBurned;
    }

    //TODO default compareTo using Date
    public int compareTo(Activity o)
    {
        return this.date.compareTo(o.date);
    }

    @Override
    public String toString()
    {
        return ("%10s %10s %10s %10.2f %10.1f").formatted(date, type, duration, distance, getCaloriesBurned());
    }


}
