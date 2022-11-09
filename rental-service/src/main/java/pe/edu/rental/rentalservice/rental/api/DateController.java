package pe.edu.rental.rentalservice.rental.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.rental.rentalservice.rental.domain.service.DateService;
import pe.edu.rental.rentalservice.rental.mapping.DateMapper;
import pe.edu.rental.rentalservice.rental.resource.DateResource;
import pe.edu.rental.rentalservice.rental.resource.create.CreateDateResource;
import pe.edu.rental.rentalservice.rental.resource.update.UpdateDateResource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@Tag(name = "Date", description = "CRUD dates")
@RequestMapping(value = "api/v1/dates")
public class DateController {

    private final DateService dateService;

    private final DateMapper mapper;

    public DateController(DateService dateService, DateMapper mapper) {
        this.dateService = dateService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DateResource> getAllDates(Pageable pageable)
    {
        return mapper.modelListPage(dateService.getAll(), pageable);
    }

    @GetMapping("{id}")
    public DateResource getDateById(@PathVariable Long id) {
        return mapper.toResource(dateService.getById(id));
    }

    @GetMapping("publicationId={publicationId}")
    public Page<DateResource> getAllByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByPublicationId(publicationId), pageable);
    }

    @GetMapping("tenantId={tenantId}")
    public Page<DateResource> getAllByTenantId(@PathVariable Long tenantId, Pageable pageable) {
        return mapper.modelListPage(dateService.getAllByTenantId(tenantId), pageable);
    }

    @PostMapping
    public DateResource createDate(@RequestBody CreateDateResource resource) {
        return mapper.toResource(dateService.create(mapper.toModel(resource)));
    }

    @PutMapping("{id}")
    public DateResource updateDate(@PathVariable Long id, @RequestBody UpdateDateResource resource) {
        return mapper.toResource(dateService.update(id, mapper.toModel(resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDate(@PathVariable Long id) {
        return dateService.delete(id);
    }
}
