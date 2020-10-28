package com.rmurugaian.myrewards.exception

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.pozo.KotlinBuilder
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

@KotlinBuilder
data class HttpErrorInfo constructor(
        val timestamp: ZonedDateTime = ZonedDateTime.now(),
        val path: String,
        val message: String?,
        @JsonProperty("status")
        @JsonDeserialize(using = HttpStatusDeserializer::class)
        val httpStatus: HttpStatus
)

class HttpStatusDeserializer(vc: Class<*>?) : StdDeserializer<HttpStatus>(vc) {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): HttpStatus {
        val status: JsonNode = p.codec.readTree(p)
        return HttpStatus.valueOf(status.asInt())
    }
}
