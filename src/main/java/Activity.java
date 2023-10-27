public class Activity
{

    private  ActivityType type; //type of known activity - running/swimming/cycling - enum
    private String date; //when the activity was performed in dd/mm/yyyy
    //TODO if needed use DateTimeFormatter with patter "dd/MM/yyyy"
    private int duration; //time taken to perform the activity in minutes
    private double distance; //distance travelled in kilometers
    private int averageHeartRate; //average heart rate during the activity




    //Getters and setters
    public ActivityType getType()
    {
        return type;
    }

    public void setType(ActivityType type)
    {
        this.type = type;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public int getAverageHeartRate()
    {
        return averageHeartRate;
    }

    public void setAverageHeartRate(int averageHeartRate)
    {
        this.averageHeartRate = averageHeartRate;
    }

    //Intensity and
    public Intensity getIntensity()
    {
        Intensity intensity;
        double kmph = this.distance/(this.duration/60d);

        double[] intensityType = switch(type)
        {
            case SWIMMING -> new double[] {0.5, 1.25, 2, 2.75, 3.5};
            case RUNNING -> new double[] {4, 8, 12, 16, 24};
            case CYCLING -> new double[] {8, 16, 17, 25, 33};
        };

        intensity = (kmph < intensityType[0]) ? Intensity.VERY_LIGHT :
                    (kmph < intensityType[1]) ? Intensity.LIGHT :
                    (kmph < intensityType[2]) ? Intensity.MODERATE :
                    (kmph < intensityType[3]) ? Intensity.VIGOROUS :
                    (kmph < intensityType[4]) ? Intensity.VERY_VIGOROUS :
                                                Intensity.EXTREME;

        return intensity;
    }

    public double getCaloriesBurned()
    {
        double caloriesBurned;

        double[] intensityCalories = switch(type)
        {
            case SWIMMING -> new double[] {5, 6.3, 7.6, 8.9, 10.2, 11.5};
            case RUNNING -> new double[] {4.1, 7.2, 10, 15.4, 20.8, 27};
            case CYCLING -> new double[] {2, 5, 7, 13, 15, 20};
        };

        caloriesBurned = intensityCalories[getIntensity().ordinal()];
        caloriesBurned *= duration;

        return caloriesBurned;
    }


    //Full constructor
    public Activity(ActivityType type, String date, int duration, double distance, int averageHeartRate)
    {
        this.type = type;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.averageHeartRate = averageHeartRate;
    }



}