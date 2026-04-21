package com.arielZarate.fake_api_hexagonal.infraestructure.rest.provider

import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
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
        val methodName = method.name
        log.info("Calling {} {} {}", clientName, methodName, uri)

        return webClient
            .method(HttpMethod.valueOf(methodName))
            .uri(uri)
            .headers { header ->
                headers?.forEach { (k, v) -> header.set(k, v) }
            }
            .retrieve()
            .onStatus({ statusCode -> statusCode.isError }) { response ->
                log.error("Call to {} response ERROR {} {}", clientName, methodName, uri)
                Mono.error(WebClientException("Error calling $clientName: ${response.statusCode()}"))
            }
            .bodyToMono(responseTypeReference)
            .timeout(Duration.ofMillis(timeout))
            .onErrorResume({ ex: Throwable ->
                log.error("Call TIMEOUT {} {} - {}", methodName, uri, ex.message)
                Mono.error(WebClientException("Timeout calling $clientName", ex))
            })
            .block()!!
    }
}

class WebClientException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)