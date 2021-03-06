package vs.hardcoredistro.startup;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.omnifaces.cdi.Eager;

import vs.hardcoredistro.entities.Album;
import vs.hardcoredistro.entities.Customer;
import vs.hardcoredistro.entities.OrderedAlbum;
import vs.hardcoredistro.entities.Purchase;
import vs.hardcoredistro.entities.PurchaseStatus;
import vs.hardcoredistro.services.AlbumService;
import vs.hardcoredistro.services.CustomerService;
import vs.hardcoredistro.services.PurchaseService;
import vs.hardcoredistro.services.StockService;

@Eager
@Startup
@ApplicationScoped
public class InititalizerBean {

    @Inject
    private CustomerService customerService;

    @Inject
    private AlbumService albumService;

    @Inject
    private StockService stockService;

    @Inject
    private PurchaseService purchaseService;

    @PostConstruct
    public void init() {

        // Creating and persisting albums
        Album jomsviking = new Album("Jomsviking", "Amon Amarth", 2017, "Viking death metal", "Metal Blade", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("17.00"));
        Album firepower = new Album("Firepower", "Judas Priest", 2018, "Great comeback", "Capitol", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("19.00"));
        Album incorruptible = new Album("Incorruptible", "Iced Earth", 2017, "havent listened much", "Dont know", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("18.00"));
        Album lastStand = new Album("The Last Stand", "Sabaton", 2016, "awesoooommee", "Nuclear Blast", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("16.00"));
        Album prequelle = new Album("Prequelle", "Ghost", 2018, "heavy rock at its best", "metal blade", "http://www.lasertimepodcast.com/wp-content/uploads/2016/02/iron-maiden-eddie-wallpaper-.jpg", new BigDecimal("19.00"));

        albumService.create(jomsviking);
        albumService.create(firepower);
        albumService.create(incorruptible);
        albumService.create(lastStand);
        albumService.create(prequelle);

        // Creating and persisting customers
        Customer vasouv = new Customer("vasouv", "1234567", "vasouv", "Themistokleous", "Xanthi", "67133", "Greece");
        Customer john = new Customer("john", "987654", "john", "Kallithea", "Xanthi", "67100", "Greece");
        Customer chris = new Customer("chris", "22222", "chris", "Sardewn", "Xanthi", "67133", "Greece");

        //Users from file realm
        Customer user1 = new Customer("user1", "user1", "user1", "user1", "user1", "user1", "user1");
        Customer user2 = new Customer("user2", "user2", "user2", "user2", "user2", "user2", "user2");
        Customer user3 = new Customer("user3", "user3", "user3", "user3", "user3", "user3", "user3");

        customerService.create(vasouv);
        customerService.create(john);
        customerService.create(chris);

        customerService.create(user1);
        customerService.create(user2);
        customerService.create(user3);

        // Creating and persisting stock for albums
        stockService.create(jomsviking.getTitle(), 5);
        stockService.create(firepower.getTitle(), 7);
        stockService.create(incorruptible.getTitle(), 3);
        stockService.create(lastStand.getTitle(), 1);
        stockService.create(prequelle.getTitle(), 10);

        // Ordered albums for vasouv
        OrderedAlbum v1 = new OrderedAlbum(2, firepower);
        OrderedAlbum v2 = new OrderedAlbum(1, incorruptible);
        List<OrderedAlbum> forVasouv = new ArrayList<>();
        forVasouv.add(v1);
        forVasouv.add(v2);

        // Purchase for vasouv
        Purchase pVasouv = new Purchase(LocalDate.now(), vasouv, forVasouv, PurchaseStatus.PENDING);

        // Ordered albums for john
        OrderedAlbum j1 = new OrderedAlbum(2, firepower);
        OrderedAlbum j2 = new OrderedAlbum(3, jomsviking);
        OrderedAlbum j3 = new OrderedAlbum(5, incorruptible);
        List<OrderedAlbum> forJohn = new ArrayList<>();
        forJohn.add(j1);
        forJohn.add(j2);
        forJohn.add(j3);

        // Purchase for john
        Purchase pJohn = new Purchase(LocalDate.now(), john, forJohn, PurchaseStatus.PENDING);

        // Persists purchases
        purchaseService.create(pVasouv);
        purchaseService.create(pJohn);

        // New orderd albums for vasouv
        OrderedAlbum v3 = new OrderedAlbum(3, jomsviking);
        OrderedAlbum v4 = new OrderedAlbum(2, incorruptible);
        OrderedAlbum v5 = new OrderedAlbum(1, firepower);
        List<OrderedAlbum> forVasouvAgain = new ArrayList<>();
        forVasouvAgain.add(v3);
        forVasouvAgain.add(v4);
        forVasouvAgain.add(v5);

        // New purchase persistence
        Purchase newVasouv = new Purchase(LocalDate.now(), vasouv, forVasouvAgain, PurchaseStatus.PENDING);
        newVasouv.setPurchaseStatus(PurchaseStatus.SHIPPED);
        newVasouv.setDateShipped(LocalDate.now());

        purchaseService.create(newVasouv);

    }

}
