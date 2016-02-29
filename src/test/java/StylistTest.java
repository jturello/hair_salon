import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAfterAllDeleted() {
    List<Stylist> stylists = Stylist.all();
    for(Stylist stylist : stylists){
      stylist.delete();
    }
      assertEquals(Stylist.all().size(), 0);
  }

  public void all_returnsCorrectNumberOfStylistsAfterAdd() {
    Stylist firstStylist = new Stylist("Sieglinde");
    Stylist secondStylist = new Stylist("Brunhilde");
    Stylist thirdStylist = new Stylist("Wotan");
    assertEquals(Stylist.all().size(), 4);
  }

  public void all_returnsCorrectNumberOfStylistsAfterDelete() {
    Stylist firstStylist = new Stylist("first");
    Stylist secondStylist = new Stylist("second");
    Stylist thirdStylist = new Stylist("third");
    thirdStylist.delete();
    assertEquals(Stylist.all().size(), 3);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Stylist firstStylist = new Stylist("Johnny");
    Stylist secondStylist = new Stylist("Johnny");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist newStylist = new Stylist("Gianni Schicchi");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(savedStylist.equals(newStylist));
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist newStylist = new Stylist("Uhura");
    newStylist.save();
    assertTrue(Stylist.find(newStylist.getId()).equals(newStylist));
  }

  @Test
  public void update_updatesStylistObjectsName() {
    Stylist myStylist = new Stylist("originalName");
    int original_id = myStylist.getId();
    myStylist.update("newName");
    assertEquals("newName", myStylist.getName());
    assertEquals(original_id, myStylist.getId());
  }

  @Test
  public void update_updatesStylistObjectsNameOnDatabase() {
    Stylist myStylist = new Stylist("originalName");
    myStylist.save();
    int originalObj_id = myStylist.getId();
    int originalDB_id = Stylist.all().get(Stylist.all().size() - 1).getId();
    myStylist.update("newName");
    assertEquals("newName", Stylist.find(originalDB_id).getName());
  }

}
