package fourthClimbers.dao;

import fourthClimbers.entity.Climber;
import fourthClimbers.entity.ClimberGroup;
import fourthClimbers.specification.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClimberGroupDao implements Dao<ClimberGroup, Integer> {
    private EntityManager manager;

    public ClimberGroupDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void add(ClimberGroup climberGroup) {
        if (climberGroup != null) manager.persist(climberGroup);
    }

    public void addAll(ClimberGroup... climberGroups) {
        for (ClimberGroup group : climberGroups) {
            if (group != null) manager.persist(group);
        }
    }

    @Override
    public void update(ClimberGroup climberGroup) {
        if (climberGroup != null) manager.merge(climberGroup);
    }

    @Override
    public ClimberGroup getByPK(Integer integer) {
        return manager.find(ClimberGroup.class, integer);
    }

    @Override
    public void delete(ClimberGroup climberGroup) {
        if (climberGroup != null) manager.remove(climberGroup);
    }

    @Override
    public void deleteByPK(Integer integer) {
        ClimberGroup climberGroup = getByPK(integer);
        if (climberGroup != null) manager.remove(climberGroup);
    }

    @Override
    public List<ClimberGroup> queryBySpec(Specification<ClimberGroup> specification) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ClimberGroup> criteriaQuery = builder.createQuery(ClimberGroup.class);
        Root<ClimberGroup> root = criteriaQuery.from(ClimberGroup.class);
        Predicate condition = specification.getPredicate(root, builder);
        criteriaQuery.where(condition);
        return manager.createQuery(criteriaQuery).getResultList();
    }
}
