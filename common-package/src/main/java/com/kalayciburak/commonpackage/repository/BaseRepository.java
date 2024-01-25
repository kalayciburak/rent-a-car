package com.kalayciburak.commonpackage.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>Soft delete işlemleri için kullanılacak olan repository interface'i</b>
 * <p>
 * Bu interface'i extend eden repository'lerde soft delete işlemleri için kullanılacak olan methodlar tanımlanır.
 * Ayrıca SimpleRepository interface'ini extend eder. Bu sayede JpaRepository'nin tüm methodları kullanılabilir.
 *
 * @param <T>  Entity sınıfı
 * @param <ID> Entity id tipi
 * @see SimpleRepository
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends SimpleRepository<T, ID> {
    String deleteQuery = "update #{#entityName} entity set entity.isActive = false, entity.deletedAt = current_timestamp where entity.id = ?1";

    @Modifying
    @Transactional
    @Query(deleteQuery)
    void softDeleteById(ID id);

    @Modifying
    @Transactional
    @Query(deleteQuery)
    void softDeleteByIds(Iterable<ID> idList);
}
