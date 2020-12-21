package fourthClimbers.dao;

import fourthClimbers.specification.Specification;

import java.util.List;

public interface Dao<T, PK> { // aka Repository
    void add(T t);
    void update(T t);
    T getByPK(PK pk);
    void delete(T t);
    void deleteByPK(PK pk);
    List<T> queryBySpec(Specification<T> specification);
}
