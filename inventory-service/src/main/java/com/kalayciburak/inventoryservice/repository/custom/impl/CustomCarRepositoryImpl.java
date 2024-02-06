package com.kalayciburak.inventoryservice.repository.custom.impl;

import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.model.entity.QCar;
import com.kalayciburak.inventoryservice.model.entity.QLookup;
import com.kalayciburak.inventoryservice.repository.custom.CustomCarRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomCarRepositoryImpl implements CustomCarRepository {
    private final QCar qCar = QCar.car;
    private final JPAQueryFactory query;
    private final QLookup qLookup = QLookup.lookup;

    @Override
    public List<Car> findByStatus(String status) {
        return query.selectFrom(qCar)
                .innerJoin(qCar.carStatus, qLookup)
                .where(qLookup.key.eq(status))
                .fetch();
    }
}
