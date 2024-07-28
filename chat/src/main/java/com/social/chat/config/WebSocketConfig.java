package com.social.chat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker that will carry messages to clients on destinations prefixed with "/user".
        registry.enableSimpleBroker("/user");

        // Set the prefix for messages that are bound for methods annotated with @MessageMapping.
        // Any message sent to a destination starting with "/app" will be routed to message-handling methods in the application.
        registry.setApplicationDestinationPrefixes("/app");

        // Set the prefix used to identify user destinations.
        // This is used for sending messages to specific users, typically in a one-on-one chat scenario.
        registry.setUserDestinationPrefix("/user");
    }

    /**
     * Registers STOMP endpoints that clients will use to connect to the WebSocket server.
     *
     * @param registry the registry to register the STOMP endpoints
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register a new STOMP endpoint at "/ws" and enable SockJS fallback options.
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * Configures the message converters to use JSON format for WebSocket messages.
     *
     * @param messageConverters the list of message converters to configure
     * @return false to indicate that the default message converters should not be added
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Create a DefaultContentTypeResolver and set the default MIME type to application/json.
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);

        // Create a MappingJackson2MessageConverter and configure it with an ObjectMapper and the content type resolver.
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);

        // Add the configured converter to the list of message converters.
        messageConverters.add(converter);

        // Return false to indicate that the default message converters should not be added.
        return false;
    }

}
