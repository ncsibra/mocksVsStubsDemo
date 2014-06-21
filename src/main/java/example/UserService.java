package example;

public interface UserService {
    User getUserById(int userId);
    void useCache(boolean flag);
}
