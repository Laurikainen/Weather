package XMLClasses;

public class Wind {
    private String name;
    private String direction;
    private int speedmin;
    private int speedmax;

    public Wind() { }

    public Wind(String name, String direction, int speedmin, int speedmax) {
        this.name = name;
        this.direction = direction;
        this.speedmin = speedmin;
        this.speedmax = speedmax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeedmin() {
        return speedmin;
    }

    public void setSpeedmin(int speedmin) {
        this.speedmin = speedmin;
    }

    public int getSpeedmax() {
        return speedmax;
    }

    public void setSpeedmax(int speedmax) {
        this.speedmax = speedmax;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", speedmin=" + speedmin +
                ", speedmax=" + speedmax +
                '}';
    }
}
