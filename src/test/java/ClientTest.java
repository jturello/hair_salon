import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Client.all().size(), 0);
  }

  public void all_returnsCorrectNumberOfClientsAfterAdd() {
    Client firstClient = new Client("first", 1);
    Client secondClient = new Client("second", 3);
    Client thirdClient = new Client("third", 2);
    assertEquals(Client.all().size(), 3);
  }

  public void all_returnsCorrectNumberOfClientsAfterDelete() {
    Client firstClient = new Client("first", 1);
    Client secondClient = new Client("second", 3);
    Client thirdClient = new Client("third", 2);
    thirdClient.delete();
    assertEquals(Client.all().size(), 2);
  }

  @Test
  public void equals_returnsTrueIfTNamesAndStylistIdsAreTheSame() {
    Client firstClient = new Client("SameName", 1);
    Client secondClient = new Client("SameName", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client myClient = new Client("someName", 1);
    myClient.save();
    assert(Client.all().get(0).equals(myClient));
  }

  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("someName", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient, myClient);
  }

  @Test
  public void update_updatesClientObjectsNameAndStylist_id_true() {
    Client myClient = new Client("originalName", 1);
    myClient.save();
    String originalName = myClient.getName();
    int originalStylistId = myClient.getStylistId();
    myClient.update("newName", 2);
    assertEquals("newName", myClient.getName());
    assertEquals(2, myClient.getStylistId());
  }

  @Test
  public void update_updatesClientObjectsNameAndStylist_idOnDatabase_true() {
    Client myClient = new Client("someClient", 1);
    myClient.save();
    int originalObj_id = myClient.getId();
    myClient.update("newName", 2);
    assertEquals("newName", Client.all().get(0).getName());
    assertEquals(2, Client.all().get(0).getId());
    assertEquals(originalObj_id, Client.all().get(0).getId());
  }

  @Test
  public void stylist_getName_returnsStylistName() {
    Stylist myStylist = new Stylist("someStylist");
    myStylist.save();
    Client myClient = new Client("someClient", myStylist.getId());
    assertEquals("someClient", myClient.getName());
  }

}
