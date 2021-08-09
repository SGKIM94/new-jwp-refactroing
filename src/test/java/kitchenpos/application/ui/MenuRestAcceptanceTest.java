package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.dto.MenuRequest;
import kitchenpos.dto.MenuResponse;
import kitchenpos.dto.MenusResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static kitchenpos.application.ui.MenuGroupRestAcceptanceTest.*;
import static kitchenpos.application.ui.ProductRestAcceptanceTest.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("메뉴 인수테스트")
public class MenuRestAcceptanceTest extends AcceptanceTest {

	@DisplayName("메뉴를 생성한다.")
	@Test
	void createMenu() {
		//given
		품목_생성됨("감자", 2000);
		Long menuGroupId = 메뉴그룹_생성_됨("한식");
		MenuRequest params = new MenuRequest("찜닭", BigDecimal.valueOf(2000), menuGroupId);

		// when
		ExtractableResponse<Response> response = 메뉴_생성_요청(params);

		// then
		메뉴_생성_됨(response);
	}

	@Test
	void listMenu() {
		//given
		품목_생성됨("감자", 2000);
		Long menuGroupId = 메뉴그룹_생성_됨("한식");
		MenuRequest params = new MenuRequest("찜닭", BigDecimal.valueOf(2000), menuGroupId);
		메뉴_생성_됨(params);

		//when
		ExtractableResponse<Response> response = RestAssured
				.given().log().all()
				.body(params)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().get("/api/menus")
				.then().log().all().extract();

		//then
		메뉴_조회_됨(response);
		메뉴에_값들이_존재함(response);
	}

	private Long 메뉴_생성_됨(MenuRequest params) {
		ExtractableResponse<Response> response = 메뉴_생성_요청(params);
		return response.body().as(MenuResponse.class).getId();
	}

	private ExtractableResponse<Response> 메뉴_생성_요청(MenuRequest params) {
		return RestAssured
		        .given().log().all()
		        .body(params)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .when().post("/api/menus")
		        .then().log().all().extract();
	}

	private void 메뉴_생성_됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private void 메뉴_조회_됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	private void 메뉴에_값들이_존재함(ExtractableResponse<Response> response) {
		List<MenuResponse> menus = response.body().as(MenusResponse.class).getMenus();
		menus.forEach(product -> assertThat(product.getId()).isNotNull());
	}
}
