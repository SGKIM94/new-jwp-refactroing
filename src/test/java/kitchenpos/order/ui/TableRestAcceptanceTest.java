package kitchenpos.order.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.dto.OrderTableRequest;
import kitchenpos.order.dto.OrderTableResponse;
import kitchenpos.order.dto.OrderTablesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@DisplayName("테이블 인수 테스트")
public class TableRestAcceptanceTest extends AcceptanceTest {

	public static final String TABLE_BASE_URL = "/api/tables";

	@DisplayName("테이블을 생성한다.")
	@Test
	void createMenuGroup() {
		// when
		ExtractableResponse<Response> response = 빈_테이블_생성_요청();

		// then
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
		Long orderTableId = response.body().as(OrderTable.class).getId();
		assertThat(orderTableId).isNotNull();
	}

	@DisplayName("테이블들을 조회한다.")
	@Test
	void listTable() {
		Long tableId = 빈_테이블_생생됨();

		// when
		ExtractableResponse<Response> response = 테이블들을_조회_요청();

		// then
		테이블이_조회됨(response);
		생성된_테이블이_포함됨(tableId, response);
	}

	private ExtractableResponse<Response> 테이블들을_조회_요청() {
		return RestAssured
					.given().log().all()
					.when().get(TABLE_BASE_URL)
					.then().log().all().extract();
	}

	private void 생성된_테이블이_포함됨(Long tableId, ExtractableResponse<Response> response) {
		List<Long> ids = extractListIds(response);
		assertThat(ids).contains(
				tableId
		);
	}

	private List<Long> extractListIds(ExtractableResponse<Response> response) {
		OrderTablesResponse orderTables = response.body().as(OrderTablesResponse.class);
		return orderTables.getOrderTables().stream()
				.map(OrderTableResponse::getId)
				.collect(Collectors.toList());
	}

	private void 테이블이_조회됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private Long 빈_테이블_생생됨() {
		Map<String, Object> params = new HashMap<>();
		params.put("numberOfGuests", 0);
		params.put("empty", true);

		return RestAssured
				.given().log().all()
				.body(params)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post(TABLE_BASE_URL)
				.then().log().all().extract().body().as(OrderTable.class).getId();
	}

	private ExtractableResponse<Response> 빈_테이블_생성_요청() {
		OrderTableRequest request = new OrderTableRequest(0, true);

		return RestAssured
				.given().log().all()
				.body(request)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post(TABLE_BASE_URL)
				.then().log().all().extract();
	}
}
