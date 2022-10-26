package pe.edu.coworkers.publicationservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.publicationservice.client.UserOwnerClient;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.model.UserOwner;
import pe.edu.coworkers.publicationservice.repositories.PublicationRepository;
import pe.edu.coworkers.publicationservice.services.PublicationService;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserOwnerClient userOwnerClient;

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication getById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    @Override
    public Publication create(Publication publication) {
        Long ownerId = publication.getOwnerId();

        ResponseEntity<UserOwner> response = userOwnerClient.getAOwnerById(publication.getOwnerId());
        UserOwner userOwner = response.getBody();

        if (userOwner == null){
            return null;
        }

        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Publication publication) {
        Publication publication1 = getById(publication.getId());
        if (publication1 == null){
            return null;
        }
        publication1.setAbout(publication.getAbout());
        publication1.setPrice(publication.getPrice());
        publication1.setEscrow(publication.getEscrow());
        publication1.setExtra_expenses(publication.getExtra_expenses());
        publication1.setGender(publication.getGender());
        publication1.setAvailability(publication.isAvailability());
        publication1.setRooms(publication.getRooms());
        publication1.setBathrooms(publication.getBathrooms());
        publication1.setWidth(publication.getWidth());
        publication1.setHeight(publication.getHeight());
        publication1.setCountry(publication.getCountry());
        publication1.setCity(publication.getCity());
        publication1.setAddress(publication.getAddress());
        publication1.setVisit(publication.isVisit());
        publication1.setViews(publication.getViews());
        return publicationRepository.save(publication1);
    }

    @Override
    public Optional<ResponseEntity<?>> delete(Long publicationId) {
        return publicationRepository.findById(publicationId).map(publication -> {
            publicationRepository.delete(publication);
            return ResponseEntity.ok().build();
        });
    }
}
