import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  public void all_returnsCorrectNumberOfRestaurantsAfterAdd() {
    Restaurant firstRestaurant = new Restaurant("first", 1);
    Restaurant secondRestaurant = new Restaurant("second", 3);
    Restaurant thirdRestaurant = new Restaurant("third", 2);
    assertEquals(Restaurant.all().size(), 3);
  }

  public void all_returnsCorrectNumberOfRestaurantsAfterDelete() {
    Restaurant firstRestaurant = new Restaurant("first", 1);
    Restaurant secondRestaurant = new Restaurant("second", 3);
    Restaurant thirdRestaurant = new Restaurant("third", 2);
    thirdRestaurant.delete();
    assertEquals(Restaurant.all().size(), 2);
  }

  @Test
  public void equals_returnsTrueIfTNamesAndCuisineIdsAreTheSame() {
    Restaurant firstRestaurant = new Restaurant("SameName", 1);
    Restaurant secondRestaurant = new Restaurant("SameName", 1);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Restaurant myRestaurant = new Restaurant("someName", 1);
    myRestaurant.save();
    assert(Restaurant.all().get(0).equals(myRestaurant));
  }

  public void find_findsRestaurantInDatabase_true() {
    Restaurant myRestaurant = new Restaurant("someName", 1);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant, myRestaurant);
  }

  @Test
  public void update_updatesRestaurantObjectsNameAndCuisine_id_true() {
    Restaurant myRestaurant = new Restaurant("originalName", 1);
    myRestaurant.save();
    String originalName = myRestaurant.getName();
    int originalCuisineId = myRestaurant.getCuisineId();
    myRestaurant.update("newName", 2);
    assertEquals("newName", myRestaurant.getName());
    assertEquals(2, myRestaurant.getCuisineId());
  }

  @Test
  public void update_updatesRestaurantObjectsNameAndCuisine_idOnDatabase_true() {
    Restaurant myRestaurant = new Restaurant("originalName", 1);
    myRestaurant.save();
    int originalObj_id = myRestaurant.getId();
    myRestaurant.update("newName", 2);
    assertEquals("newName", Restaurant.all().get(0).getName());
    assertEquals(2, Restaurant.all().get(0).getCuisineId());
    assertEquals(originalObj_id, Restaurant.all().get(0).getId());
  }

  @Test
  public void getCuisineType_returnsCuisineType() {
    Cuisine myCuisine = new Cuisine("myCuisineType");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("originalName", myCuisine.getId());
    assertEquals("myCuisineType", myRestaurant.getCuisineType());
  }

  @Test
  public void restaurant_canInstantiateWithDescription() {
    Restaurant myRestaurant = new Restaurant("newRestaurant", 1, "restaurant description");
    assertEquals("restaurant description", myRestaurant.getDescription());
  }

  @Test
  public void updateDescription_setsDescription() {
    Restaurant myRestaurant = new Restaurant("newRestaurant", 1);
    myRestaurant.updateDescription("added description");
    assertEquals("added description", myRestaurant.getDescription());
  }

}
