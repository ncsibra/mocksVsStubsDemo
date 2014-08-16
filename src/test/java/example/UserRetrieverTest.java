package example;

import org.easymock.EasyMock;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class UserRetrieverTest {

    @Test
    public void testShouldReturnUserForValidId_TightestCoupling() {
        // GIVEN
        int userId = 1;
        User expected = new User(userId);
        UserServiceFactory mockFactory = EasyMock.createMock(UserServiceFactory.class);
        UserService mockService = EasyMock.createMock(UserService.class);
        UserRetriever underTest = new UserRetriever(mockFactory);

        EasyMock.expect(mockFactory.getService()).andReturn(mockService);
        EasyMock.expect(mockService.getUserById(userId)).andReturn(expected);
        EasyMock.replay(mockFactory, mockService);

        // WHEN
        User actual = underTest.getUserById(userId);

        // THEN
        assertEquals(expected.getId(), actual.getId());
        EasyMock.verify(mockFactory, mockService);
    }

    @Test
    public void testShouldReturnUserForValidId_LooserCoupling() {
        // GIVEN
        int userId = 1;
        User expected = new User(userId);
        UserServiceFactory mockFactory = EasyMock.createMock(UserServiceFactory.class);
        UserService mockService = EasyMock.createMock(UserService.class);
        UserRetriever underTest = new UserRetriever(mockFactory);

        EasyMock.expect(mockFactory.getService()).andStubReturn(mockService);
        EasyMock.expect(mockService.getUserById(userId)).andStubReturn(expected);
        EasyMock.replay(mockFactory, mockService);

        // WHEN
        User actual = underTest.getUserById(userId);

        // THEN
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void testShouldReturnUserForValidId_LoosestCoupling() {
        // GIVEN
        int userId = 1;
        User expected = new User(userId);
        UserServiceFactory stubFactory = EasyMock.createNiceMock(UserServiceFactory.class);
        UserService stubService = EasyMock.createNiceMock(UserService.class);
        UserRetriever underTest = new UserRetriever(stubFactory);

        EasyMock.expect(stubFactory.getService()).andStubReturn(stubService);
        EasyMock.expect(stubService.getUserById(EasyMock.anyInt())).andStubReturn(expected);
        EasyMock.replay(stubFactory, stubService);

        // WHEN
        User actual = underTest.getUserById(userId);

        // THEN
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
     public void testShouldReturnUserForValidId_LoosestCouplingWithMockito() {
        // GIVEN
        int userId = 1;
        User expected = new User(userId);
        UserServiceFactory stubFactory = Mockito.mock(UserServiceFactory.class, Mockito.RETURNS_DEEP_STUBS);
        UserRetriever underTest = new UserRetriever(stubFactory);

        Mockito.when(stubFactory.getService().getUserById(Mockito.anyInt())).thenReturn(expected);

        // WHEN
        User actual = underTest.getUserById(userId);

        // THEN
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    public void testShouldCallUserServiceOnce_TightCoupling() {
        // GIVEN
        int userId = 1;
        User expected = new User(userId);
        UserServiceFactory stubFactory = EasyMock.createNiceMock(UserServiceFactory.class);
        UserService mockService = EasyMock.createMock(UserService.class);
        UserRetriever underTest = new UserRetriever(stubFactory);

        EasyMock.expect(stubFactory.getService()).andStubReturn(mockService);
        EasyMock.expect(mockService.getUserById(EasyMock.anyInt())).andReturn(expected);
        EasyMock.replay(stubFactory, mockService);

        // WHEN
        User actual = underTest.getUserById(userId);

        // THEN
        assertEquals(expected.getId(), actual.getId());
        EasyMock.verify(mockService);
    }

    @Test
    public void testShouldCallUserServiceOnce_LooseCoupling() {
        // GIVEN
        UserServiceFactory stubFactory = EasyMock.createNiceMock(UserServiceFactory.class);
        UserService mockService = EasyMock.createMock(UserService.class);
        UserRetriever underTest = new UserRetriever(stubFactory);

        EasyMock.expect(stubFactory.getService()).andStubReturn(mockService);
        EasyMock.expect(mockService.getUserById(EasyMock.anyInt())).andReturn(null).times(1);
        EasyMock.replay(stubFactory, mockService);

        // WHEN
        underTest.getUserById(1);

        // THEN
        EasyMock.verify(mockService);
    }

    @Test
    public void testShouldCallUserServiceOnce_LooseCouplingWithMockito() {
        // GIVEN
        UserServiceFactory stubFactory = Mockito.mock(UserServiceFactory.class);
        UserService mockService = Mockito.mock(UserService.class);
        UserRetriever underTest = new UserRetriever(stubFactory);

        Mockito.when(stubFactory.getService()).thenReturn(mockService);

        // WHEN
        underTest.getUserById(1);

        // THEN
        Mockito.verify(mockService, Mockito.times(1)).getUserById(Mockito.anyInt());
    }
}
