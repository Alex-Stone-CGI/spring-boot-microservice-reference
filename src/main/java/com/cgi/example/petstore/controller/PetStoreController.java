package com.cgi.example.petstore.controller;

import com.cgi.example.petstore.api.PetStoreApi;
import com.cgi.example.petstore.controller.validation.PetValidator;
import com.cgi.example.petstore.logging.LogMethodArguments;
import com.cgi.example.petstore.logging.LogMethodResponse;
import com.cgi.example.petstore.model.CustomerRequest;
import com.cgi.example.petstore.model.MultiplePetsResponse;
import com.cgi.example.petstore.model.NewPetRequest;
import com.cgi.example.petstore.model.PetPatchRequest;
import com.cgi.example.petstore.model.PetResponse;
import com.cgi.example.petstore.model.PetStatus;
import com.cgi.example.petstore.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetStoreController implements PetStoreApi {

    private final PetValidator petValidator;
    private final PetService petService;

    @Override
    @LogMethodArguments
    @LogMethodResponse
    public ResponseEntity<PetResponse> addPet(NewPetRequest newPet) {
        PetResponse addedPet = petService.addToPetStore(newPet);

        return ResponseEntity.ok(addedPet);
    }

    @Override
    @LogMethodArguments
    @LogMethodResponse
    public ResponseEntity<MultiplePetsResponse> findPetsByStatus(List<PetStatus> statuses) {
        List<PetResponse> pets = petService.retrieveAllPetsWithAStatusMatching(statuses);

        MultiplePetsResponse petsResponse = new MultiplePetsResponse();
        petsResponse.setPets(pets);

        return ResponseEntity.ok(petsResponse);
    }

    @Override
    @LogMethodArguments
    @LogMethodResponse
    public ResponseEntity<PetResponse> getPetById(String petId) {
        petValidator.validatePetId(petId);

        PetResponse pet = petService.retrievePetDetails(petId);

        return ResponseEntity.ok().body(pet);
    }

    @Override
    @LogMethodArguments
    @LogMethodResponse
    public ResponseEntity<PetResponse> purchasePet(String petId, CustomerRequest customer) {
        petValidator.validatePetId(petId);

        PetResponse purchasedPet = petService.purchase(petId, customer);

        return ResponseEntity.ok().body(purchasedPet);
    }

    @Override
    @LogMethodArguments
    @LogMethodResponse
    public ResponseEntity<PetResponse> patchPet(PetPatchRequest petPatch) {
        PetResponse patchedPet = petService.patch(petPatch);

        return ResponseEntity.ok().body(patchedPet);
    }
}
