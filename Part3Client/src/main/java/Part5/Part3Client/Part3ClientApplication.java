package Part5.Part3Client;

import Part5.Part3Client.service.JwtApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Part3ClientApplication implements CommandLineRunner {

	@Autowired
	private JwtApiClientService jwtService;
	public static void main(String[] args) {
		SpringApplication.run(Part3ClientApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String baseUrl = "http://localhost:8080";

		System.out.println("\n=== Login as normal user ===");
		jwtService.signIn(baseUrl, "user", "1234");
		jwtService.callProtectedEndpoint(baseUrl + "/api/all");
		jwtService.callProtectedEndpoint(baseUrl + "/api/users"); // should fail for normal user

		System.out.println("\n=== Login as admin ===");
		jwtService.signIn(baseUrl, "admin", "1234");
		jwtService.callProtectedEndpoint(baseUrl + "/api/users"); // should succeed for admin
	}
}

