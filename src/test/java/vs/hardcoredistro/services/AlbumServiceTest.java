package vs.hardcoredistro.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vs.hardcoredistro.entities.Album;
import vs.hardcoredistro.entities.Customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

public class AlbumServiceTest {

    Album lastStand = new Album("The Last Stand", "Sabaton", 2016, "SPARTAAAA", "Nuclear Blast", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("16.00"));

    @Mock
    private AlbumService albumService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void albumIsFound() {
        when(albumService.findByTitle("The Last Stand")).thenReturn(lastStand);

        assertEquals(lastStand.getTitle(), albumService.findByTitle("The Last Stand").getTitle());
        assertEquals(lastStand.getId(), albumService.findByTitle("The Last Stand").getId());
    }

    @Test
    public void customerIsNotFound() {

        when(albumService.findByTitle("The Last Stand")).thenReturn(null);

        assertEquals(null, albumService.findByTitle("The Last Stand"));

    }

    @Test
    public void customerIsPersisted() {

        Album hardwired = new Album("Hardwired... to Self-Destruct", "Metallica", 2016, "top", "Blackened", "https://en.wikipedia.org/wiki/Hardwired..._to_Self-Destruct#/media/File:Metallica_Hardwired..._To_Self-Destruct_2016.jpeg", new BigDecimal("18.00"));

        doNothing().when(albumService).create(any());

        albumService.create(hardwired);

        verify(albumService).create(hardwired);

    }

}
