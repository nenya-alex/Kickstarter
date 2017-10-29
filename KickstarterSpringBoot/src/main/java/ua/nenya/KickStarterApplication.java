package ua.nenya;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import ua.nenya.config.DefaultProfileUtil;

import java.net.UnknownHostException;

@SpringBootApplication
public class KickStarterApplication {

	private static final Logger log = LoggerFactory.getLogger(KickStarterApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(KickStarterApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		log.info("\n----------------------------------------------------------\n\t" +
						"Access URL: \t\t{}://localhost:{}\n\t",
				protocol,
				env.getProperty("server.port"));
	}
}
