package com.offermicroservice.service.impl

import com.offermicroservice.entity.Offer
import com.offermicroservice.repository.OfferRepository
import com.offermicroservice.service.OfferService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OfferServiceImpl(@Autowired private val offerRepository: OfferRepository):OfferService {

    override fun saveOffer(offer: Offer): Offer {
        if(findById(offer.id).isPresent){
            throw Exception("La oferta ya existe")
        }
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
            val optionalOffer = offerRepository.findById(offer.id)
            optionalOffer.get().name = offer.name
            optionalOffer.get().state = offer.state
            optionalOffer.get().description = offer.description
            optionalOffer.get().date = offer.date
            optionalOffer.get().timeBegin = offer.timeBegin
            optionalOffer.get().timeFinal = offer.timeFinal
            return optionalOffer.get()
        }
        throw Exception("Error al actualizar")
    }
}