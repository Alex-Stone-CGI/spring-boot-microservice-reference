package com.cgi.example.petstore.utils;

import com.cgi.example.petstore.model.Address;
import com.cgi.example.petstore.model.Customer;
import com.cgi.example.petstore.model.NewPet;
import com.cgi.example.petstore.model.Pet;
import com.cgi.example.petstore.model.PetInformationItem;
import com.cgi.example.petstore.model.PetStatus;
import com.cgi.example.petstore.model.PetType;
import com.cgi.example.petstore.service.pet.PetDocument;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TestData {

    private static final String PET_ID = "KT1546";
    private static final String PET_NAME = "Fido";
    private static final PetType DOG_PET_TYPE = PetType.DOG;
    private static final String VACCINATION_ID = "AF54785412K";
    private static final List<String> PHOTO_URLS =
            List.of("https://www.freepik.com/free-photo/isolated-happy-smiling-dog-white-background-portrait-4_39994000.htm#uuid=4f38a524-aa89-430d-8041-1de9ffb631c6");

    public Pet createPet() {
        Pet pet = new Pet();

        pet.setPetId(PET_ID);
        pet.setVaccinationId(VACCINATION_ID);
        pet.setVaccinations(Collections.emptyList());
        pet.setName(PET_NAME);
        pet.setPetType(DOG_PET_TYPE);
        pet.photoUrls(PHOTO_URLS);
        pet.setPetStatus(PetStatus.AVAILABLE_FOR_PURCHASE);
        pet.setAdditionalInformation(Collections.emptyList());

        return pet;
    }

    public PetDocument createPetDocument() {
        return createPetDocument(PET_ID);
    }

    public PetDocument createPetDocument(String petId) {
        LocalDateTime now = LocalDateTime.now();
        return PetDocument.builder()
                .petId(petId)
                .vaccinationId(VACCINATION_ID)
                .name(PET_NAME)
                .petType(DOG_PET_TYPE.getValue())
                .photoUrls(PHOTO_URLS)
                .additionalInformation(Collections.emptyList())
                .petStatus(PetStatus.AVAILABLE_FOR_PURCHASE.getValue())
                .createdAt(now)
                .lastModified(now)
                .build();
    }

    public NewPet createNewPet() {
        NewPet pet = new NewPet();

        pet.setVaccinationId(VACCINATION_ID);
        pet.setName(PET_NAME);
        pet.setPetType(DOG_PET_TYPE);
        pet.photoUrls(PHOTO_URLS);
        pet.setAdditionalInformation(List.of(createPetInformationItem("Personality", "Energetic")));

        return pet;
    }

    public PetInformationItem createPetInformationItem(String name, String description) {
        PetInformationItem petInformationItem = new PetInformationItem();

        petInformationItem.setName(name);
        petInformationItem.setDescription(description);

        return petInformationItem;
    }

    public Customer createCustomer() {
        Customer customer = new Customer();

        customer.setCustomerId(246879L);
        customer.setUsername("alex.stone");
        customer.setFirstName("Alex");
        customer.setLastName("Stone");
        customer.email("alex.stone@cgi.com");
        customer.setAddress(createAddress());

        return customer;
    }

    private Address createAddress() {
        Address address = new Address();

        address.setStreet("40 Princes Street");
        address.setCity("Edinburgh");
        address.setPostCode("EH2 2BY");
        address.setCountry("United Kingdom");

        return address;
    }
}
