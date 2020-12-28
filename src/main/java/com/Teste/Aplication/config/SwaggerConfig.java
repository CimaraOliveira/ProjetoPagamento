package com.Teste.Aplication.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Teste.Aplication"))
                .paths(regex("/api/compras.*"))
                //.paths(regex("/api/usuarios.*"))
                .build()
                .apiInfo(metaInfo());
    }
	
	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Pagamento API REST",
                "Formas de Pagamento cartao ou boleto.",
                "1.0",
                "Terms of Service",
                new Contact("ADS", "suporte.projeto.siap@gmail.com",
                       "suporte.projeto.siap@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
