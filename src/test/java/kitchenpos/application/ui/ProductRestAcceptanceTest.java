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
		ProductRequest params = new ProductRequest("테스트 상품", BigDecimal.valueOf(20000));

		// when
		ExtractableResponse<Response> response = RestAssured
		        .given().log().all()
		        .body(params)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post("/api/products")
		        .then().log().all().extract();

		// then
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
