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
		//given
		ProductRequest params = 품목을_생성한다();

		// when
		ExtractableResponse<Response> response = 품목_생성을_요청한다(params);

		// then
		품목이_생성됨(response);
	}

	private ProductRequest 품목을_생성한다() {
		return new ProductRequest("테스트 상품", BigDecimal.valueOf(20000));
	}

	private ExtractableResponse<Response> 품목_생성을_요청한다(ProductRequest params) {
		return RestAssured
					.given().log().all()
					.body(params)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.when().post("/api/products")
					.then().log().all().extract();
	}

	private void 품목이_생성됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
