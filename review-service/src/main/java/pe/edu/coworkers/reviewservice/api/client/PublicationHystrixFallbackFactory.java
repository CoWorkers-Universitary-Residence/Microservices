package pe.edu.coworkers.reviewservice.api.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.edu.coworkers.reviewservice.domain.model.model.Publication;


@Component
public class PublicationHystrixFallbackFactory implements PublicationClient {
    @Override
    public ResponseEntity<Publication> getPublication(Long id) {
        Publication publication = Publication.builder()
                .about("none")
                .price(0)
                .escrow(0)
                .extra_expenses("none")
                .gender("none")
                .availability(false)
                .rooms(1)
                .bathrooms("none")
                .width(0)
                .height(0)
                .city("none")
                .country("none")
                .address("none")
                .visit(false)
                .views(0)
                .build();
        return ResponseEntity.ok(publication);
    }
}
