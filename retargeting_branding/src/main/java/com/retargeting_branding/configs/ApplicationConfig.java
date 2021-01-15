package com.retargeting_branding.configs;

import java.util.concurrent.Executors;

import com.retargeting_branding.requests.adevents.CreateAdEventsRequest;
import com.retargeting_branding.requests.impression.CreateImpressionRequest;
import com.retargeting_branding.responses.adevents.GeneralAdEventsResponse;
import com.retargeting_branding.responses.impression.GeneralImpressionResponse;
import com.retargeting_branding.utils.RedisService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@ConfigurationProperties(prefix = "system-config")
public class ApplicationConfig {
    
    private TokenGeneratorConfig generator;

    private PromoCodeConfig promoCodeGenerator;

    private ServerApi serverApi;

    public PromoCodeConfig getPromoCodeGenerator(){
        return this.promoCodeGenerator;
    }

    public void setPromoCodeGenerator (PromoCodeConfig promoCodeGenerator){
        this.promoCodeGenerator = promoCodeGenerator;
    }

    public ServerApi getServerApi(){
        return this.serverApi;
    }

    public void setServerApi (ServerApi serverApi){
        this.serverApi = serverApi;
    }

    public TokenGeneratorConfig getGenerator() {
        return this.generator;
    }

    public static class PromoCodeConfig{

        private String generator;

        public String getGenerator() {
            return this.generator;
        }
    
        public void setGenerator(String generator) {
            this.generator = generator;
        }

        @Override
        public String toString() {
            return "{" +
                " generator='" + getGenerator() + "'" +
                "}";
        }
    }

    public static class ServerApi{

        private String serverAddress;

        public String getServerAddress() {
            return this.serverAddress;
        }
    
        public void setServerAddress(String serverAddress) {
            this.serverAddress = serverAddress;
        }

        @Override
        public String toString() {
            return "{" +
                " serverAddress='" + getServerAddress() + "'" +
                "}";
        }
    }

    public void setGenerator(TokenGeneratorConfig generator) {
        this.generator = generator;
    }

    public static class TokenGeneratorConfig  {

        private String key;

        public String getKey() {
            return this.key;
        }
    
        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "{" +
                " key='" + getKey() + "'" +
                "}";
        }
    } 

    @Override
    public String toString() {
        return "{" +
            " generator='" + getGenerator() + "'" +
            " serverApi='" + getServerApi() + "'" +
            " promoCodeGenerator='" + getPromoCodeGenerator() + "'" +
            "}";
    }

    @Bean
	JedisConnectionFactory connectionFactory() {
		return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, GeneralImpressionResponse> redisCreateImpressionResponse(Jackson2JsonRedisSerializer<GeneralImpressionResponse> serializer) {
        RedisTemplate<String, GeneralImpressionResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<GeneralImpressionResponse> jackson2JsonRedisSerializerCreateImpressionResponse() {
        return new Jackson2JsonRedisSerializer<>(GeneralImpressionResponse.class);
    }

    @Bean
    public Jackson2JsonRedisSerializer<GeneralAdEventsResponse> jackson2JsonRedisSerializerCreateAdEventsResponse() {
        return new Jackson2JsonRedisSerializer<>(GeneralAdEventsResponse.class);
    }

    @Bean
    RedisTemplate<String, GeneralAdEventsResponse> redisCreateAdEventsResponse(Jackson2JsonRedisSerializer<GeneralAdEventsResponse> serializer) {
        RedisTemplate<String, GeneralAdEventsResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    RedisService service() {
        return new RedisService();
    }

	@Bean
	RedisMessageListenerContainer redisContainer(
    @Qualifier("impressionListenerAdapter") MessageListenerAdapter impressionListenerAdapter,
    @Qualifier("adEventsListenerAdapter") MessageListenerAdapter adEventsListenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
        container.addMessageListener(impressionListenerAdapter,  new PatternTopic("createImpressionRequest"));
        container.addMessageListener(adEventsListenerAdapter,  new PatternTopic("createAdEventsRequest"));
		container.setTaskExecutor(Executors.newFixedThreadPool(4));
		return container;
    }
    
    @Bean("impressionListenerAdapter")
    MessageListenerAdapter impressionListenerAdapter(RedisService redisService) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisService, "createImpression");
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(CreateImpressionRequest.class));
        return messageListenerAdapter;
    }
    
    @Bean("adEventsListenerAdapter")
    MessageListenerAdapter adEventsListenerAdapter(RedisService redisService) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisService, "createAdEvents");
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(CreateAdEventsRequest.class));
        return messageListenerAdapter;
    }

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("API KEY / API SECRET",
						new SecurityScheme().type(SecurityScheme.Type.APIKEY).scheme("apiKey")))
				.info(new Info().title("Retargetting and Branding API").version("1.0.0").description(
						"This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).For this sample, you can use the api key `special-key` to test the authorization     filters.")
						.termsOfService("http://Lanthanion.com")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}