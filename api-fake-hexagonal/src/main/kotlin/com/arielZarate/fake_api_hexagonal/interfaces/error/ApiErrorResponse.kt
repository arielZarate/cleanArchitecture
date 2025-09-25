package com.arielZarate.fake_api_hexagonal.interfaces.error

data class ApiErrorResponse(
    val type: String? = null,     // URL o identificador del tipo de error
    val title: String,            // Título legible del error
    val status: Int,              // Código HTTP
    val detail: String?,          // Detalle opcional
    val instance: String? = null  // URI de la solicitud que causó el error
) {
}