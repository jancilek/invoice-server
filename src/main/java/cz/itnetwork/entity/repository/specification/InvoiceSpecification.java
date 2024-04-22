package cz.itnetwork.entity.repository.specification;

import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.InvoiceEntity_;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.PersonEntity_;
import cz.itnetwork.entity.filter.InvoiceFilter;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class InvoiceSpecification implements Specification<InvoiceEntity> {

    private final InvoiceFilter invoiceFilter;

    @Override
    public Predicate toPredicate(Root<InvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (invoiceFilter.getSellerId() != null) {
            Join<PersonEntity, InvoiceEntity> sellerJoin = root.join(InvoiceEntity_.SELLER);
            predicates.add(criteriaBuilder.equal(sellerJoin.get(PersonEntity_.ID), invoiceFilter.getSellerId()));
        }

        if (invoiceFilter.getBuyerId() != null) {
            Join<PersonEntity, InvoiceEntity> buyerJoin = root.join(InvoiceEntity_.BUYER);
            predicates.add(criteriaBuilder.equal(buyerJoin.get(PersonEntity_.ID), invoiceFilter.getBuyerId()));
        }

        if (invoiceFilter.getMaxPrice() != null) {
            Expression<Long> price = root.get(InvoiceEntity_.PRICE);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(price, invoiceFilter.getMaxPrice()));
        }

        if (invoiceFilter.getMinPrice() != null) {
            Expression<Long> price = root.get(InvoiceEntity_.PRICE);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(price, invoiceFilter.getMinPrice()));
        }

        if (invoiceFilter.getProduct() != null && !invoiceFilter.getProduct().isEmpty()) {
            String productLowerCase = invoiceFilter.getProduct().toLowerCase();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(InvoiceEntity_.PRODUCT)), "%" + productLowerCase + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
