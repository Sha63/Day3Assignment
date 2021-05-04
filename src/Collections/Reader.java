package Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Reader {

    public static List<String> readFile(String path, boolean withHeader) {
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr);) {
            //String[] arr = new String[];
            String sCurrentLine;
            if(withHeader)
                br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                lines.add(sCurrentLine);
            }

            return lines;
        } catch (IOException e) {
            e.printStackTrace();
            return lines;
        }
    }

    public static void updateCountryContinent(List<String> countryContinentString, List<Country> countries) {
        countryContinentString.forEach((countryContinent) -> {
            String delim = ",";
            String esc = "\"";
            String regex = "(?<!" + Pattern.quote(esc) + ")" + Pattern.quote(delim);
            String[] s = countryContinent.split(regex);
            if(s.length > 5) {
                Country c = getCountry(s[2].trim(), countries);
                if (c != null) {
                    c.setContinentCode(s[5].trim());
                    c.setCountryCode2(s[1].trim());
                }
            }
        });
    }

    public static City getCity(String code, List<City> cities) {
        for (City city : cities) {
            if(city.getCountryCode().equals(code))
                return city;
        }

        return null;
    }

    public static void updateCountryCapital(List<String> capitalsString, List<Country> countries) {
        capitalsString.forEach((capitalString) -> {
            String delim = ",";
            String esc = "\"";
            String regex = "(?<!" + Pattern.quote(esc) + ")" + Pattern.quote(delim);
            String[] s = capitalString.split(regex);
            Country c = getCountry2(s[4].trim(), countries);
            if (c != null) {
                City capital = getCity(c.getCountryCode(), c.getCities());
                c.setCapital(capital);
            }

        });
    }

    public static Country getCountry(String code, List<Country> countries) {
        for (Country country : countries) {
            if(country.getCountryCode().equals(code))
                return country;
        }

        return null;
    }

    public static Country getCountry2(String code, List<Country> countries) {
        for (Country country : countries) {
            if(country.getCountryCode2() != null && country.getCountryCode2().equals(code))
                return country;
        }

        return null;
    }

    public static Map<String, City> highestPopulationCityByCountry(Map<String, List<City>> dict) {
        Map<String, City> new_dict = new HashMap<>();
        dict.forEach((code, dict_cities) -> {
            if(dict_cities.isEmpty())
                return;
            new_dict.put(code, dict_cities.get(0));
        });
        return new_dict;
    }

    public static Map<String, City> highestPopulationCityByContinent(Map<String, List<City>> dict) {
        Map<String, City> new_dict = new HashMap<>();
        dict.forEach((code, dict_cities) -> {
            if(dict_cities.isEmpty())
                return;
            new_dict.put(code, dict_cities.get(0));
        });
        return new_dict;
    }

    public static City highestPopulationCapital(List<Country> countries) {
        int max = 0;
        City max_capacity = null;
        for (Country country : countries) {
            if(country.getCapital() != null && country.getCapital().getCityPopulation() > max) {
                max = country.getCapital().getCityPopulation();
                max_capacity = country.getCapital();
            }
        }

        return max_capacity;
    }

}