package org.arielzarate.infrastructure.rest.provider;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.arielzarate.interfaces.error.exception.WebClientException;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class WebClientProvider {

    private static final Logger LOG = Logger.getLogger(WebClientProvider.class);

    public <R> R applyWithoutBody(
            String clientName,
            WebClientMethod method,
            URI uri,
            long timeout,
            Map<String, String> headers,
            Class<R> responseType
    ) {
        String path = uri.getPath();
        LOG.infof("Calling %s %s %s", clientName, method.name(), path);

        Client client = ClientBuilder.newBuilder()
                .connectTimeout(timeout, TimeUnit.MILLISECONDS)
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .build();

        Invocation.Builder requestBuilder = client.target(uri)
                .request(MediaType.APPLICATION_JSON);

        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }

        try {
            Response response;
            switch (method) {
                case GET -> {
                    response = requestBuilder.get();
                    break;
                }
                case POST -> {
                    response = requestBuilder.post(null);
                    break;
                }
                case PUT -> {
                    response = requestBuilder.put(null);
                    break;
                }
                case DELETE -> {
                    response = requestBuilder.delete();
                    break;
                }
                case PATCH -> {
                    response = requestBuilder.method("PATCH");
                    break;
                }
                default -> throw new IllegalArgumentException("Unknown method: " + method);
            }

            if (response.getStatus() >= 400) {
                LOG.errorf("Call to %s response ERROR %d %s", clientName, response.getStatus(), path);
                throw new WebClientException("Error calling " + clientName + ": " + response.getStatus());
            }

            return response.readEntity(responseType);

        } catch (ProcessingException e) {
            LOG.errorf("Call TIMEOUT %s %s - %s", method.name(), path, e.getMessage());
            throw new WebClientException("Timeout calling " + clientName, e);
        } finally {
            client.close();
        }
    }
}