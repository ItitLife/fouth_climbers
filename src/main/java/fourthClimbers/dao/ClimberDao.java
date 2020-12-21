package fourthClimbers.dao;

import fourthClimbers.entity.Climber;
import fourthClimbers.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClimberDao implements Dao<Climber, Integer> {
    private EntityManager manager;

    public ClimberDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(Climber climber) {
        if (climber != null) manager.persist(climber);
    }

    public void addAll(Climber... climbers) {
        for (Climber climber : climbers) {
            if (climber != null) manager.persist(climber);
        }
    }

    @Override
    public void update(Climber climber) {
        if (climber != null) manager.merge(climber);
    }

    @Override
    public Climber getByPK(Integer integer) {
        return manager.find(Climber.class, integer);
    }

    @Override
    public void delete(Climber climber) {
        if (climber != null) manager.remove(climber);
    }

    @Override
    public void deleteByPK(Integer integer) {
        Climber climber = getByPK(integer);
        if (climber != null) manager.remove(climber);
    }

    @Override
    public List<Climber> queryBySpec(Specification<Climber> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }

    public List<Climber> queryBySpecsAnd(Specification<Climber>... specifications) {
        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Climber> criteriaQuery = builder.createQuery(Climber.class);
        Root<Climber> root = criteriaQuery.from(Climber.class);
        for (Specification<Climber> specification : specifications) {
            predicates.add(specification.getPredicate(root, builder));
        }
        criteriaQuery.where(predicates.toArray(Predicate[]::new));
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
