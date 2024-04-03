package com.proyekakhir.podcaststreaming.utils

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqEncodeUserJwtDto
import com.proyekakhir.podcaststreaming.exception.InvalidToken
import io.jsonwebtoken.*
import java.util.*
import javax.crypto.spec.SecretKeySpec

class JwtGenerator {
    companion object {
        private const val SECRET_KEY = "YOUR_SUPER_SECRET_KEY_THAT_IT_AT_LEAST_256_BITS_LONG"
    }

    fun createUserJwt(req:ReqEncodeUserJwtDto):String {
        val signatureAlgorithm: SignatureAlgorithm = SignatureAlgorithm.HS256
        val nowMills: Long = System.currentTimeMillis()

        val apiKeySecretBytes = SECRET_KEY.toByteArray()
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder: JwtBuilder = Jwts.builder().setSubject(req.idUser)
                .claim("id", req.idUser)
                .claim("username", req.username)
                .claim("email", req.email)
                .claim("type", req.type)
                .setIssuer("technocenter")
                .setAudience("technocenter")
                .signWith(signingKey, signatureAlgorithm)

        val expMills = nowMills + 3600000L
        val exp = Date(expMills)
        builder.setExpiration(exp)

        return builder.compact()
    }

    fun decodeJwt(jwt:String): Claims {
        try {
            val claims: Claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.toByteArray())
                    .build()
                    .parseClaimsJws(jwt).body

            return claims
        } catch (e: JwtException) {
            e.printStackTrace()
            throw InvalidToken("Invalid Token")
        }
    }
}