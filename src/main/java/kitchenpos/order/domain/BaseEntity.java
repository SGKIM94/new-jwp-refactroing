package kitchenpos.order.domain;

import java.time.LocalDateTime;

public class BaseEntity {
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	public BaseEntity() {
	}

	public BaseEntity(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public BaseEntity(LocalDateTime createdDate, LocalDateTime updatedDate) {
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
}
