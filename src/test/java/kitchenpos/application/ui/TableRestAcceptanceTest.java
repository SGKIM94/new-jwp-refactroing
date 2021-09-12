package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.order.domain.OrderTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@DisplayName("테이블 인수 테스트")
public class TableRestAcceptanceTest extends AcceptanceTest {

	public static final String TABLE_BASE_URL = "/api/tables";

	@DisplayName("테이블을 생성한다.")
	@Test
	void createMenuGroup() {
		// when
		Map<String, Object> params = new HashMap<>();
		params.put("numberOfGuests", 0);
		params.put("empty", true);

		ExtractableResponse<Response> response = RestAssured
		        .given().log().all()
		        .body(params)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post(TABLE_BASE_URL)
		        .then().log().all().extract();

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
		Long orderTableId = response.body().as(OrderTable.class).getId();
		assertThat(orderTableId).isNotNull();
	}
}
