package example;

public class UserNameService {

    private final UserService userService;

    public UserNameService(UserService userService) {
        this.userService = userService;
    }

    public String getUserNameById(int userId) {
        User user = userService.getUserById(userId);

        return user.getName();
    }
}
