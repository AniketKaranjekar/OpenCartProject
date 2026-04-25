package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public Object[][] getData() throws Exception {
		// Simple, interview-friendly data provider (no Excel dependency).
		// Format: email, password, expectedResult(Valid/Invalid)
		ConfigReader.loadConfig();

		String validEmail = ConfigReader.get("email");
		String validPassword = ConfigReader.get("password");

		return new Object[][] {
				{ validEmail, validPassword, "Valid" },
				{ validEmail, "wrongPassword123", "Invalid" },
				{ "invalid_email@test.com", validPassword, "Invalid" }
		};
	}
}