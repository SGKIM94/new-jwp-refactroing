package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.dto.ProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

@DisplayName("품목 인수테스트")
public class ProductRestAcceptanceTest extends AcceptanceTest {


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
		품목_생성을_요청한다("테스트 상품", 20000);

		// when
		ExtractableResponse<Response> response = RestAssured
				.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().get("/api/products")
				.then().log().all().extract();

		// then
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private ExtractableResponse<Response> 품목_생성을_요청한다(String name, int price) {
		return RestAssured
					.given().log().all()
					.body(new ProductRequest(name, BigDecimal.valueOf(price)))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when().post("/api/products")
					.then().log().all().extract();
	}

	private void 품목이_생성됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
