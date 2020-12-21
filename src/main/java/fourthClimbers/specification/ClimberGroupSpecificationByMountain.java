package fourthClimbers.specification;

import fourthClimbers.entity.ClimberGroup;
import fourthClimbers.entity.ClimberGroup_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClimberGroupSpecificationByMountain implements Specification<ClimberGroup> {
    private String mountain;

    public ClimberGroupSpecificationByMountain(String mountain) {
        this.mountain = mountain;
    }

    @Override
    public Predicate getPredicate(Root<ClimberGroup> root, CriteriaBuilder builder) {
        return builder.equal(root.get(ClimberGroup_.mountain.getName()), mountain);
    }
}
