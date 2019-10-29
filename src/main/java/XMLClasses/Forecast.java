package XMLClasses;

public class Forecast {
    private String date;
    private Night night;
    private Day day;

    public Forecast(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Night getNight() {
        return night;
    }

    public void setNight(Night night) {
        this.night = night;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", night=" + night +
                ", day=" + day +
                '}';
    }
}
