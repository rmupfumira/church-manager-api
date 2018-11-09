package za.org.rfm.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import za.org.rfm.dao.UserRepository;
import za.org.rfm.entity.User;
import za.org.rfm.service.impl.AuthServiceImpl;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl authService;


    @Before
    public void setUp() throws Exception {
       // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin(){
        User user = createRandomUser();
        when(userRepository.getUserByUsername("test")).thenReturn(user);
        User dbUser = authService.doLogin(user);
        Assert.assertEquals(user,dbUser);
    }

    private User createRandomUser() {

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        return user;
    }
}
