package XMLReader;

import XMLClasses.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class XMLReader {
    //List of all forecasts
    private List<Forecast> forecastsList = new ArrayList<>();
    //Method that processes the xml file
    List<Forecast> parseForecasts() {
        try {
            //Get XML data from ilmateenistus
            String link = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
            //Get rid of the 403 error, make a connection to the website
            URLConnection connection = new URL(link).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Use input stream instead of link so there won't be any errors
            Document doc = builder.parse(connection.getInputStream());
            //Normalize the XML
            doc.getDocumentElement().normalize();
            //Make new forecasts
            NodeList nodeForecast = doc.getElementsByTagName("forecast");
            //Read day information
            NodeList nodeDay = doc.getElementsByTagName("day");
            //Read night information
            NodeList nodeNight = doc.getElementsByTagName("night");
            //Iterate over all the forecasts and construct them
            for (int i=0; i<nodeForecast.getLength(); i++) {
                //Create the necessary nodes
                Node nodeF = nodeForecast.item(i);
                Node nodeD = nodeDay.item(i);
                Node nodeN = nodeNight.item(i);
                //Check forecast
                if(nodeF.getNodeType() == Node.ELEMENT_NODE) {
                    //Create the elements
                    Element elemF = (Element) nodeF;
                    Element elemD = (Element) nodeD;
                    Element elemN = (Element) nodeN;
                    //Set date
                    Forecast forecast = new Forecast(elemF.getAttribute("date"));
                    //Process days parameters
                    if(nodeD.getNodeType() == Node.ELEMENT_NODE) {
                        //New day constructor
                        Day day = new Day();
                        day.setPhenomenon(setDay("phenomenon", elemD));
                        day.setTempmin(Integer.parseInt(Objects.requireNonNull(setDay("tempmin", elemD))));
                        day.setTempmax(Integer.parseInt(Objects.requireNonNull(setDay("tempmax", elemD))));
                        day.setText(setDay("text", elemD));
                        day.setSea(setDay("sea", elemD));
                        day.setPeipsi(setDay("peipsi", elemD));
                        //Create lists to contain wind and places data
                        List<Place> places = new ArrayList<>();
                        List<Wind> winds = new ArrayList<>();
                        //Add places and wind data to lists
                        PlacesWind(elemD, places, winds);
                        forecast.setDay(day);
                        day.setPlaces(places);
                        day.setWinds(winds);
                    }
                    //Set night element and process it's elements
                    if(nodeN.getNodeType() == Node.ELEMENT_NODE) {
                        //New night constructor
                        Night night = new Night();
                        night.setPhenomenon(setNight("phenomenon", elemN));
                        night.setTempmin(Integer.parseInt(Objects.requireNonNull(setNight("tempmin", elemN))));
                        night.setTempmax(Integer.parseInt(Objects.requireNonNull(setNight("tempmax", elemN))));
                        night.setText(setNight("text", elemN));
                        night.setSea(setNight("sea", elemN));
                        night.setPeipsi(setNight("peipsi", elemN));
                        //Create lists to contain wind and places data
                        List<Place> places = new ArrayList<>();
                        List<Wind> winds = new ArrayList<>();
                        //Add places and wind data to lists
                        PlacesWind(elemD, places, winds);
                        forecast.setNight(night);
                        night.setPlaces(places);
                        night.setWinds(winds);
                    }
                    forecastsList.add(forecast);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return forecastsList;
    }

    private void PlacesWind(Element elemD, List<Place> places, List<Wind> winds) {
        for (int j=0; j<elemD.getElementsByTagName("place").getLength(); j++) {
            Place place = new Place();
            String string = elemD.getElementsByTagName("place").item(j).getTextContent();
            string = string.replaceAll("\\s+", " ").trim();
            String[] parts = string.trim().split(" ");
            StringBuilder phenomenon = new StringBuilder();
            for (int k=1; k<parts.length-1; k++) {
                phenomenon.append(parts[k]).append(" ");
            }
            place.setName(parts[0]);
            place.setPhenomenon(phenomenon.toString());
            place.setTempmin(Integer.parseInt(parts[parts.length-1]));
            places.add(place);
        }
        for (int j=0; j<elemD.getElementsByTagName("wind").getLength(); j++) {
            Wind wind = new Wind();
            String string = elemD.getElementsByTagName("wind").item(j).getTextContent();
            string = string.replaceAll("\\s+", " ").trim();
            String[] parts = string.trim().split(" ");
            StringBuilder direction = new StringBuilder();
            for (int k=1; k<parts.length-3; k++) {
                direction.append(parts[k]).append(" ");
            }
            wind.setDirection(direction.toString());
            wind.setName(parts[0]);
            wind.setSpeedmax(Integer.parseInt(parts[parts.length-1]));
            wind.setSpeedmin(Integer.parseInt(parts[parts.length-2]));
            winds.add(wind);
        }
    }

    //Retrieve data about day elements
    private String setDay(String parameter, Element elemD) {
        if (elemD.getElementsByTagName(parameter).getLength() != 0) {
            return elemD.getElementsByTagName(parameter).item(0).getTextContent();
        }
        return null;
    }
    //Retrieve data about night elements
    private String setNight(String parameter, Element elemN) {

        if (elemN.getElementsByTagName(parameter).getLength() != 0) {
            return elemN.getElementsByTagName(parameter).item(0).getTextContent();
        }
        return null;
    }
}