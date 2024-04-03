package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqUserDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResUserDto
import com.proyekakhir.podcaststreaming.domain.entity.UserEntity
import com.proyekakhir.podcaststreaming.exception.DataExist
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.TypeRepository
import com.proyekakhir.podcaststreaming.repository.UserRepository
import com.proyekakhir.podcaststreaming.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        val userRepository: UserRepository,
        val typeRepository: TypeRepository
): UserService {
    override fun insert(req: ReqUserDto): ResMessageDto<ResUserDto> {
        val existingUsername = userRepository.findByUsername(req.username)
        val existingEmail = userRepository.findByEmail(req.email)

        if (existingEmail != null)
            throw DataExist("Email Already Used")
        else if (existingUsername != null)
            throw DataExist("Username Already Used")
        else {
            val passwordEncoder = BCryptPasswordEncoder()

            val type = if (req.idType == null) {
                typeRepository.findById("T001")
                        .orElseThrow{ DataNotFound("Type Not Found") }
            } else {
                typeRepository.findById(req.idType.toString())
                        .orElseThrow{ DataNotFound("Type Not Found") }
            }

            val insert = UserEntity(
                    username = req.username,
                    email = req.email,
                    password = passwordEncoder.encode(req.password),
                    idType = type
            )
            val savedUser = userRepository.save(insert)
            val response = ResUserDto(
                    idUser = savedUser.idUser!!,
                    username = savedUser.username!!,
                    email = savedUser.email!!,
                    type = savedUser.idType!!.type
            )
            return ResMessageDto(data = response)
        }
    }

    override fun list(): ResMessageDto<List<ResUserDto>> {
        val users = userRepository.findAll()
        val response = arrayListOf<ResUserDto>()
        for (user in users){
            val data = ResUserDto(
                    idUser = user.idUser!!,
                    username = user.username!!,
                    email = user.email!!,
                    type = user.idType!!.type
            )
            response.add(data)
        }
        return ResMessageDto(data = response)
    }

    override fun detail(id: Int): ResMessageDto<ResUserDto> {
        val userFind = userRepository.findById(id).orElseThrow{ DataNotFound("User Not Found") }
        val response = ResUserDto(
                idUser = userFind.idUser!!,
                username = userFind.username!!,
                email = userFind.email!!,
                type = userFind.idType!!.type
        )
        return ResMessageDto(data = response)
    }

    override fun update(id: Int, req: ReqUserDto): ResMessageDto<ResUserDto> {
        val userFind = userRepository.findById(id).orElseThrow{ DataNotFound("User Not Found") }
        val existingUsername = userRepository.findByUsername(req.username)
        val existingEmail = userRepository.findByEmail(req.email)

        if (existingEmail != null)
            throw DataExist("Email Already Used")
        else if (existingUsername != null)
            throw DataExist("Username Already Used")
        else {
            val type = if (req.idType == null) {
                typeRepository.findById("T001")
                        .orElseThrow{ DataNotFound("Type Not Found") }
            } else {
                typeRepository.findById(req.idType.toString())
                        .orElseThrow{ DataNotFound("Type Not Found") }
            }

            userFind.username = req.username
            userFind.email = req.email
            userFind.password = req.password
            userFind.idType = type

            val updateUser = userRepository.save(userFind)
            val response = ResUserDto(
                    idUser = updateUser.idUser!!,
                    username = updateUser.username!!,
                    email = updateUser.email!!,
                    type = updateUser.idType!!.type
            )
            return ResMessageDto(data = response)
        }
    }

    override fun delete(id: Int): ResMessageDto<String> {
        val checkId = userRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFound("User Not Found")

        userRepository.deleteById(id)

        return ResMessageDto()
    }
}