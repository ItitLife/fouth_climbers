package fourthClimbers.specification;

import fourthClimbers.entity.Climber;
import fourthClimbers.entity.Climber_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ClimberSpecificationOlderThen implements Specification<Climber> {
    private int from;


    public ClimberSpecificationOlderThen(int from) {
        this.from = from;
    }

    @Override
    public Predicate getPredicate(Root<Climber> root, CriteriaBuilder builder) {
        return builder.greaterThanOrEqualTo(root.get(Climber_.age), this.from);
    }
}

