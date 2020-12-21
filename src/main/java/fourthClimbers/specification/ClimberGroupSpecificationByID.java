package fourthClimbers.specification;

import fourthClimbers.entity.ClimberGroup;
import fourthClimbers.entity.ClimberGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberGroupSpecificationByID implements Specification<ClimberGroup> {
    private int id;

    public ClimberGroupSpecificationByID(int id) {
        this.id = id;
    }

    @Override
    public Predicate getPredicate(Root<ClimberGroup> groupRoot, CriteriaBuilder builder) {
        return builder.gt(groupRoot.get(ClimberGroup_.id), id);
    }
}

/*

*/

