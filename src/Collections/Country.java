package Collections;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private List<City> cities;
    private String countryCode;
    private String name;
    private int countryPopulation;
    private String continentCode;
    private City capital;
    private String countryCode2;

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public Country(String countryCode, String name, int countryPopulation) {
        this.countryCode = countryCode;
        this.name = name;
        this.countryPopulation = countryPopulation;
        this.cities = new ArrayList<>();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(int countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        cities.forEach(city -> {
            if(city.getCountryCode().equals(this.countryCode))
                this.addCity(city);
        });
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    public static List<Country> convertStringToCountry(List<String> countriesString, List<City> cities) {
        List<Country> countries = new ArrayList<>();
        countriesString.forEach(countryString -> {
            String[] s = countryString.split(",");
            String countryCode = s[0].replaceAll(" ","");
            String name = s[1].replaceAll(" ","");
            int population = Integer.parseInt(s[2].replaceAll(" ",""));
            Country c = new Country(countryCode, name, population);
            c.setCities(cities);
            countries.add(c);
        });

        return countries;

    }
}
