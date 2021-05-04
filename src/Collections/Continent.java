package Collections;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    private String continentCode;
    private List<Country> countries;

    public Continent(String continentCode) {
        this.continentCode = continentCode;
        countries = new ArrayList<>();
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public void addCountry(Country c) {
        countries.add(c);
    }

    public void setCountries(List<Country> countries) {
        countries.forEach(country -> {
            if(country.getContinentCode() != null && country.getContinentCode().equals(this.continentCode))
                this.addCountry(country);
        });
    }

    public List<Country> getCountries() {
        return countries;
    }

    public static List<Continent> convertStringToContinent(List<String> continentsString, List<Country> countries) {
        List<Continent> continents = new ArrayList<>();
        continentsString.forEach(continentString -> {
            String[] s = continentString.split(",");
            try {
                if(!s[5].equals("")) {
                    String continentCode = s[5].replaceAll(" ", "");
                    Continent c = new Continent(continentCode);
                    c.setCountries(countries);
                    if(!c.getCountries().isEmpty())
                        continents.add(c);
                }
            }catch(ArrayIndexOutOfBoundsException e) {

            }
        });

        return continents;

    }
}
