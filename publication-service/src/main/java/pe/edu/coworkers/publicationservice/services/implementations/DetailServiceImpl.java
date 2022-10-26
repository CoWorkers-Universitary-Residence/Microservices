package pe.edu.coworkers.publicationservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.edu.coworkers.publicationservice.entities.Detail;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.repositories.DetailRepository;
import pe.edu.coworkers.publicationservice.services.DetailService;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailRepository detailRepository;

    @Override
    public List<Detail> getAll() {
        return detailRepository.findAll();
    }

    @Override
    public Detail getById(Long id) {
        return detailRepository.findById(id).orElse(null);
    }

    @Override
    public Detail create(Detail detail) {
        return detailRepository.save(detail);
    }

    @Override
    public Detail update(Detail detail) {
        Detail detail1 = getById(detail.getId());
        if (detail1 == null){
            return null;
        }
        detail1.setName(detail.getName());
        detail1.setDescription(detail.getDescription());
        detail1.setPublication(detail.getPublication());
        return detailRepository.save(detail1);
    }

    @Override
    public Optional<ResponseEntity<?>> delete(Long detailId) {
        return detailRepository.findById(detailId).map(detail -> {
            detailRepository.delete(detail);
            return ResponseEntity.ok().build();
        });
    }

    @Override
    public List<Detail> findByPublication(Publication publication) {
        return detailRepository.findByPublication(publication);
    }
}
