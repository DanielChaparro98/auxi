package com.offermicroservice.service.impl

import com.offermicroservice.dto.OfferDto
import com.offermicroservice.entity.Offer
import com.offermicroservice.enums.State
import com.offermicroservice.repository.OfferRepository
import com.offermicroservice.service.OfferService
import jakarta.persistence.EntityNotFoundException
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Service
class OfferServiceImpl(@Autowired private val offerRepository: OfferRepository, @Autowired private val modelMapper: ModelMapper):OfferService {

    override fun saveOffer(offerDto: OfferDto): Offer {
        if(offerDto.state != State.NUEVO.toString()){
            offerDto.state = State.NUEVO.toString()
        }
        val offer:Offer = modelMapper.map(offerDto,Offer::class.java)
        return offerRepository.save(offer)
    }

    override fun listOffer(): List<Offer> {
        return offerRepository.findAll()
    }

    override fun deleteOffer(id: Long): String {
        try {
            offerRepository.deleteById(id)
        }catch (e: Exception){
            throw Exception("Error al eliminar oferta")
        }
        return "Oferta Elimanada"
    }

    override fun findById(id: Long): Optional<Offer> {
        val offerOptional = offerRepository.findById(id);
        return offerOptional
    }

    override fun findByState(state: String): List<Offer> {
        return offerRepository.findByState(state)
    }

    override fun oneOffer(id: Long): Offer {
        if(findById(id).isPresent){
            val oneOffer = findById(id).get()
            return oneOffer
        }
        throw Exception("Error al encontrar la oferta")
    }

    override fun updateOffer(offer: Offer): Offer {
        if(findById(offer.id).isPresent){
            val optionalOffer = offerRepository.findById(offer.id);
            optionalOffer.get().name = offer.name
            optionalOffer.get().state = offer.state
            optionalOffer.get().description = offer.description
            optionalOffer.get().date = offer.date
            optionalOffer.get().startTime = offer.startTime
            optionalOffer.get().finalDate = offer.finalDate
            optionalOffer.get().finalDate = offer.finalDate
            optionalOffer.get().email = offer.email
            optionalOffer.get().price = offer.price
            return offerRepository.save(optionalOffer.get())
        }
        throw Exception("Error al actualizar")
    }

    override fun findByEmail(email: String): List<Offer> {
        return offerRepository.findByEmail(email)
    }

    override fun selectOffer(idOffer:Long,emailUser: String): Offer {
        var findByOffer =
            offerRepository.findById(idOffer).orElseThrow { EntityNotFoundException("Oferta no encontrada") }
        emailUser?.let { findByOffer.emailUser = it }
        findByOffer.state = State.AGENDADO.toString()
        return  offerRepository.save(findByOffer)
    }

    override fun findByEmailAndState(email: String, state: String): List<Offer> {
        val filter = offerRepository.findByEmailAndState(email, state)
        if (filter.isEmpty()) {
            throw Exception("No existen ofertas")
        }
        return filter
    }

    override fun findByEmailUser(emailUser: String): List<Offer> {
        val filter =  offerRepository.findByEmailUser(emailUser)
        if(filter.isEmpty()){
            throw Exception("Email no existe")
        }
        return filter
    }

    override fun cancelOffer(idOffer: Long, emailUser: String): Offer {
        var findOffer =
            offerRepository.findById(idOffer).orElseThrow { EntityNotFoundException("Oferta no encontrada") }
        emailUser?.let { findOffer.emailUser = it }
        findOffer.state = State.CANCELADO.toString()
        return offerRepository.save(findOffer)
    }

    override fun successOffer(idOffer: Long, emailUser: String): Offer {
        var findOffer = offerRepository.findById(idOffer).orElseThrow { EntityNotFoundException("Oferta no encontrada") }
        emailUser?.let { findOffer.emailUser = it }
        findOffer.state = State.FINALIZADO.toString()
        return offerRepository.save(findOffer)
    }

    override fun validateDateOffer(id: Long): Boolean {
        var findOffer = offerRepository.findById(id).orElseThrow { EntityNotFoundException("No existe oferta")}
        var dateNow = LocalDate.now()
        var zoneId = ZoneId.of("America/Bogota")
        var timeNow = LocalTime.now(zoneId)
        val offerDate = findOffer.date?.toInstant()?.atZone(zoneId)?.toLocalDate()
        val offerTime = findOffer.finalDate
        val isOfferTimeBefore = offerTime?.isAfter(timeNow);
        val isOfferDateBefore = offerDate?.isAfter(dateNow)
        if (offerDate != null) {
            return isOfferDateBefore == true && isOfferTimeBefore == true
        }
        throw Exception("Validación de fechas")
    }
}