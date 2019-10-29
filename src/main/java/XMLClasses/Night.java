package XMLClasses;

import java.util.List;

public class Night {
    private String phenomenon;
    private int tempmin;
    private int tempmax;
    private String text;
    private List<Place> places;
    private List<Wind> winds;
    private String sea;
    private String peipsi;

    public Night() { }

    public Night(String phenomenon, int tempmin, int tempmax, String text, String sea, String peipsi) {
        this.phenomenon = phenomenon;
        this.tempmin = tempmin;
        this.tempmax = tempmax;
        this.text = text;
        this.sea = sea;
        this.peipsi = peipsi;
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

    public int getTempmax() {
        return tempmax;
    }

    public void setTempmax(int tempmax) {
        this.tempmax = tempmax;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public List<Wind> getWinds() {
        return winds;
    }

    public void setWinds(List<Wind> winds) {
        this.winds = winds;
    }

    public String getSea() {
        return sea;
    }

    public void setSea(String sea) {
        this.sea = sea;
    }

    public String getPeipsi() {
        return peipsi;
    }

    public void setPeipsi(String peipsi) {
        this.peipsi = peipsi;
    }

    @Override
    public String toString() {
        return "Night{" +
                "phenomenon='" + phenomenon + '\'' +
                ", tempmin=" + tempmin +
                ", tempmax=" + tempmax +
                ", text='" + text + '\'' +
                ", places=" + places +
                ", winds=" + winds +
                ", sea='" + sea + '\'' +
                ", peipsi='" + peipsi + '\'' +
                '}';
    }
}
