package com.cgi.example.petstore.service.persistence;

import com.cgi.example.petstore.model.NewPet;
import com.cgi.example.petstore.service.persistence.pet.PetDataStoreService;
import com.cgi.example.petstore.service.persistence.pet.PetDocument;
import com.cgi.example.petstore.service.persistence.pet.PetMapper;
import com.cgi.example.petstore.service.persistence.pet.PetRepository;
import com.cgi.example.petstore.utils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetDataStoreServiceTest {

    private final TestData testData = new TestData();

    @Mock
    private PetRepository mockPetRepository;

    private PetDataStoreService petDataStoreService;

    @BeforeEach
    void setUp() {
        PetMapper mapper = Mappers.getMapper(PetMapper.class);
        petDataStoreService = new PetDataStoreService(mapper,
                mockPetRepository);
    }

    @Test
    void shouldSaveSuccessfully() {
        NewPet petToSave = testData.createNewPet();
        when(mockPetRepository.insert(any(PetDocument.class)))
                .thenReturn(testData.createPetDocument());

        petDataStoreService.insertNewPet(petToSave);
        verify(mockPetRepository).insert(any(PetDocument.class));
    }
}