package it.montblanc0;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		for (HttpMessageConverter converter : converters) {
			if (converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) {
				ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
				Hibernate6Module h6m = new Hibernate6Module();
//                h6m.configure(Hibernate6Module.Feature.FORCE_LAZY_LOADING, true);
				h6m.configure(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
				mapper.registerModule(h6m);
			}
		}
	}
}
