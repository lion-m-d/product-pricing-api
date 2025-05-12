package com.ws.infrastructure.price.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for registering interceptors in the Spring MVC context.
 * <p>Configures {@link RequestResponseLoggingInterceptor} to log request parameters and bodies
 * for all HTTP requests using {@link WebMvcConfigurer}.</p>
 * @see WebMvcConfigurer
 * @see RequestResponseLoggingInterceptor
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestResponseLoggingInterceptor requestResponseLoggingInterceptor;

    /**
     * Constructor to initialize the {@link RequestResponseLoggingInterceptor}.
     *
     * @param requestResponseLoggingInterceptor the interceptor to register
     */
    public WebConfig(RequestResponseLoggingInterceptor requestResponseLoggingInterceptor) {
        this.requestResponseLoggingInterceptor = requestResponseLoggingInterceptor;
    }

    /**
     * Adds the {@link RequestResponseLoggingInterceptor} to the interceptor registry for all HTTP requests.
     *
     * @param registry the interceptor registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestResponseLoggingInterceptor)
                .addPathPatterns("/**");
    }
}