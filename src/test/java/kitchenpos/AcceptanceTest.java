package kitchenpos;

import io.restassured.RestAssured;
import kitchenpos.utils.DatabaseCleanup;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanup databaseClean;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
		databaseClean.execute();
	}
}
