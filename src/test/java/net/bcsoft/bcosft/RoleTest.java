package net.bcsoft.bcosft;

import net.bcsoft.bcosft.controller.RoleController;
import net.bcsoft.bcosft.dto.RoleDTO;
import net.bcsoft.bcosft.exception.BadRequestException;
import net.bcsoft.bcosft.exception.ConflictException;
import net.bcsoft.bcosft.exception.NoContentException;
import net.bcsoft.bcosft.exception.NotFound;
import net.bcsoft.bcosft.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RoleTest {

    @MockBean
    RoleService roleService;

    @Test
    void get_ok() {
        List <RoleDTO> roleDTOList = new ArrayList<>();
        roleDTOList.add(new RoleDTO(0L, "user1", "nome"));

        Mockito.doReturn(roleDTOList).when(roleService).getRoles();
        RoleController roleController = new RoleController(roleService);

        ResponseEntity re = roleController.getRoles();
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void get_noContent() {
        Mockito.doThrow(new NoContentException("test")).when(roleService).getRoles();
        RoleController roleController = new RoleController(roleService);
        Assertions.assertThrows(NoContentException.class, roleController::getRoles);
    }

    @Test
    void create_created() throws URISyntaxException {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doReturn(fakeRoleDTO).when(roleService).insert(fakeRoleDTO);
        RoleController roleController = new RoleController(roleService);

        ResponseEntity re = roleController.insert(fakeRoleDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void create_badRequest() {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doThrow(new BadRequestException("test")).when(roleService).insert(fakeRoleDTO);
        RoleController roleController = new RoleController(roleService);
        Assertions.assertThrows(BadRequestException.class, () -> roleController.insert(fakeRoleDTO));
    }

    @Test
    void selectByUserId_ok() {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doReturn(fakeRoleDTO).when(roleService).getRoleByUserId(0L);

        RoleController roleController = new RoleController(roleService);

        ResponseEntity re = roleController.getByUserId(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void selectByUserId_notFound() {
        Mockito.doThrow(new NotFound("test")).when(roleService).getRoleByUserId(0L);
        RoleController roleController = new RoleController(roleService);
        Assertions.assertThrows(NotFound.class, () -> roleController.getByUserId(0L));
    }

    @Test
    void update_ok() {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doReturn(fakeRoleDTO).when(roleService).update(fakeRoleDTO);

        RoleController roleController = new RoleController(roleService);

        ResponseEntity re = roleController.put(fakeRoleDTO, fakeRoleDTO.getId());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void update_conflict() {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doThrow(new ConflictException("test")).when(roleService).update(fakeRoleDTO);
        RoleController roleController = new RoleController(roleService);
        Assertions.assertThrows(ConflictException.class, () -> roleController.put(fakeRoleDTO, 0L));
    }

    @Test
    void delete_noContent() {
        RoleDTO fakeRoleDTO = new RoleDTO(0L, "user1", "nome");
        Mockito.doAnswer(invocation -> null).when(roleService).delete(fakeRoleDTO.getId());
        RoleController roleController = new RoleController(roleService);
        ResponseEntity re = roleController.delete(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
