package com.example.unit.resources.user;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.unit.template.VanHackTemplateLoader;
import com.example.unit.util.ParserUtil;
import com.vanhack.api.core.exception.EntityAlreadyExistsException;
import com.vanhack.api.core.exception.EntityNotFoundException;
import com.vanhack.api.core.service.user.UserServiceImpl;
import com.vanhack.api.resources.infraestructure.ApiExceptionHandler;
import com.vanhack.api.resources.user.UserController;
import com.vanhack.api.resources.user.request.UserRequest;
import com.vanhack.api.resources.user.response.UserResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private static final String USER_CONTROLLER_PATH = "/v1/users";

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    private static MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();

        FixtureFactoryLoader.loadTemplates("com.example.unit.template");
    }

    @Test
    public void fetchUsersTest() throws Exception {

        mockMvc.perform(get(USER_CONTROLLER_PATH)
                .param("slug", "user-slug")
                .param("name", "user-name"))
                .andExpect(status().isOk());

        verify(userService, times(1)).getUser("user-slug", "user-name");

    }

    @Test
    public void testeCreateUser() throws Exception {

        UserRequest user = Fixture.from(UserRequest.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_REQUEST);

        when(userService.createUser(user)).thenReturn(Fixture.from(UserResponse.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_RESPONSE));

        mockMvc.perform(post(USER_CONTROLLER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserUtil.asJsonString(user))).andExpect(status().isCreated());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testCreateUserWithDuplicatedSlug() throws Exception {

        UserRequest user = Fixture.from(UserRequest.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_REQUEST);

        when(userService.createUser(user)).thenThrow(new EntityAlreadyExistsException("Test"));

        mockMvc.perform(post(USER_CONTROLLER_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserUtil.asJsonString(user))).andExpect(status().isConflict());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void updateUserTest() throws Exception {
        UserRequest user = Fixture.from(UserRequest.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_REQUEST);

        when(userService.updateUser(user, "old-slug")).thenReturn(Fixture.from(UserResponse.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_RESPONSE));

        mockMvc.perform(put(USER_CONTROLLER_PATH + "/old-slug", "userSlug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserUtil.asJsonString(user))).andExpect(status().isOk());

        verify(userService, times(1)).updateUser(user, "old-slug");
    }

    @Test
    public void updateUserWithAlreadyExistingNewSlugTest() throws Exception {

        UserRequest user = Fixture.from(UserRequest.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_REQUEST);

        when(userService.updateUser(user, "old-slug")).thenThrow(new EntityAlreadyExistsException("Test"));

        mockMvc.perform(put(USER_CONTROLLER_PATH + "/old-slug", "userSlug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserUtil.asJsonString(user))).andExpect(status().isConflict());

        verify(userService, times(1)).updateUser(user, "old-slug");
    }

    @Test
    public void updateInexistentUser() throws Exception {

        UserRequest user = Fixture.from(UserRequest.class)
                .gimme(VanHackTemplateLoader.USER_CREATE_REQUEST);

        when(userService.updateUser(user, "old-slug")).thenThrow(new EntityNotFoundException("Test"));

        mockMvc.perform(put(USER_CONTROLLER_PATH + "/old-slug", "userSlug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ParserUtil.asJsonString(user))).andExpect(status().isNotFound());

        verify(userService, times(1)).updateUser(user, "old-slug");
    }

    @Test
    public void deleteUser() throws Exception {

        doNothing().when(userService).deleteUser("user-slug");

        mockMvc.perform(delete(USER_CONTROLLER_PATH + "/user-slug", "userSlug")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser("user-slug");
    }

    @Test
    public void deleteUserWithInvalidUser() throws Exception {

        doThrow(new EntityNotFoundException("Test")).when(userService).deleteUser("user-slug");

        mockMvc.perform(delete(USER_CONTROLLER_PATH + "/user-slug", "userSlug")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).deleteUser("user-slug");
    }


}
