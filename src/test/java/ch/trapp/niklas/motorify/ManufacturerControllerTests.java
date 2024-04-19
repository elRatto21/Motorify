package ch.trapp.niklas.motorify;

import ch.trapp.niklas.motorify.model.Manufacturer;
import ch.trapp.niklas.motorify.repository.ManufacturerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManufacturerControllerTests {

    @Autowired
    private MockMvc api;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @BeforeAll
    void setup() {
        this.manufacturerRepository.save(new Manufacturer("GetAllTestName", "Germany"));
        this.manufacturerRepository.save(new Manufacturer("GetTestName", "Switzerland"));
    }

    @Test
    void testGetManufacturers() throws Exception {
        String accessToken = getAccessToken();

        api.perform(get("/api/manufacturer").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("GetAllTestName")));
    }

    @Test
    void testGetManufacturersByName() throws Exception {
        String accessToken = getAccessToken();

        api.perform(get("/api/manufacturer?name=Get").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("GetAllTestName")))
                .andExpect(content().string(containsString("GetTestName")));
    }

    @Test
    void testCreateManufacturer() throws Exception {
        String accessToken = getAccessToken();

        Manufacturer manufacturer = new Manufacturer("Testname", "Testcountry");

        String body = new ObjectMapper().writeValueAsString(manufacturer);

        api.perform(post("/api/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("Testname")));
    }

    @Test
    void testDeleteManufacturer() throws Exception {
        String accessToken = getAccessToken();

        Manufacturer manufacturer = new Manufacturer("NotupdatedName", "Testcountry");
        Long id = this.manufacturerRepository.save(manufacturer).getId();

        api.perform(delete("/api/manufacturer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateManufacturer() throws Exception {
        String accessToken = getAccessToken();

        Manufacturer manufacturer = new Manufacturer("NotupdatedName", "Testcountry");
        Long id = this.manufacturerRepository.save(manufacturer).getId();

        Manufacturer manufacturerUpdated = new Manufacturer("Updatedname", "Testcountry");

        String body = new ObjectMapper().writeValueAsString(manufacturerUpdated);

        api.perform(put("/api/manufacturer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Updatedname")));
    }

    @Test
    void testCreateManufacturerNoPermissions() throws  Exception {
        String accessToken = getUserAccessToken();

        Manufacturer manufacturer = new Manufacturer("Testname", "Testcountry");

        String body = new ObjectMapper().writeValueAsString(manufacturer);

        api.perform(post("/api/manufacturer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isForbidden());
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

    private String getUserAccessToken() {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=motorify&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=user&" +
                "password=user";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/motorify/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }

}
