package pe.edu.coworkers.publicationservice.services;

import pe.edu.coworkers.publicationservice.entities.Photo;
import pe.edu.coworkers.publicationservice.entities.Publication;

import java.util.List;

public interface PhotoService {
    public List<Photo> getAll();
    public Photo getById(Long id);
    public Photo create(Photo photo);
    public Photo update(Photo photo);
    public void delete(Long id) throws Exception;
    public List<Photo> findByPublication(Publication publication);
}
