package Part1.Client;

import Part1.Client.service.ApiClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Part1ClientApplication implements CommandLineRunner {

	@Autowired
	private ApiClientService apiClientService;

	public static void main(String[] args) {
		SpringApplication.run(Part1ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String baseUrl = "http://localhost:8081";

		System.out.println("\n=== Testing with Bob (Sales Employee) ===");
		apiClientService.callEndpointWithAuth(baseUrl + "/shop", "bob", "1234");
		apiClientService.callEndpointWithAuth(baseUrl + "/orders", "bob", "1234");
		apiClientService.callEndpointWithAuth(baseUrl + "/payments", "bob", "1234");

		System.out.println("\n=== Testing with Mary (Finance Employee) ===");
		apiClientService.callEndpointWithAuth(baseUrl + "/shop", "mary", "1234");
		apiClientService.callEndpointWithAuth(baseUrl + "/orders", "mary", "1234");
		apiClientService.callEndpointWithAuth(baseUrl + "/payments", "mary", "1234");
	}
}

