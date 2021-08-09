package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.dto.MenuRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static kitchenpos.application.ui.MenuGroupRestAcceptanceTest.*;
import static kitchenpos.application.ui.ProductRestAcceptanceTest.*;

@DisplayName("메뉴 인수테스트")
public class MenuRestAcceptanceTest extends AcceptanceTest {

	@DisplayName("메뉴를 생성한다.")
	@Test
	void createMenu() {
		품목_생성됨("감자", 2000);
		Long menuGroupId = 메뉴그룹_생성_됨("한식");
		MenuRequest params = new MenuRequest("찜닭", BigDecimal.valueOf(2000), menuGroupId);

		// when
		ExtractableResponse<Response> response = 메뉴_생성_요청(params);

		// then
		메뉴_생성_됨(response);
	}

	private ExtractableResponse<Response> 메뉴_생성_요청(MenuRequest params) {
		ExtractableResponse<Response> response = RestAssured
		        .given().log().all()
		        .body(params)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post("/api/menus")
		        .then().log().all().extract();
		return response;
	}

	private void 메뉴_생성_됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}
}
