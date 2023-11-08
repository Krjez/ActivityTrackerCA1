import java.util.function.Predicate;

public class Main
{

    public static void main(String[] args) {
        final boolean[] running = {true};

        ActivityManager manager = new ActivityManager();
        ActivityFileReader fileReader = new ActivityFileReader(manager);
        fileReader.LoadFile("activity_data_10.csv");
        //(fileReader.LoadFile("activity_data_50.csv");
        //fileReader.LoadFile("activity_data_100.csv");


        // Default filter text
        final String[] filterText = {"none"};

        // Default sort text
        final String[] sortText = {"none"};

        // Default order
        final ActivityOrder[] currentSort = {ActivityOrder.DATE_ASC};

        // Default filter
        final Predicate[] currentFilter = {(a) -> true};


        System.out.println("ACTIVITY TRACKER");


        ActivityMenu sortMenu = new ActivityMenu();
        sortMenu.addOption("Activity Type",
                () -> {
                    currentSort[0] = ActivityOrder.TYPE_ASC;
                    sortText[0] = "Activity Type (Asc)";
                });
        sortMenu.addOption("Calories Burned (Descending)",
                () -> {
                    currentSort[0] = ActivityOrder.CALORIES_DESC;
                    sortText[0] = "Calories burned (Desc)";
                });
        sortMenu.addOption("Date (Ascending)",
                () -> {
                    currentSort[0] = ActivityOrder.DATE_ASC;
                    sortText[0] = "Date (Asc)";
                });
        sortMenu.addOption("Date (Descending)",
                () -> {
                    currentSort[0] = ActivityOrder.DATE_DESC;
                    sortText[0] = "Date (Desc)";
                });
        sortMenu.addOption("Activity Duration (Ascending)",
                () -> {
                    currentSort[0] = ActivityOrder.DURATION_ASC;
                    sortText[0] = "Duration (Asc)";
                });
        sortMenu.addOption("Activity Duration (Descending)",
                () -> {
                    currentSort[0] = ActivityOrder.DURATION_DESC;
                    sortText[0] = "Duration (Desc)";
                });
        sortMenu.addOption("Distance (Ascending)",
                () -> {
                    currentSort[0] = ActivityOrder.DISTANCE_ASC;
                    sortText[0] = "Distance (Asc)";
                });
        sortMenu.addOption("Distance (Descending)",
                () -> {
                    currentSort[0] = ActivityOrder.DISTANCE_DESC;
                    sortText[0] = "Distance (Desc)";
                });


        ActivityMenu filterMenu = new ActivityMenu();
        filterMenu.addOption("Clear filter",
                () -> {
                    currentFilter[0] = (a) -> true;
                    filterText[0] = "none";
                });
        filterMenu.addOption("Activity Type filter",
                () -> {
                    String input = ActivityMenu.getInput("Activity: ", "(Swimming|Running|Cycling)");
                    currentFilter[0] = (a) -> ((Activity) a).getType().toString().equalsIgnoreCase(input);
                    filterText[0] = "Activity type - " + input;
                });
        filterMenu.addOption("Minimum Distance",
                () -> {
                    double input = Double.parseDouble(ActivityMenu.getInput("Distance: ", "\\d+|\\d*.\\d+"));
                    currentFilter[0] = (a) -> ((Activity)a).getDistance() > input;
                    filterText[0] = "Minimum Distance - " + input;
                });
        filterMenu.addOption("Intensity",
                () -> {
                    String input = ActivityMenu.getInput("Intensity: ",
                            "(Very Light|Light|Moderate|Vigorous|Very Vigorous|Extreme)");
                    currentFilter[0] = (a) -> ((Activity) a).getIntensity().toString().equalsIgnoreCase(input);
                    filterText[0] = "Intensity - " + input;
                });
        filterMenu.addOption("Minimum Duration",
                () -> {
                    int input = Integer.parseInt(ActivityMenu.getInput("Duration: ", "\\d+"));
                    currentFilter[0] = (a) -> ((Activity)a).getDuration() > input;
                    filterText[0] = "Minimum duration - " + input;
                });

        ActivityMenu menu;

        while(running[0])
        {
            menu = new ActivityMenu();
            menu.addOption("Display",
                    () -> manager.getFilteredAndSorted(currentFilter[0], currentSort[0]).forEach(System.out::println));
            menu.addOption("Sort by: " + sortText[0],
                    sortMenu::createMenu);
            menu.addOption("Filter by: " + filterText[0],
                    filterMenu::createMenu);
            menu.addOption("Display average distance across all",
                    () -> System.out.printf("%.2f%n", manager.getAverageDistance()));
            menu.addOption("Display average calories burned across all",
                    () -> System.out.printf("%.2f%n", manager.getAverageCaloriesBurned()));
            menu.addOption("Find a specific activity",
                    () -> {
                        String date = ActivityMenu.getInput("Date (dd/mm/yyyy): ", "(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([0-9]{4})");
                        double distance = Double.parseDouble(ActivityMenu.getInput("Distance: ", "[0-9]*.[0-9]+"));
                        Activity activity = manager.findActivity(date,distance);
                        System.out.println(activity != null ? activity : "Not found");
                    });
            menu.addOption("Load a new file",
                    () -> {
                        String fileName = ActivityMenu.getInput("File name: ",".+\\.csv");
                        fileReader.LoadFile(fileName);
                    });
            menu.addOption("Quit",
                    () -> running[0] = false);

            menu.createMenu();
        }
    }
}
