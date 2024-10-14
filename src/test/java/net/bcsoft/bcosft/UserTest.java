package net.bcsoft.bcosft;

import net.bcsoft.bcosft.controller.UserController;
import net.bcsoft.bcosft.dto.UsersDTO;
import net.bcsoft.bcosft.exception.BadRequestException;
import net.bcsoft.bcosft.exception.ConflictException;
import net.bcsoft.bcosft.exception.NoContentException;
import net.bcsoft.bcosft.exception.NotFound;
import net.bcsoft.bcosft.service.UserService;
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
public class UserTest {

    @MockBean
    UserService userService;

    @Test
    void get_ok() {
        List <UsersDTO> usersDTOList = new ArrayList<>();
        usersDTOList.add(new UsersDTO(0L, "test", "test", "test", "password", "register date", "last access", 0L));

        Mockito.doReturn(usersDTOList).when(userService).selectAll();
        UserController userController = new UserController(userService);

        ResponseEntity re = userController.get();
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void get_noContent() {
        Mockito.doThrow(new NoContentException("test")).when(userService).selectAll();
        UserController userController = new UserController(userService);
        Assertions.assertThrows(NoContentException.class, userController::get);
    }

    @Test
    void create_created() throws URISyntaxException {
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test", "test", "password", "register date", "last access", 0L);
        Mockito.doReturn(fakeUserDTO).when(userService).insert(fakeUserDTO);

        UserController userController = new UserController(userService);

        ResponseEntity re = userController.insert(fakeUserDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void create_badRequest() {
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test, test", "test", "password", "register date", "last access", 0L);
        Mockito.doThrow(new BadRequestException("test")).when(userService).insert(fakeUserDTO);
        UserController userController = new UserController(userService);
        Assertions.assertThrows(BadRequestException.class, () -> userController.insert(fakeUserDTO));
    }

    @Test
    void selectById_ok() {
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test", "test", "password", "register date", "last access", 0L);
        Mockito.doReturn(fakeUserDTO).when(userService).selectById(fakeUserDTO.getId());

        UserController userController = new UserController(userService);

        ResponseEntity re = userController.getById(fakeUserDTO.getId());
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void selectById_notFound(){
        Mockito.doThrow(new NotFound("test")).when(userService).selectById(0L);
        UserController userController = new UserController(userService);
        Assertions.assertThrows(NotFound.class, () -> userController.getById(0L));
    }

    @Test
    void update_ok(){
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test", "test", "password", "register date", "last access", 0L);
        Mockito.doReturn(fakeUserDTO).when(userService).update(fakeUserDTO.getId(), fakeUserDTO);

        UserController userController = new UserController(userService);

        ResponseEntity re = userController.update(fakeUserDTO.getId(), fakeUserDTO);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void update_conflict(){
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test", "test", "password", "register date", "last access", 0L);
        Mockito.doThrow(new ConflictException("test")).when(userService).update(fakeUserDTO.getId(), fakeUserDTO);
        UserController userController = new UserController(userService);
        Assertions.assertThrows(ConflictException.class, () -> userController.update(0L, fakeUserDTO));
    }

    @Test
    void delete_noContent(){
        UsersDTO fakeUserDTO = new UsersDTO(0L, "test", "test", "test","password", "register date", "last access", 0L);
        Mockito.doAnswer(invocation -> null).when(userService).delete(fakeUserDTO.getId());
        UserController userController = new UserController(userService);
        ResponseEntity re = userController.delete(0L);
        Assertions.assertEquals(re.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
