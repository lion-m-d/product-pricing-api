package com.ws.infrastructure.price.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor for logging HTTP request parameters and bodies.
 * <p>Logs request details, including parameters and body (for POST/PUT requests), for debugging and monitoring.</p>
 * @see HandlerInterceptor
 * @see HttpServletRequest
 * @see HttpServletResponse
 */
@Slf4j
@Component
public class RequestResponseLoggingInterceptor implements HandlerInterceptor {

    /**
     * Intercepts incoming HTTP requests before they are handled by the controller.
     * Logs request parameters and the body for POST and PUT methods.
     *
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @param handler the handler to execute
     * @return {@code true} to allow the request to proceed, {@code false} to abort
     * @throws Exception if any error occurs during interception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logRequestParams(request);

        if (HttpMethod.POST.matches(request.getMethod()) || HttpMethod.PUT.matches(request.getMethod())) {
            logRequestBody(request);
        }

        return true;
    }

    /**
     * Logs the request parameters as a query string.
     * @param request the current HTTP request
     */

    private void logRequestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        params.append("?");
        request.getParameterMap().forEach((key, value) -> {
            for (String v : value) {
                params.append(key).append("=").append(v).append("&");
            }
        });

        if (params.length() > 0) {
            params.setLength(params.length() - 1);
        }

        log.info("Request : {}{}",request.getRequestURL(), params);
    }

    /**
     * Logs the request body for POST and PUT methods.
     * @param request the HTTP request
     * @throws IOException if an error occurs while reading the body
     */

    private void logRequestBody(HttpServletRequest request) throws IOException {
        String requestBody = Optional.of(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8)).orElse("{}");
        log.info("Request Body: {}", requestBody);
    }
}