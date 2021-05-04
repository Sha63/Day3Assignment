package Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String cityPath = ".\\Cities.txt";
        String countryPath = ".\\Countries.txt";
        String countryContinentPath = ".\\countryContinent.csv";
        String capitalCountryContinentPath = ".\\CapitalCountryContinent.csv";

        List<String> citiesString = Reader.readFile(cityPath, false);
        List<String> countriesString = Reader.readFile(countryPath, false);

        List<City> cities = City.convertStringToCity(citiesString);
        List<Country> countries = Country.convertStringToCountry(countriesString, cities);

        Map<String, List<City>> country_cities = new HashMap<>();
        countries.forEach(country -> {
            country_cities.put(country.getCountryCode(), country.getCities());
        });

        country_cities.forEach((code, dict_cities) -> {
            dict_cities.sort((city1, city2) -> city2.getCityPopulation() - city1.getCityPopulation());
        });

        /*country_cities.forEach((code, dict_cities) -> {
            System.out.println(code + ":");
            dict_cities.forEach((city) -> {
                System.out.print("[" + city.getCityName() + " - " + city.getCityPopulation() + "], ");
            });
            System.out.println();
        });*/

        List<String> countryContinentString = Reader.readFile(countryContinentPath, true);
        List<String> capitalCountryContinentString = Reader.readFile(capitalCountryContinentPath, true);

        Reader.updateCountryContinent(countryContinentString, countries);
        Reader.updateCountryCapital(capitalCountryContinentString, countries);

        List<Continent> continents = Continent.convertStringToContinent(countryContinentString, countries);

        Map<String, List<City>> continent_cities = new HashMap<>();
        for (Continent continent : continents) {
            List<City> cities_collection = new ArrayList<>();
            for (Country country : continent.getCountries()) {
                cities_collection.addAll(country.getCities());
            }
            continent_cities.put(continent.getContinentCode(), cities_collection);
        }


        continent_cities.forEach((code, dict_cities) -> {
            dict_cities.sort((city1, city2) -> city2.getCityPopulation() - city1.getCityPopulation());
        });



        Map<String, City> countryCityPopulation = Reader.highestPopulationCityByCountry(country_cities);
        Map<String, City> continentCityPopulation = Reader.highestPopulationCityByContinent(continent_cities);
        System.out.println("Highest Population Cities by Country");

        country_cities.forEach((code, dict_cities) -> {
            System.out.println(code + ":");
            dict_cities.forEach((city) -> {
                System.out.print("[" + city.getCityName() + " - " + city.getCityPopulation() + "], ");
            });
            System.out.println();
        });

        System.out.println("==========================================================");
        System.out.println("Highest Population Cities by Continent:");
        continent_cities.forEach((code, dict_cities) -> {
            System.out.println(code + ":");
            dict_cities.forEach((city) -> {
                System.out.print("[" + city.getCityName() + " - " + city.getCityPopulation() + "], ");
            });
            System.out.println();
        });

        System.out.println("==========================================================");

        City capital = Reader.highestPopulationCapital(countries);

        System.out.println("Highest Capital Population:");
        System.out.println(capital.getCityName() + " - " + capital.getCityPopulation());

        /*
        7- convert list string to list city  ------
        8- convert list string to list country -------
        10- sort values according to population --------
        15- method highest population city of each country --------
        16- method highest population city by continent  ---------
        17- method highest population capital --------------
        */


    }

}
