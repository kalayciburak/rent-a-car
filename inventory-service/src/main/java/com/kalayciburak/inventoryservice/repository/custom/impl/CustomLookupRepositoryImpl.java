package com.kalayciburak.inventoryservice.repository.custom.impl;

import com.kalayciburak.inventoryservice.model.entity.Lookup;
import com.kalayciburak.inventoryservice.model.entity.QLookup;
import com.kalayciburak.inventoryservice.repository.custom.CustomLookupRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomLookupRepositoryImpl implements CustomLookupRepository {
    private final JPAQueryFactory queryFactory;
    private final QLookup qLookup = QLookup.lookup;

    @Override
    public List<Lookup> findChildrenByParentId(Long parentId) {
        var lookupCriteria = qLookup.parent.id.eq(parentId);
        return queryFactory
                .selectFrom(qLookup)
                .where(lookupCriteria)
                .fetch();
    }

    @Override
    public List<Lookup> findChildrenByParentKey(String key) {
        var lookupCriteria = qLookup.parent.key.eq(key);
        return queryFactory
                .selectFrom(qLookup)
                .where(lookupCriteria)
                .fetch();
    }

    @Override
    public List<Lookup> findChildrenByParentLabel(String label) {
        var lookupCriteria = qLookup.parent.label.eq(label);
        return queryFactory
                .selectFrom(qLookup)
                .where(lookupCriteria)
                .fetch();
    }
}