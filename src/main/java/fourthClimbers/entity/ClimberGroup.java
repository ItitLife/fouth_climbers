package fourthClimbers.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table
public class ClimberGroup extends PrimaryKey {
    @Column(nullable = false)
    private boolean open = true;
    @ManyToMany
    private Set<Climber> climbers;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mountain mountain;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column
    private int duration; //days
    @Column
    int capacity;

    public ClimberGroup(Mountain mountain, int groupSize, LocalDate startDate, int duration) {
        climbers = new HashSet<>(groupSize);
        setMountain(mountain);
        open();
        setStartDate(startDate);
        setDuration(duration);
        capacity = groupSize;
    }

    public boolean isOpen() {
        return open;
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }

    public Set<Climber> getClimbers() {
        return climbers;
    }

    public Mountain getMountain() {
        return mountain;
    }

    public void setMountain(Mountain mountain) {
        if (mountain == null)
            throw new IllegalArgumentException("Mountain is not specified");
        this.mountain = mountain;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate != null) this.startDate = startDate;
        else throw new IllegalArgumentException("StartDate is not specified");
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0) this.duration = duration;
        else throw new IllegalArgumentException("Duration must be longer then 0");
    }

    public void addClimbers(Climber... newClimbers) {
        if (!open)
            throw new IllegalArgumentException("Group is closed");
        if (newClimbers == null || newClimbers.length > capacity)
            throw new IllegalArgumentException("Climbers are not specified or there are more of them than there are places in the group");
        for (int i = 0; i < newClimbers.length; i++) {
            if (newClimbers[i].isAvailable(this.startDate)) {
                climbers.add(newClimbers[i]);
                newClimbers[i].addGroup(this);
                capacity--;
            } else System.out.println(newClimbers[i].getName() + " is busy. This climber was not added to the group "
                    + mountain.getName() + ": " + startDate);
        }
        /*else if (j == climbers.length - 1)
            System.out.println("There was not enough space for a new climber in the group: " + newClimbers[i].getName());*/
    }


    @Override
    public String toString() {
        return "ClimberGroup{" +
                ", climbers=" + climbers.size() +
                ", mountain=" + mountain.getName() +
                ", start=" + startDate +
                '}';
    }
}
