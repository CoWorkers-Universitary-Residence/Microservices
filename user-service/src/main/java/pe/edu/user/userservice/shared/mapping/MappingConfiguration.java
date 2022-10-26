package pe.edu.user.userservice.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.user.userservice.user.mapping.UserOwnerMapper;
import pe.edu.user.userservice.user.mapping.UserTenantMapper;

@Configuration("boncesModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public UserOwnerMapper userOwnerMapper() { return new UserOwnerMapper(); }

    @Bean
    public UserTenantMapper userTenantMapper() { return new UserTenantMapper(); }

    //@Bean
    //public DateMapper dateMapper() { return new DateMapper(); }

}