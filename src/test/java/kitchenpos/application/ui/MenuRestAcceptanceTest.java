package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@DisplayName("메뉴 인수테스트")
public class MenuRestAcceptanceTest extends AcceptanceTest {

	@DisplayName("메뉴를 생성한다.")
	@Test
	void createMenu() {
		// when
		Map<String, Object> params = new HashMap<>();
		params.put("name", "pack");
		params.put("price", 12000);
		params.put("menuGroupId", 1);
		params.put("menuProducts", 1);

		ExtractableResponse<Response> response = RestAssured
		        .given().log().all()
		        .body(params)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post("/api/menus")
		        .then().log().all().extract();

		// then
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
