package yarik.mockito.services;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import yarik.mockito.entities.User;
import yarik.mockito.repositories.UserRepository;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    private AuthService authService;

    public AuthServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.authService = new AuthService(this.userRepository);
    }

    @Test
    public void isAuthenticatedShouldSuccess() {
        String email = "test@gmail.com";
        String pass = "pass";

        given(this.userRepository.findByEmail(email)).willReturn(
            new User(email, pass, true)
        );

        Boolean isAuth = this.authService.isAuthenticated(email, pass);

        assertThat(isAuth).isTrue();
    }

    @Test
    public void isAuthenticatedShouldNotFound() {
        String email = "test@gmail.com";

        given(this.userRepository.findByEmail(email)).willReturn(null);

        Boolean isAuth = this.authService.isAuthenticated(email, "pas1");

        assertThat(isAuth).isFalse();
    }

    @Test
    public void isAuthenticatedShouldWrongPassword() {
        String email = "test@gmail.com";

        given(this.userRepository.findByEmail(email)).willReturn(
                new User(email, "pass", true)
        );

        Boolean isAuth = this.authService.isAuthenticated(email, "pas1");

        assertThat(isAuth).isFalse();
    }

    @Test
    public void isAuthenticatedShouldNotActive() {
        String email = "test@gmail.com";
        String pass = "pass";

        given(this.userRepository.findByEmail(email)).willReturn(
            new User(email, pass, false)
        );

        Boolean isAuth = this.authService.isAuthenticated(email, pass);

        assertThat(isAuth).isFalse();
    }
}