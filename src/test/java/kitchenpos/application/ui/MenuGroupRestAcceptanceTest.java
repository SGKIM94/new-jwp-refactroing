package kitchenpos.application.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuGroupResponse;
import kitchenpos.menu.dto.MenuGroupsResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("메뉴 그룹 인수테스트")
public class MenuGroupRestAcceptanceTest extends AcceptanceTest {

	public static final String MENU_GROUPS_BASE_URL = "/api/menu-groups";

	@Test
	void createMenuGroup() {
		// when
		ExtractableResponse<Response> response = 메뉴그룹_생성_요청("한식");

		// then
		메뉴그룹_생성됨(response);
	}

	@Test
	void listMenuGroup() {
		// given
		메뉴그룹_생성_됨("한식");

		// when
		ExtractableResponse<Response> response = 메뉴그룹_조회_요청();

		// then
		메뉴그룹이_조회됨(response);
		메뉴그룹에_값들이_존재함(response);
	}

	private void 메뉴그룹이_조회됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static ExtractableResponse<Response> 메뉴그룹_조회_요청() {
		return RestAssured
					.given().log().all()
					.when().get(MENU_GROUPS_BASE_URL)
					.then().log().all().extract();
	}

	public static Long 메뉴그룹_생성_됨(String name) {
		ExtractableResponse<Response> response = 메뉴그룹_생성_요청(name);

		return response.body().as(MenuGroupResponse.class).getId();
	}

	public static ExtractableResponse<Response> 메뉴그룹_생성_요청(String name) {
		MenuGroupRequest request = new MenuGroupRequest(name);

		return RestAssured
				.given().log().all()
				.body(request)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post(MENU_GROUPS_BASE_URL)
				.then().log().all().extract();
	}

	private void 메뉴그룹_생성됨(ExtractableResponse<Response> response) {
		Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	private void 메뉴그룹에_값들이_존재함(ExtractableResponse<Response> response) {
		List<MenuGroupResponse> menuGroups = response.body().as(MenuGroupsResponse.class).getMenuGroups();
		menuGroups.forEach(product -> assertThat(product.getId()).isNotNull());
	}
}
