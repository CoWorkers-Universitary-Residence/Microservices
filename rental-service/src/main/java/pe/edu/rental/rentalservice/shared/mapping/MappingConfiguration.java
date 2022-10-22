package pe.edu.rental.rentalservice.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.rental.rentalservice.rental.mapping.DateMapper;
import pe.edu.rental.rentalservice.rental.mapping.RentalMapper;

@Configuration("boncesModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
    public DateMapper dateMapper() { return new DateMapper(); }

    @Bean
    public RentalMapper rentalMapper() { return new RentalMapper(); }

}
