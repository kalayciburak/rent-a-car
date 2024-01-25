package com.kalayciburak.commonpackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * <b>JPA repository'i kendi ihtiyaçlarımıza göre genişlettiğimiz interface</b>
 * <p>
 * Bu interface'i extend eden repository'lerde JPA repository'nin tüm methodları kullanılabilir.
 *
 * @param <T>  Entity sınıfı
 * @param <ID> Entity id tipi
 * @see JpaRepository
 */
@NoRepositoryBean
public interface SimpleRepository<T, ID> extends JpaRepository<T, ID> {
}
