package kitchenpos.dto;

import kitchenpos.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResponse {
	private List<ProductResponse> products;

	public ProductsResponse() {
	}

	public ProductsResponse(List<ProductResponse> products) {
		this.products = products;
	}

	public static ProductsResponse of(List<Product> products) {
		List<ProductResponse> productResponses = products.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());

		return new ProductsResponse(productResponses);
	}

	public List<ProductResponse> getProducts() {
		return products;
	}
}
