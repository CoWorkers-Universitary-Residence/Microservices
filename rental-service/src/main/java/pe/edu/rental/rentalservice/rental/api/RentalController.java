package pe.edu.rental.rentalservice.rental.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.rental.rentalservice.rental.domain.service.RentalService;
import pe.edu.rental.rentalservice.rental.mapping.RentalMapper;
import pe.edu.rental.rentalservice.rental.resource.RentalResource;
import pe.edu.rental.rentalservice.rental.resource.create.CreateRentalResource;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "Rental", description = "CRUD rentals")
@RestController
@RequestMapping(value = "api/v1/rental")
public class RentalController {

    private final RentalService rentalService;

    private final RentalMapper mapper;

    public RentalController(RentalService rentalService, RentalMapper mapper) {
        this.rentalService = rentalService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<RentalResource> getAllRental(Pageable pageable)
    {
        return mapper.modelListPage(rentalService.getAll(), pageable);
    }

    @GetMapping("{id}")
    public RentalResource getRentalById(@PathVariable Long id) { return mapper.toResource(rentalService.getById(id)); }

    @GetMapping("dateId={dateId}")
    public RentalResource getRentalByDateId(@PathVariable Long dateId){
        return mapper.toResource(rentalService.getByDateId(dateId));
    }

    @PostMapping("{dateId}")
    public RentalResource createRental(@PathVariable Long dateId, @RequestBody CreateRentalResource resource) {
        return mapper.toResource(rentalService.create(dateId, mapper.toModel(resource)));
    }

    @PutMapping("{id}")
    public RentalResource updateRental(@PathVariable Long id, @RequestBody CreateRentalResource resource) {
        return mapper.toResource(rentalService.update(id, mapper.toModel(resource)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRental(@PathVariable Long id){
        return rentalService.delete(id);
    }

}
