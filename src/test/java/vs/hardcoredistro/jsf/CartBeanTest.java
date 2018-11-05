package vs.hardcoredistro.jsf;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vs.hardcoredistro.entities.Album;
import vs.hardcoredistro.entities.Customer;
import vs.hardcoredistro.entities.OrderedAlbum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartBeanTest {

    @Mock
    CartBean cartBean;

    Album jomsviking = new Album("Jomsviking", "Amon Amarth", 2017, "Viking death metal", "Metal Blade", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("17.00"));
    Album firepower = new Album("Firepower", "Judas Priest", 2018, "Great comeback", "Capitol", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("19.00"));
    Album incorruptible = new Album("Incorruptible", "Iced Earth", 2017, "havent listened much", "Dont know", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("18.00"));
    Album lastStand = new Album("The Last Stand", "Sabaton", 2016, "awesoooommee", "Nuclear Blast", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("16.00"));
    Album prequelle = new Album("Prequelle", "Ghost", 2018, "heavy rock at its best", "metal blade", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("19.00"));

    @Before
    public void setupCartBean() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        this.cartBean.init();
        List<OrderedAlbum> orderedAlbums = new ArrayList<>();

        cartBean.add(jomsviking);
        cartBean.add(jomsviking);
        cartBean.add(incorruptible);
        cartBean.add(incorruptible);
    }

    @Test
    public void increasedQuantityTest() {
        OrderedAlbum jomsviking = cartBean.getOrderedAlbums().get(0);
        OrderedAlbum firepower = cartBean.getOrderedAlbums().get(1);
        OrderedAlbum lastStand = cartBean.getOrderedAlbums().get(2);

        Assert.assertEquals(jomsviking.getQuantity(),3);
        Assert.assertEquals(firepower.getQuantity(),2);
        Assert.assertEquals(lastStand.getQuantity(),1);

    }
}
