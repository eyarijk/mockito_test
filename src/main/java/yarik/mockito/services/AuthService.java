package yarik.mockito.services;

import yarik.mockito.entities.User;
import yarik.mockito.repositories.UserRepository;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean isAuthenticated(String email, String password) {
        User user = this.userRepository.findByEmail(email);

        try {
            if (user == null) {
                throw new RuntimeException(String.format("Not found user by email %s", email));
            }

            // :TODO implement hashing
            if (!user.getPassword().equals(password)) {
                throw new RuntimeException("Wrong password");
            }

            if (!user.getActive()) {
                throw new RuntimeException("User not active");
            }
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
