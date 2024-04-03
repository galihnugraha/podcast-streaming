package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqEncodeUserJwtDto
import com.proyekakhir.podcaststreaming.domain.dto.request.ReqLoginDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResEncodeJwtDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.UserRepository
import com.proyekakhir.podcaststreaming.service.LoginService
import com.proyekakhir.podcaststreaming.utils.JwtGenerator
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(
        val userRepository: UserRepository
): LoginService {
    override fun login(req:ReqLoginDto): ResMessageDto<ResEncodeJwtDto> {
        val passwordEncoder = BCryptPasswordEncoder()

        val checkUsername = userRepository.findByUsername(req.username)
                ?: throw DataNotFound("username not found")

        if (!passwordEncoder.matches(req.password, checkUsername.password))
            throw DataNotFound("password incorrect")

        val reqToken = ReqEncodeUserJwtDto(
                idUser = checkUsername.idUser.toString(),
                username = checkUsername.username.toString(),
                email = checkUsername.email.toString(),
                type = checkUsername.idType!!.type.toString()
        )

        val token = JwtGenerator().createUserJwt(reqToken)

        return ResMessageDto(data = ResEncodeJwtDto(checkUsername.idUser.toString(), token))
    }
}