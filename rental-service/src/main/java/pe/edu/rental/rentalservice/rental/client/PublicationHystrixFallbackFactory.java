package pe.edu.rental.rentalservice.rental.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pe.edu.rental.rentalservice.rental.model.Publication;

//@Component
public class PublicationHystrixFallbackFactory/* implements PublicationClient*/ {
    /*
    @Override
    public ResponseEntity<Publication> getPublication(Long id) {
        Publication publication = Publication.builder()
                .about("")
                .price(0)
                .escrow(0)
                .extra_expenses("")
                .gender("")
                .availability(false)
                .rooms(0)
                .bathrooms("")
                .width(0)
                .height(0)
                .address("")
                .country("")
                .city("")
                .views(0)
                .visit(false).build();
        return ResponseEntity.ok(publication);
    }*/
}
