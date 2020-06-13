package yarik.mockito.repositories;

import yarik.mockito.entities.User;

public class UserRepository {
    public User findByEmail(String email) {
        // TODO implement connection to DB, this is test repository
        return Math.random() < 0.5 ? null : new User(email, "test_pass");
    }
}
