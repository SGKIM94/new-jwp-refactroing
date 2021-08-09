package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.dto.ProductRequest;
import kitchenpos.dto.ProductResponse;
import kitchenpos.dto.ProductsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("품목 인수테스트")
public class ProductRestAcceptanceTest extends AcceptanceTest {

	public static final String PRODUCT_API_BASE_URL = "/api/products";

	@DisplayName("품목을 생성한다.")
	@Test
	void createProduct() {
		//when
		ExtractableResponse<Response> response = 품목_생성을_요청한다("테스트 상품", 20000);

		// then
		품목이_생성됨(response);
	}

	@DisplayName("품목을 조회한다.")
	@Test
	void listProduct() {
		//given
		품목_생성됨();

		// when
		ExtractableResponse<Response> response = 품목_조회를_요청한다();

		// then
		품목이_조회됨(response);
		품목에_값들이_쫀재함(response);
	}

	static ExtractableResponse<Response> 품목_조회를_요청한다() {
		return RestAssured
					.given().log().all()
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when().get(PRODUCT_API_BASE_URL)
					.then().log().all().extract();
	}

	public static Long 품목_생성됨(String name, int price) {
		ExtractableResponse<Response> response = RestAssured
				.given().log().all()
				.body(new ProductRequest(name, BigDecimal.valueOf(price)))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post(PRODUCT_API_BASE_URL)
				.then().log().all().extract();

		return response.body().as(ProductResponse.class).getId();
	}

	private static ExtractableResponse<Response> 품목_생성을_요청한다(String name, int price) {
		return RestAssured
					.given().log().all()
					.body(new ProductRequest(name, BigDecimal.valueOf(price)))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when().post(PRODUCT_API_BASE_URL)
					.then().log().all().extract();
	}

	private void 품목이_생성됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private void 품목이_조회됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private Long 품목_생성됨() {
		ExtractableResponse<Response> createdProduct = 품목_생성을_요청한다("테스트 상품", 20000);
		return createdProduct.body().as(ProductResponse.class).getId();
	}

	private void 품목에_값들이_쫀재함(ExtractableResponse<Response> response) {
		List<ProductResponse> products = response.body().as(ProductsResponse.class).getProducts();
		products.forEach(product -> assertThat(product.getId()).isNotNull());
	}

}
