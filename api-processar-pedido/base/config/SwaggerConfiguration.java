package br.com.vita.projeto.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	private static final String NOME_SERVICO = "PROJETO-BASE";
	private static final String DESCRICAO_SERVICO = "Api de modelo Vita.";
	private static final String VERSAO_SERVICO = "1.0.0";
	private static final String BASE_PACKAGE = "br.com.vita.projeto.base.controller";
	
	@Bean
	public Docket apiConfigDocs() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("v1").select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any()).build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(NOME_SERVICO)
				.description(DESCRICAO_SERVICO).version(VERSAO_SERVICO).build();
	}

}
