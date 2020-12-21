package fourthClimbers;

import fourthClimbers.dao.ClimberDao;
import fourthClimbers.dao.ClimberGroupDao;
import fourthClimbers.dao.MountainDao;
import fourthClimbers.entity.Climber;
import fourthClimbers.entity.ClimberGroup;
import fourthClimbers.entity.Mountain;
import fourthClimbers.specification.ClimberGroupSpecificationIsOpen;
import fourthClimbers.specification.ClimberSpecificationOlderThen;
import fourthClimbers.specification.ClimberSpecificationYoungerThen;
import fourthClimbers.specification.MountainSpecificationByCountry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;


public class GroupBuilder {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("manager");
        EntityManager manager = factory.createEntityManager();
        ClimberDao climberDao = new ClimberDao(manager);
        MountainDao mountainDao = new MountainDao(manager);
        ClimberGroupDao climberGroupDao = new ClimberGroupDao(manager);
        Climber john = new Climber("John", "Johns av.", 19);
        Climber iren = new Climber("Iren", "Irens av.", 23);
        Climber alex = new Climber("Alex", "Alex av.", 44);
        Climber ivan = new Climber("Ivan", "Ivans av.", 50);
        Climber oleg = new Climber("Oleg", "Olegs av.", 36);
        Mountain mount1 = new Mountain("Mount1", "Russia", 150);
        Mountain mount2 = new Mountain("Mount2", "England", 250);
        Mountain mount3 = new Mountain("Mount3", "Germany", 350);
        Mountain mount4 = new Mountain("Mount1", "Russia", 650);
        Mountain mount5 = new Mountain("Mount1", "Russia", 450);
        Mountain mount6 = new Mountain("Mount1", "Russia", 550);
        ClimberGroup group1 = new ClimberGroup(mount1, 5, LocalDate.of(2020,10,10),
                2 + (int) Math.random() * 10);
        ClimberGroup group2 = new ClimberGroup(mount2, 5, LocalDate.of(2020,10,11),
                2 + (int) Math.random() * 10);
        ClimberGroup group3 = new ClimberGroup(mount3, 5, LocalDate.now().plusDays((int) (Math.random() * 10)),
                1 + (int) Math.random() * 10);
        ClimberGroup group4 = new ClimberGroup(mount3, 5, LocalDate.now().plusDays((int) (Math.random() * 10)),
                1 + (int) Math.random() * 10);
        group1.addClimbers(john, alex, iren);
        group2.addClimbers(alex, iren, john);
        group2.close();
        group3.addClimbers(john, alex);
        group3.close();
        group1.addClimbers(ivan, oleg);
        System.out.println(iren.getGroups());
        manager.getTransaction().begin();
        climberDao.addAll(john,iren,alex,ivan,oleg,iren);
        climberGroupDao.addAll(group1,group2,group3,group4);
        mountainDao.addAll(mount1,mount2,mount3,mount4,mount5,mount6);
        manager.getTransaction().commit();

        List<Climber> climbers = climberDao.queryBySpecsAnd(new ClimberSpecificationOlderThen(23), new ClimberSpecificationYoungerThen(49));
        System.out.println(climbers);
        List<Mountain> mountains = mountainDao.queryBySpec(new MountainSpecificationByCountry("Russia"));
        System.out.println(mountains);
        List<ClimberGroup> openGroups = climberGroupDao.queryBySpec(new ClimberGroupSpecificationIsOpen());
        System.out.println(openGroups);
    }
}
