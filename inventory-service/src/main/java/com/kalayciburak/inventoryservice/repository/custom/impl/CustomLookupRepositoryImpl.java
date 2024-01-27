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
        return queryFactory
                .selectFrom(qLookup)
                .where(qLookup.parent.id.eq(parentId))
                .fetch();
    }

    @Override
    public List<Lookup> findChildrenByParentKey(String key) {
        return queryFactory
                .selectFrom(qLookup)
                .where(qLookup.parent.key.eq(key))
                .fetch();
    }

    @Override
    public List<Lookup> findChildrenByParentLabel(String label) {
        return queryFactory
                .selectFrom(qLookup)
                .where(qLookup.parent.label.eq(label))
                .fetch();
    }
}