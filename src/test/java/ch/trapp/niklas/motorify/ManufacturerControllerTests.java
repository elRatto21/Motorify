package ch.trapp.niklas.motorify;

import ch.trapp.niklas.motorify.repository.BikeRepository;
import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.ManufacturerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BikeControllerTests {

	@Autowired
	private MockMvc api;

	@Autowired
	private BikeRepository bikeRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@BeforeAll
	void setup() {

	}

	@Test
	void testGetBikes() throws Exception {
		String accessToken = getAccessToken();

		api.perform(get("/api/bike").header("Authorization", "Bearer " + accessToken).with(csrf()))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testCreateBike() throws Exception {
		String accessToken = getAccessToken();

		api.perform(post("/api/bike").header("Authorization", "Bearer " + accessToken).with(csrf()))
				.andDo(print()).andExpect(status().isCreated());
	}

	private String getAccessToken() {
		RestTemplate rest = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		String body = "client_id=motorify&" +
				"grant_type=password&" +
				"scope=openid profile roles offline_access&" +
				"username=admin&" +
				"password=admin";

		HttpEntity<String> entity = new HttpEntity<>(body, headers);

		ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/motorify/protocol/openid-connect/token", entity, String.class);

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
	}

}
