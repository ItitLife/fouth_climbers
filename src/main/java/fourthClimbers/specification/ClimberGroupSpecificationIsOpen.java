package fourthClimbers.specification;

import fourthClimbers.entity.ClimberGroup;
import fourthClimbers.entity.ClimberGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberGroupSpecificationIsOpen implements Specification<ClimberGroup> {

    public ClimberGroupSpecificationIsOpen() {
    }

    @Override
    public Predicate getPredicate(Root<ClimberGroup> root, CriteriaBuilder builder) {
        return builder.equal(root.get(ClimberGroup_.open),true);
    }
}
