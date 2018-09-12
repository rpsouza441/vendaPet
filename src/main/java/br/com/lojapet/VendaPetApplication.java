package br.com.lojapet;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class VendaPetApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaPetApplication.class, args);
	}

	//Não é mais necessário configurar o FormattingConversionService. 
	//Apenas colocar o  @Temporal E @DateTimeFormat nos campos Calendar
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
