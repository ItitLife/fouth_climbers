package fourthClimbers.specification;

import fourthClimbers.entity.Mountain;
import fourthClimbers.entity.Mountain_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MountainSpecificationByCountry implements Specification<Mountain> {
    private String country;

    public MountainSpecificationByCountry(String country) {
        this.country = country;
    }

    @Override
    public Predicate getPredicate(Root<Mountain> root, CriteriaBuilder builder) {
        return builder.equal(root.get(Mountain_.country), country);
    }
}
