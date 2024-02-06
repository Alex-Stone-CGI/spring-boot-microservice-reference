package com.cgi.example.petstore.service.persistence;

import com.cgi.example.petstore.model.Pet;
import com.cgi.example.petstore.model.PetStatus;
import com.cgi.example.petstore.model.PetType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
interface PetAndPetDocumentMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    PetDocument map(Pet petToMap);

    @Mapping(target = "vaccinations", ignore = true)
    Pet map(PetDocument petDocumentToMap);

    List<Pet> mapPetDocuments(List<PetDocument> petDocuments);

    List<String> mapPetStatuses(List<PetStatus> petStatuses);

    default String mapPetStatusToString(PetStatus petStatus) {
        if (Objects.isNull(petStatus)) {
            return null;
        }
        return petStatus.getValue();
    }

    default PetType mapStringToPetType(String petTypeString) {
        if (Objects.isNull(petTypeString)) {
            return null;
        }
        return EnumUtils.getEnumIgnoreCase(PetType.class,
                petTypeString.replace(StringUtils.SPACE, "_"));
    }

    default PetStatus mapStringToPetStatus(String petStatusString) {
        if (Objects.isNull(petStatusString)) {
            return null;
        }
        return EnumUtils.getEnumIgnoreCase(PetStatus.class,
                petStatusString.replace(StringUtils.SPACE, "_"));
    }
}
