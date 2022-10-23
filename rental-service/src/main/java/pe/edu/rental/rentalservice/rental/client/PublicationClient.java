package pe.edu.rental.rentalservice.rental.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.rental.rentalservice.rental.model.Publication;

@FeignClient(name = "publication-service", decode404 = true/*, fallback = PublicationHystrixFallbackFactory.class*/)
public interface PublicationClient {

    @GetMapping(path = "/api/v1/publications/{id}")
    public ResponseEntity<Publication> getPublication(@PathVariable("id") Long id);

}
