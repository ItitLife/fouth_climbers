package fourthClimbers.specification;

import fourthClimbers.entity.Climber;
import fourthClimbers.entity.Climber_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberSpecificationYoungerThen implements Specification<Climber> {
    private int to;

    public ClimberSpecificationYoungerThen(int to) {
        this.to = to;
    }

    @Override
    public Predicate getPredicate(Root<Climber> root, CriteriaBuilder builder) {
        return builder.lessThanOrEqualTo(root.get(Climber_.age), to);
    }
}
