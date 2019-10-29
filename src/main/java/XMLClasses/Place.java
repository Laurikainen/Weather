package XMLClasses;

public class Place {
    private String name;
    private String phenomenon;
    private int tempmin;

    public Place() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public int getTempmin() {
        return tempmin;
    }

    public void setTempmin(int tempmin) {
        this.tempmin = tempmin;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", phenomenon='" + phenomenon + '\'' +
                ", tempmin=" + tempmin +
                '}';
    }
}
