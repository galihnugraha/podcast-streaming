package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqGenreDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResGenreDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.entity.GenreEntity
import com.proyekakhir.podcaststreaming.exception.DataExist
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.GenreRepository
import com.proyekakhir.podcaststreaming.repository.PodcastRepository
import com.proyekakhir.podcaststreaming.service.GenreService
import org.springframework.stereotype.Service

@Service
class GenreServiceImpl(
        val genreRepository: GenreRepository,
        val podcastRepository: PodcastRepository
): GenreService {
    override fun insert(req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        val existingGenre = genreRepository.findByGenre(req.genre)

        if (existingGenre != null) {
            throw DataExist("Genre Already Exist")
        } else {
            val insert = GenreEntity(
                    genre = req.genre
            )
            val savedGenre = genreRepository.save(insert)
            val response = ResGenreDto(
                    idGenre = savedGenre.idGenre,
                    genre = savedGenre.genre
            )
            return ResMessageDto(data = response)
        }
    }

    override fun list(): ResMessageDto<List<ResGenreDto>> {
        val genres = genreRepository.findAll()
        val response = arrayListOf<ResGenreDto>()
        for (genre in genres){
            val data = ResGenreDto(
                    idGenre = genre.idGenre!!,
                    genre = genre.genre!!
            )
            response.add(data)
        }
        return ResMessageDto(data = response)
    }

    override fun detail(id: Int): ResMessageDto<ResGenreDto> {
        val genreFind = genreRepository.findById(id).orElseThrow{ DataNotFound("Genre Not Found") }
        val response = ResGenreDto(
                idGenre = genreFind.idGenre!!,
                genre = genreFind.genre!!
        )
        return ResMessageDto(data = response)
    }

    override fun update(id: Int, req: ReqGenreDto): ResMessageDto<ResGenreDto> {
        val genreFind = genreRepository.findById(id).orElseThrow{ DataNotFound("Genre Not Found") }
        val existingGenre = genreRepository.findByGenre(req.genre)

        if (existingGenre != null) {
            throw DataExist("Genre Already Exist")
        } else {
            genreFind.genre = req.genre

            val updateGenre = genreRepository.save(genreFind)
            val response = ResGenreDto(
                    idGenre = updateGenre.idGenre,
                    genre = updateGenre.genre
            )
            return ResMessageDto(data = response)
        }
    }

    override fun delete(id: Int): ResMessageDto<String> {
        val checkId = genreRepository.findById(id)
        if(!checkId.isPresent)
            throw DataNotFound("Genre Not Found")

        val podcasts = podcastRepository.findAllByIdGenre(checkId.get())
        podcasts.forEach { it.idGenre = null }
        podcastRepository.saveAll(podcasts)

        genreRepository.deleteById(id)

        return ResMessageDto()
    }
}