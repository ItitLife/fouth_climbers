package fourthClimbers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
//@Table
public class Mountain extends PrimaryKey{
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 30)
    private String country;
    @Column(nullable = false)
    private int height;

    public Mountain(String name, String country, int height) {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.length() < 4)
            throw new IllegalArgumentException("The name of the mountain must be longer than 4 characters");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.length() < 4)
            throw new IllegalArgumentException("The name of the country must be more than 4 characters");
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 100)
            throw new IllegalArgumentException("The height of the mountain should be more than 100 meters");
        this.height = height;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                '}';
    }
}
