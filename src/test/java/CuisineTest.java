import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  public void all_returnsCorrectNumberOfCuisinesAfterAdd() {
    Cuisine firstCuisine = new Cuisine("first");
    Cuisine secondCuisine = new Cuisine("second");
    Cuisine thirdCuisine = new Cuisine("third");
    assertEquals(Cuisine.all().size(), 3);
  }

  public void all_returnsCorrectNumberOfCuisinesAfterDelete() {
    Cuisine firstCuisine = new Cuisine("first");
    Cuisine secondCuisine = new Cuisine("second");
    Cuisine thirdCuisine = new Cuisine("third");
    thirdCuisine.delete();
    assertEquals(Cuisine.all().size(), 2);
  }

  @Test
  public void equals_returnsTrueIfTypesAreTheSame() {
    Cuisine firstCuisine = new Cuisine("American");
    Cuisine secondCuisine = new Cuisine("American");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Cuisine newCuisine = new Cuisine("Nepalese");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
    assertTrue(savedCuisine.equals(newCuisine));
  }

  @Test
  public void find_findsCuisineInDatabase_true() {
    Cuisine newCuisine = new Cuisine("Nepali");
    newCuisine.save();
    assertTrue(newCuisine.all().get(0).equals(newCuisine));
  }

  @Test
  public void update_updatesCuisineObjectsType() {
    Cuisine myCuisine = new Cuisine("originalType");
    int original_id = myCuisine.getId();
    myCuisine.update("newType");
    assertEquals("newType", myCuisine.getType());
    assertEquals(original_id, myCuisine.getId());
  }

  @Test
  public void update_updatesCuisineObjectsTypeOnDatabase() {
    Cuisine myCuisine = new Cuisine("originalType");
    myCuisine.save();
      int originalObj_id = myCuisine.getId();
    int originalDB_id = Cuisine.all().get(0).getId();
    myCuisine.update("newType");
    assertEquals("newType", Cuisine.all().get(0).getType());
    assertEquals(originalObj_id, originalDB_id);
    assertEquals(myCuisine.getId(), Cuisine.all().get(0).getId());
    assertEquals(originalObj_id, Cuisine.all().get(0).getId());
  }

}
