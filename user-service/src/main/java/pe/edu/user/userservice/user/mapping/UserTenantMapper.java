package pe.edu.user.userservice.user.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.user.userservice.shared.mapping.EnhancedModelMapper;
import pe.edu.user.userservice.user.domain.model.entities.UserTenant;
import pe.edu.user.userservice.user.resource.create.CreateUserTenantResource;
import pe.edu.user.userservice.user.resource.update.UpdateUserTenantResource;

public class UserTenantMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public UpdateUserTenantResource toResource(UserTenant model) {
        return mapper.map(model, UpdateUserTenantResource.class);
    }

    public UserTenant toModel(CreateUserTenantResource resource) {
        return mapper.map(resource, UserTenant.class);
    }

    public UserTenant toModel(UpdateUserTenantResource resource) {
        return mapper.map(resource, UserTenant.class);
    }
}