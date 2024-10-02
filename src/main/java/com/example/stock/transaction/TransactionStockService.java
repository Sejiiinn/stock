package com.example.stock.transaction;

import com.example.stock.service.StockService;

public class TransactionStockService {

	private StockService stockService;

	private TransactionStockService(StockService stockService) {
		this.stockService = stockService;
	}

	public void decrease(Long id, Long quantity) {
		startTransaction();

		stockService.decrease(id, quantity);

		// 트랜잭션을 완료하는 동작을 수행할 때에는 stockService.decrease 메서드를 수행하는 중이 아니므로, 다른 스레드가 접근 가능
		endTransaction();
	}

	private void startTransaction() {
		System.out.println("Trasaction Start");
	}

	private void endTransaction() {
		System.out.println("Commit");
	}
}
