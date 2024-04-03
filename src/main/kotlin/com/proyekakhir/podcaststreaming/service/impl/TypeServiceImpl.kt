package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqTypeDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResTypeDto
import com.proyekakhir.podcaststreaming.domain.entity.TypeEntity
import com.proyekakhir.podcaststreaming.exception.DataExist
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.TypeRepository
import com.proyekakhir.podcaststreaming.service.TypeService
import org.springframework.stereotype.Service

@Service
class TypeServiceImpl(
        val typeRepository: TypeRepository
): TypeService {
    override fun insert(req: ReqTypeDto): ResMessageDto<ResTypeDto> {
        val existingIdType = typeRepository.findByIdType(req.idType)
        val existingType = typeRepository.findByType(req.type)

        if (existingIdType != null) {
            throw DataExist("ID Type Already Exist")
        } else if (existingType != null) {
            throw DataExist("Type Already Exist")
        } else {
            val insert = TypeEntity(
                    idType = req.idType,
                    type = req.type
            )
            val savedType = typeRepository.save(insert)
            val response = ResTypeDto(
                    idType = savedType.idType,
                    type = savedType.type
            )
            return ResMessageDto(data = response)
        }
    }

    override fun list(): ResMessageDto<List<ResTypeDto>> {
        val types = typeRepository.findAll()
        val response = arrayListOf<ResTypeDto>()
        for (type in types){
            val data = ResTypeDto(
                    idType = type.idType!!,
                    type = type.type!!
            )
            response.add(data)
        }
        return ResMessageDto(data = response)
    }

    override fun detail(id: String): ResMessageDto<ResTypeDto> {
        val typeFind = typeRepository.findById(id).orElseThrow{ DataNotFound("Type Not Found") }
        val response = ResTypeDto(
                idType = typeFind.idType!!,
                type = typeFind.type!!
        )
        return ResMessageDto(data = response)
    }

    override fun update(id: String, req: ReqTypeDto): ResMessageDto<ResTypeDto> {
        val typeFind = typeRepository.findById(id).orElseThrow{ DataNotFound("Type Not Found") }

        val existingIdType = typeRepository.findByIdType(req.idType)
        val existingType = typeRepository.findByType(req.type)

        if (existingIdType != null) {
            throw DataExist("ID Type Already Exist")
        } else if (existingType != null) {
            throw DataExist("Type Already Exist")
        } else {
            typeFind.type = req.type

            val updateType = typeRepository.save(typeFind)
            val response = ResTypeDto(
                    idType = updateType.idType,
                    type = updateType.type
            )
            return ResMessageDto(data = response)
        }
    }

    override fun delete(id: String): ResMessageDto<String> {
        val checkId = typeRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFound("Type Not Found")

        typeRepository.deleteById(id)

        return ResMessageDto()
    }
}