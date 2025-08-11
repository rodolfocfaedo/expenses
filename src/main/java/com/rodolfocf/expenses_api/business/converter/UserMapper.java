package com.rodolfocf.expenses_api.business.converter;

import com.rodolfocf.expenses_api.business.dto.UserRequestDTO;
import com.rodolfocf.expenses_api.business.dto.UserResponseDTO;
import com.rodolfocf.expenses_api.infrastructure.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toUserEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UserRequestDTO userRequestDTO, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patchEntityFromDto(UserRequestDTO userRequestDTO, @MappingTarget User user);

}
