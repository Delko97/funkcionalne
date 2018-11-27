package vs.hardcoredistro.jsf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import vs.hardcoredistro.entities.Album;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

public class AlbumDetailsBeanTest {

    Album hardwired = new Album("Hardwired... to Self-Destruct", "Metallica", 2016, "top", "Blackened", "https://en.wikipedia.org/wiki/Hardwired..._to_Self-Destruct#/media/File:Metallica_Hardwired..._To_Self-Destruct_2016.jpeg", new BigDecimal("18.00"));
    Album wishmaster = new Album("Wishmaster", "Nightwish", 2000, "best nightwish album", "Spinefarm", "https://en.wikipedia.org/wiki/Wishmaster_(album)#/media/File:Nightwish_Wishmaster.jpg", new BigDecimal("12.00"));

    @Mock
    AlbumDetailsBean albumDetailsBean;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void albumDetailsAreReturned() {

        when(albumDetailsBean.getAlbumDetails()).thenReturn(hardwired);

        Assert.assertEquals(hardwired,albumDetailsBean.getAlbumDetails());

    }

    @Test
    public void albumDetailsAreSet() {
        Mockito.doCallRealMethod().when(albumDetailsBean).setAlbumDetails(wishmaster);
        Mockito.doCallRealMethod().when(albumDetailsBean).getAlbumDetails();

        albumDetailsBean.setAlbumDetails(wishmaster);
        Assert.assertEquals(wishmaster,albumDetailsBean.getAlbumDetails());

    }
}
