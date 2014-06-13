package example;

public interface UserService {
    User getUserById(int userId);
    User getUserByIdFromCache(int userId);
}
