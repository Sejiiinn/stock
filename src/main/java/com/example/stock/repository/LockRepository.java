package com.example.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.stock.domain.Stock;

public interface LockRepository extends JpaRepository<Stock, Long> {

	// MySQL 에서는 get_lock, release_lock을 통해 namedLock을 획득하거나 해제할 수 있다.
	@Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
	void getLock(String key);

	@Query(value = "select release_lock(:key)", nativeQuery = true)
	void releaseLock(String key);
}
