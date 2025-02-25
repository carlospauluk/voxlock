package br.com.voxlock.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Value("${Host:}")
	private String configUrl;

	@Bean
	public OpenAPI OpenApi() {
		OpenAPI openAPI = new OpenAPI();
		if (StringUtils.isNotEmpty(configUrl)) {
			openAPI.addServersItem(
				new Server().url(configUrl));
		}


		return openAPI;
	}
}
