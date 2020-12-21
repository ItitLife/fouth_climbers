package fourthClimbers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
//@Table
public class Climber extends PrimaryKey {
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false, length = 30)
    private String address;
    @Column(nullable = false)
    private int age;
    @ManyToMany(mappedBy = "climbers")
    private Set<ClimberGroup> groups = new HashSet<>();

    public Climber(String name, String address, int age) {
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.length() < 3)
            throw new IllegalArgumentException("Name must be longer than 3 characters");
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        if (address == null || address.length() < 5)
            throw new IllegalArgumentException("Address must be longer than 5 characters");
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age < 18)
            throw new IllegalArgumentException("Climber must be alder then 18");
        this.age = age;
    }

    public Set<ClimberGroup> getGroups() {
        return groups;
    }

    public void addGroup(ClimberGroup group) {
        if (group != null) {
            if (!groups.contains(group)) {
                this.groups.add(group);
                System.out.println(name + " was added in group" + group.getMountain().getName() + ": " + group.getStartDate());
            } else {
                System.out.println("This group is already in Set");
            }
        }
    }


    public boolean isAvailable(LocalDate date) {
        boolean isAvailable = true;
        for (ClimberGroup climberGroup : groups) {
            if (date.isAfter(climberGroup.getStartDate()) &&
                    date.isBefore(climberGroup.getStartDate().plusDays(climberGroup.getDuration()))) {
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }


    @Override
    public String toString() {
        return "Climber{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
