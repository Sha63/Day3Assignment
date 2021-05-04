package Collections;

import java.util.ArrayList;
import java.util.List;

public class City {
    private int cityId;
    private String cityName;
    private int cityPopulation;
    private String countryCode;
    private static int count = 1;

    public City(int cityId, String cityName, int cityPopulation, String countryCode) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityPopulation = cityPopulation;
        this.countryCode = countryCode;
        count++;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(int cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static List<City> convertStringToCity(List<String> citiesString) {
        List<City> cities = new ArrayList<>();
        citiesString.forEach(cityString -> {
            String[] s = cityString.split(",");
            int id = City.count;
            String name = s[1].replaceAll(" ","");
            int population = Integer.parseInt(s[2].replaceAll(" ",""));
            String countryCode = s[3].replaceAll(" ","");
            City c = new City(id, name, population, countryCode);
            cities.add(c);
        });

        return cities;

    }
}
