package pe.edu.coworkers.publicationservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.coworkers.publicationservice.entities.Detail;
import pe.edu.coworkers.publicationservice.entities.Publication;
import pe.edu.coworkers.publicationservice.repositories.DetailRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class DetailRepositoryTest {
    @Mock
    private DetailRepository detailRepository;

    @Test
    void whenFindByPublicationThenReturnTheListOfDetails(){
        Detail detail1 = Detail.builder()
                .name("detail name 1")
                .description("this is the description")
                .publication(Publication.builder().id(1L).build())
                .build();
        detailRepository.save(detail1);

        Detail detail2 = Detail.builder()
                .name("detail name 2")
                .description("this is the description 2")
                .publication(Publication.builder().id(1L).build())
                .build();
        detailRepository.save(detail2);

        Detail detail3 = Detail.builder()
                .name("detail name 3")
                .description("this is the description 3")
                .publication(Publication.builder().id(1L).build())
                .build();
        detailRepository.save(detail3);

        when(detailRepository.findByPublication(Publication.builder().build())).thenReturn(List.of(detail1, detail2, detail3));
        Assertions.assertEquals(detailRepository.findByPublication(new Publication()).size(), 3);
    }
}
