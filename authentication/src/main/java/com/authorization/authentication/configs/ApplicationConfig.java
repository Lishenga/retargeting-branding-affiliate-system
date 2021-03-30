package com.authorization.authentication.configs;

import java.util.concurrent.Executors;

import com.authorization.authentication.helpers.RedisService;
import com.authorization.authentication.requests.chat.CreateChatRequest;
import com.authorization.authentication.requests.messages.CreateMessageRequest;
import com.authorization.authentication.responses.chat.GeneralChatResponse;
import com.authorization.authentication.responses.messages.CreateMessageResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
// import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@ConfigurationProperties(prefix = "system-config")
public class ApplicationConfig {
    
    private TokenGeneratorConfig generator;

    private Api api;

    private EmailSender emailSender;

    private MailJet mailJet;

    public MailJet getMailJet() {
        return this.mailJet;
    }

    public void setMailJet(MailJet mailJet) {
        this.mailJet = mailJet;
    }

    public static class MailJet {

        private String apiKey;

        private String secretKey;

        public String getApiKey() {
            return this.apiKey;
        }
    
        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getSecretKey() {
            return this.secretKey;
        }
    
        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        @Override
        public String toString() {
            return "{" +
                " apiKey='" + getApiKey() + "'" +
                " secretKey='" + getSecretKey() + "'" +
                "}";
        }
    }

    public TokenGeneratorConfig getGenerator() {
        return this.generator;
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

    public Api getApi() {
        return this.api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public static class Api {

        private String version;

        public String getVersion() {
            return this.version;
        }
    
        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "{" +
                " version='" + getVersion() + "'" +
                "}";
        }
    }

    public EmailSender getEmailSender() {
        return this.emailSender;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public static class EmailSender {

        private String sender;

        public String getSender() {
            return this.sender;
        }
    
        public void setSender(String sender) {
            this.sender = sender;
        }

        @Override
        public String toString() {
            return "{" +
                " sender='" + getSender() + "'" +
                "}";
        }
    }

    @Override
    public String toString() {
        return "{" +
            " generator='" + getGenerator() + "'" +
            ", api='" + getApi() + "'" +
            ", emailSender='" + getEmailSender()+ "'" +
            ", mailJet='" + getMailJet()+ "'" +
            "}";
    }

    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean factoryBean(){
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates");
        return bean;
    }

    @Bean
	JedisConnectionFactory connectionFactory() {
		return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, CreateMessageResponse> redisCreateMessagesResponse(Jackson2JsonRedisSerializer<CreateMessageResponse> serializer) {
        RedisTemplate<String, CreateMessageResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, GeneralChatResponse> redisCreateChatResponse(Jackson2JsonRedisSerializer<GeneralChatResponse> serializer) {
        RedisTemplate<String, GeneralChatResponse> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<CreateMessageResponse> jackson2JsonRedisSerializerCreateMessageResponse() {
        return new Jackson2JsonRedisSerializer<>(CreateMessageResponse.class);
    }

    @Bean
    public Jackson2JsonRedisSerializer<GeneralChatResponse> jackson2JsonRedisSerializerCreateChatResponse() {
        return new Jackson2JsonRedisSerializer<>(GeneralChatResponse.class);
    }

	@Bean
	RedisMessageListenerContainer redisContainer(
    @Qualifier("chatListenerAdapter") MessageListenerAdapter chatListenerAdapter,
    @Qualifier("messageListenerAdapter") MessageListenerAdapter messageListenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.addMessageListener(chatListenerAdapter,  new PatternTopic("createchat"));
        container.addMessageListener(messageListenerAdapter, new PatternTopic("createmessage"));
		container.setTaskExecutor(Executors.newFixedThreadPool(4));
		return container;
    }
    
    @Bean("chatListenerAdapter")
    MessageListenerAdapter chatListenerAdapter(RedisService redisService) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisService, "createChat");
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(CreateChatRequest.class));
        return messageListenerAdapter;
    }

    @Bean("messageListenerAdapter")
    MessageListenerAdapter messageListenerAdapter(RedisService redisService) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(redisService, "createMessage");
        messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(CreateMessageRequest.class));
        return messageListenerAdapter;
    }

    @Bean
    RedisService service() {
        return new RedisService();
    }

    // @LoadBalanced
	// @Bean
	// public WebClient.Builder getWebClient() {
	// 	return WebClient.builder();
    // }
    
    @Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("API KEY / API SECRET",
						new SecurityScheme().type(SecurityScheme.Type.APIKEY).scheme("apiKey")))
				.info(new Info().title("Authentication and Authorization API").version("1.0.0").description(
						"This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).For this sample, you can use the api key `special-key` to test the authorization     filters.")
						.termsOfService("http://Lanthanion.com")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}