package ch.trapp.niklas.motorify;

import ch.trapp.niklas.motorify.bike.BikeRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class RestControllerTests {

	@Autowired
	private MockMvc api;

	@Autowired
	private BikeRepository bikeRepository;

}
