package pe.edu.user.userservice.user.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.user.userservice.shared.mapping.EnhancedModelMapper;
import pe.edu.user.userservice.user.domain.model.entities.UserOwner;
import pe.edu.user.userservice.user.resource.create.CreateUserOwnerResource;
import pe.edu.user.userservice.user.resource.update.UpdateUserOwnerResource;

public class UserOwnerMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public UpdateUserOwnerResource toResource(UserOwner model) {
        return mapper.map(model, UpdateUserOwnerResource.class);
    }

    public UserOwner toModel(CreateUserOwnerResource resource) {
        return mapper.map(resource, UserOwner.class);
    }

    public UserOwner toModel(UpdateUserOwnerResource resource) {
        return mapper.map(resource, UserOwner.class);
    }
}