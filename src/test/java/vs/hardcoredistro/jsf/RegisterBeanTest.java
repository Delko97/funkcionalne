package vs.hardcoredistro.jsf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vs.hardcoredistro.services.CustomerService;

public class RegisterBeanTest {

    @Mock
    RegisterBean registerBean;

    @InjectMocks
    CustomerService customerService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkIfClearedData() {
        Mockito.doCallRealMethod().when(registerBean).setAddress("address");
        Mockito.doCallRealMethod().when(registerBean).register();
        registerBean.setAddress("address");
        registerBean.register();

    }


}
