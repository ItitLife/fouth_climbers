package fourthClimbers.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PrimaryKey {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
