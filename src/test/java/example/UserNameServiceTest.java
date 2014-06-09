package example;

import org.easymock.EasyMock;
import org.junit.Test;
import org.mockito.Mockito;

public class UserNameServiceTest {

    @Test
    public void testGetUserNameByIdWithEasyMock() {
        // GIVEN
        UserService stubService = EasyMock.createNiceMock(UserService.class);
        User mockUser = EasyMock.createMock(User.class);
        UserNameService underTest = new UserNameService(stubService);

        EasyMock.expect(stubService.getUserById(EasyMock.anyInt())).andStubReturn(mockUser);
        EasyMock.expect(mockUser.getName()).andReturn("");
        EasyMock.replay(stubService, mockUser);

        // WHEN
        underTest.getUserNameById(1);

        // THEN
        EasyMock.verify(mockUser);
    }

    @Test
    public void testGetUserNameByIdWithMockito() {
        // GIVEN
        UserService stubService = Mockito.mock(UserService.class);
        User mockUser = Mockito.mock(User.class);
        UserNameService underTest = new UserNameService(stubService);

        Mockito.when(stubService.getUserById(Mockito.anyInt())).thenReturn(mockUser);

        // WHEN
        underTest.getUserNameById(1);

        // THEN
        Mockito.verify(mockUser).getName();
    }
}
