package pe.edu.user.userservice.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.user.userservice.user.domain.service.UserTenantService;
import pe.edu.user.userservice.user.mapping.UserTenantMapper;
import pe.edu.user.userservice.user.resource.UserTenantResource;
import pe.edu.user.userservice.user.resource.create.CreateUserTenantResource;
import pe.edu.user.userservice.user.resource.update.UpdateUserTenantResource;

import javax.validation.Valid;
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@Tag(name = "User tenant", description = "CRUD tenants")
@RestController
@RequestMapping("/api/v1/userstenant")
public class UserTenantController {
    private final UserTenantService userTenantService;
    private final UserTenantMapper userTenantMapper;

    public UserTenantController(UserTenantService userTenantService, UserTenantMapper userTenantMapper) {
        this.userTenantService = userTenantService;
        this.userTenantMapper = userTenantMapper;
    }

    @Operation(summary = "Get a tenant", description = "Get a user tenant stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTenantResource.class)
                    )}
            )
    })
    @GetMapping("/sign-in")
    public UserTenantResource authenticateUserTenant(@RequestParam(name = "email") String email,
                                                           @RequestParam(name = "password") String password) {
        return userTenantMapper.toResource(userTenantService.authenticate(email, password));
    }

    @Operation(summary = "Get a tenant by id", description = "Get a user tenant stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTenantResource.class)
                    )}
            )
    })
    @GetMapping("{id}")
    public ResponseEntity<UserTenantResource> getATenantById(@PathVariable Long id) {
        UserTenantResource result = userTenantMapper.toResource(userTenantService.findById(id));
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Create a tenant", description = "Create a user tenant in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTenantResource.class)
                    )}
            )
    })
    @PostMapping("/sign-up")
    public UserTenantResource registerUserTenant(@Valid @RequestBody CreateUserTenantResource request) {
        return userTenantMapper.toResource(userTenantService.register(userTenantMapper.toModel(request)));
    }

    @Operation(summary = "Update a tenant", description = "Update a user tenant in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTenantResource.class)
                    )}
            )
    })
    @PutMapping("{userTenantId}")
    public UserTenantResource update(@PathVariable Long userTenantId,
                                           @Valid @RequestBody UpdateUserTenantResource request) {
        return userTenantMapper.toResource(userTenantService.update(userTenantId, userTenantMapper.toModel(request)));
    }

    @Operation(summary = "Delete a tenant", description = "Delete a user tenant in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tenant deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTenantResource.class)
                    )}
            )
    })
    @DeleteMapping("{userTenantId}")
    public ResponseEntity<?> delete(@PathVariable Long userTenantId) {
        return userTenantService.delete(userTenantId);
    }
}