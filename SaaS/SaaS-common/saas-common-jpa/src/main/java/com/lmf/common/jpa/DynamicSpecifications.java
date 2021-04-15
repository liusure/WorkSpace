package com.saas.common.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DynamicSpecifications {

    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
        return (Specification<T>) (root, query, builder) -> {
            if (filters != null && !filters.isEmpty()) {

                List<Predicate> predicates = new ArrayList<>();
                for (SearchFilter filter : filters) {

                    // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
                    String[] names = StringUtils.split(filter.fieldName, ".");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }

                    // logic operator
                    switch (filter.operator) {
                        case EQ:
                            predicates.add(builder.equal(expression, filter.value));
                            break;
                        case LIKE:
                            predicates.add(builder.like(expression, "%" + filter.value + "%"));
                            break;
                        case LLIKE:
                            predicates.add(builder.like(expression, filter.value + "%"));
                            break;
                        case RLIKE:
                            predicates.add(builder.like(expression, "%" + filter.value));
                            break;
                        case CLIKE:
                            predicates.add(builder.like(expression, filter.value + ""));
                            break;
                        case GT:
                            predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                            break;
                        case LT:
                            predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                            break;
                        case GTE:
                            predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                            break;
                        case LTE:
                            predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                            break;
                        case NOTEQ:
                            predicates.add(builder.notEqual(expression, filter.value));
                            break;
                        case ISNULL:
                            predicates.add(builder.isNull(expression));
                            break;
                        case NOTNULL:
                            predicates.add(builder.isNotNull(expression));
                            break;
                        case IN:
                            predicates.add(builder.in(expression).value(filter.value));
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + filter.operator);
                    }
                }

                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            }

            return builder.conjunction();
        };
    }
}
