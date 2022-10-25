package pe.edu.rental.rentalservice.rental.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.rental.rentalservice.rental.domain.entity.Rental;
import pe.edu.rental.rentalservice.rental.resource.RentalResource;
import pe.edu.rental.rentalservice.rental.resource.create.CreateRentalResource;
import pe.edu.rental.rentalservice.rental.resource.update.UpdateRentalResource;
import pe.edu.rental.rentalservice.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class RentalMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public RentalResource toResource(Rental model) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(model, RentalResource.class);
    }

    public Rental toModel(CreateRentalResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, Rental.class);
    }

    public Rental toModel(UpdateRentalResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, Rental.class);
    }

    public Page<RentalResource> modelListPage (List<Rental> modelList, Pageable pageable) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return new PageImpl<>(mapper.mapList(modelList, RentalResource.class), pageable, modelList.size());
    }

}
