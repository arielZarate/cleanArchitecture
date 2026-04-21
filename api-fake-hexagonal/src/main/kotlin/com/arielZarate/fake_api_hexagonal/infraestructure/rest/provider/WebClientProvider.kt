package com.arielZarate.fake_api_hexagonal.infraestructure.rest.provider

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URI
import java.time.Duration

@Component
class WebClientProvider(
    private val webClient: WebClient
) {
    private val log = LoggerFactory.getLogger(WebClientProvider::class.java)

    fun <R> applyWithoutBody(
        clientName: String,
        method: WebClientMethod,
        uri: URI,
        timeout: Long,
        headers: Map<String, String>?,
        responseTypeReference: Class<R>
    ): R {
        val path = uri.path
        log.info("Calling {} {} {}", clientName, method.name, path)

        val requestSpec = when (method) {
            WebClientMethod.GET -> webClient.get()
            WebClientMethod.POST -> webClient.post()
            WebClientMethod.PUT -> webClient.put()
            WebClientMethod.DELETE -> webClient.delete()
            WebClientMethod.PATCH -> webClient.patch()
        }

        var uriSpec = requestSpec.uri(path)
        if (headers != null) {
            uriSpec = uriSpec.headers { h -> headers.forEach { (k, v) -> h.set(k, v) } }
        }

        return uriSpec
            .retrieve()
            .onStatus({ statusCode -> statusCode.isError }) { response ->
                log.error("Call to {} response ERROR {} {}", clientName, method.name, path)
                Mono.error(WebClientException("Error calling $clientName: ${response.statusCode()}"))
            }
            .bodyToMono(responseTypeReference)
            .timeout(Duration.ofMillis(timeout))
            .onErrorResume({ ex: Throwable ->
                log.error("Call TIMEOUT {} {} - {}", method.name, path, ex.message)
                Mono.error(WebClientException("Timeout calling $clientName", ex))
            })
            .block()!!
    }
}

class WebClientException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)