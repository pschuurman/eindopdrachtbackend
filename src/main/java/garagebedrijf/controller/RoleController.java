package garagebedrijf.controller;

import garagebedrijf.dto.RoleDto;
import garagebedrijf.model.Role;
import garagebedrijf.repository.RoleRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final RoleRepository repos;

    public RoleController(RoleRepository repos) {
        this.repos = repos;
    }

    @PostMapping("/roles")
    public String createRole(@RequestBody RoleDto role) {
        Role newRole = new Role();
        newRole.setRolename(role.rolename);
        repos.save(newRole);

        return "Done";
    }
}
