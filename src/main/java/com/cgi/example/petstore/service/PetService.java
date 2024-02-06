package com.cgi.example.petstore.service;

import com.cgi.example.petstore.exception.NotFoundException;
import com.cgi.example.petstore.model.Customer;
import com.cgi.example.petstore.model.NewPet;
import com.cgi.example.petstore.model.Pet;
import com.cgi.example.petstore.model.PetPatch;
import com.cgi.example.petstore.model.PetStatus;
import com.cgi.example.petstore.model.Vaccination;
import com.cgi.example.petstore.service.persistence.DataStoreService;
import com.cgi.example.petstore.thirdparty.vaccinations.VaccinationsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PetService {

    private final VaccinationsService vaccinationsService;
    private final NewPetToPetMapper newPetToPetMapper;
    private final DataStoreService dataStoreService;

    public Pet addToPetStore(NewPet newPet) {

        Pet pet = newPetToPetMapper.map(newPet);

        dataStoreService.save(pet);

        List<Vaccination> vaccinations = vaccinationsService.getVaccinationDetails(pet.getVaccinationId());
        pet.setVaccinations(vaccinations);

        return pet;
    }

    public Pet retrievePetDetails(Long petId) {
        Optional<Pet> optionalPet = dataStoreService.findPetById(petId);

        if (optionalPet.isEmpty()) {
            String message = "Unable to find the pet with Id: [%d]".formatted(petId);
            throw new NotFoundException(message);
        }

        Pet pet = optionalPet.get();
        List<Vaccination> vaccinations = vaccinationsService.getVaccinationDetails(pet.getVaccinationId());
        pet.setVaccinations(vaccinations);

        return pet;
    }

    public List<Pet> retrieveAllPetsWithAStatusMatching(List<PetStatus> statuses) {
        return dataStoreService.findPetsByStatus(statuses);
    }

    public Pet patch(PetPatch pet) {
        Pet patchedPet = dataStoreService.patch(pet);
        log.debug("Successfully patched the pet with petId [{}]", patchedPet.getId());
        return patchedPet;
    }

    public Pet purchase(long petId, Customer customer) {
        return null;
    }
}
