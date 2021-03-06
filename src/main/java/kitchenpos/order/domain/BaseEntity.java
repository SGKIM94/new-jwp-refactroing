package kitchenpos.order.domain;

import java.time.LocalDateTime;

public class BaseEntity {
	private Long id;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	public BaseEntity() {
	}

	public BaseEntity(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
}
