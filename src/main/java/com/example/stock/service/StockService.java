package com.example.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;

@Service
public class StockService {

	private final StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	// 트랜잭션이 부모의 트랜잭션과 별개로 실행되어야 하기 때문에 propagation 추가
	// Propagation.REQUIRES_NEW는 새로운 트랜잭션을 항상 시작하도록 강제하는 역할을 함
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void decrease(Long id, Long quantity) {
		// Stock 조회
		// 재고를 감소
		// 갱신된 값을 저장
		Stock stock = stockRepository.findById(id).orElseThrow();
		stock.decrease(quantity);

		stockRepository.saveAndFlush(stock);
	}
}
