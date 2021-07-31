package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.dto.MenuGroupRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("메뉴 그룹 인수테스트")
public class MenuGroupRestAcceptanceTest extends AcceptanceTest {

	@Test
	void createMenuGroup() {
		// when
		MenuGroupRequest request = new MenuGroupRequest("한식");

		ExtractableResponse<Response> response = RestAssured
		        .given().log().all()
		        .body(request)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post("/api/menu-groups")
		        .then().log().all().extract();

		// then
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
