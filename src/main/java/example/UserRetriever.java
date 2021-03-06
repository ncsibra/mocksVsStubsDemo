package example;

public class UserRetriever {

    private final UserServiceFactory factory;

    public UserRetriever(UserServiceFactory factory) {
        this.factory = factory;
    }

    public User getUserById(int userId) {
        UserService service = factory.getService();

        User user = service.getUserById(userId);
        return user;
    }
}
