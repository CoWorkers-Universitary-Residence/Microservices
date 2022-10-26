package pe.edu.rental.rentalservice.rental.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.edu.rental.rentalservice.rental.domain.entity.Date;
import pe.edu.rental.rentalservice.rental.resource.DateResource;
import pe.edu.rental.rentalservice.rental.resource.create.CreateDateResource;
import pe.edu.rental.rentalservice.rental.resource.update.UpdateDateResource;
import pe.edu.rental.rentalservice.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class DateMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public DateResource toResource(Date model) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.typeMap(Date.class, DateResource.class)
                .addMapping(dt -> dt.getPublicationId(), DateResource::setPublicationId).map(model);
    }

    public Date toModel(CreateDateResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, Date.class);
    }

    public Date toModel(UpdateDateResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, Date.class);
    }

    public Page<DateResource> modelListPage (List<Date> modelList, Pageable pageable) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.typeMap(Date.class, DateResource.class)
                .addMapping(dt -> dt.getPublicationId(), DateResource::setPublicationId);
        return new PageImpl<>(mapper.mapList(modelList, DateResource.class), pageable, modelList.size());
    }

}
