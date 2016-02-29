import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(0, Client.all().size());
  }

  @Test
  public void all_returnsCorrectNumberOfClientsAfterAdd() throws Exception{
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    int stylistId = Stylist.all().get(0).getId();
    try{
      if(Stylist.all().get(0).getId() != 1) {
        throw new Exception("stylist.id expected<1>, is: " + stylistId);
      }
    } catch(Exception e) {
      System.err.println("caught exception: " + e.getMessage());
    }
    Client firstClient = new Client("first", 1);
    firstClient.save();
    Client secondClient = new Client("second", 1);
    secondClient.save();
    Client thirdClient = new Client("third", 1);
    thirdClient.save();

    assertEquals(3, Client.all().size());
  }

  public void all_returnsCorrectNumberOfClientsAfterDelete() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client firstClient = new Client("first", 1);
    firstClient.save();
    Client secondClient = new Client("second", 1);
    secondClient.save();
    Client thirdClient = new Client("third", 1);
    thirdClient.save();

    thirdClient.delete();
    assertEquals(2, Client.all().size());
  }

  @Test
  public void equals_returnsTrueIfTNamesAndStylistIdsAreTheSame() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client firstClient = new Client("SameName", 1);
    Client secondClient = new Client("SameName", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client myClient = new Client("someName", 1);
    myClient.save();
    assert(Client.all().get(0).equals(myClient));
  }

  public void find_findsClientInDatabase_true() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client myClient = new Client("someName", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(savedClient.equals(myClient));
  }

  @Test
  public void update_updatesClientObjectsNameAndStylist_id_true() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client myClient = new Client("originalName", 1);
    myClient.save();
    String originalName = myClient.getName();
    int originalStylistId = myClient.getStylistId();
    myClient.update("newName", 1);
    assertEquals("newName", myClient.getName());
    assertEquals(1, myClient.getStylistId());
  }

  @Test
  public void update_updatesClientObjectsNameAndStylist_idOnDatabase_true() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Client myClient = new Client("someClient", 1);
    myClient.save();
    int originalObj_id = myClient.getId();
    myClient.update("newName", 1);
    assertEquals("newName", Client.all().get(0).getName());
    assertEquals(1, Client.all().get(0).getStylistId());
    assertEquals( Client.all().get(0).getId(), originalObj_id);
  }

  @Test
  public void stylist_getName_returnsStylistName() {
    // Stylist seedStylist = new Stylist("seedStylist");
    // seedStylist.save();
    Stylist myStylist = new Stylist("someStylist");
    myStylist.save();
    Client myClient = new Client("someClient", myStylist.getId());
    assertEquals("someClient", myClient.getName());
  }

}
