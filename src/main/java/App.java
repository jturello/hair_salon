import java.util.*;

import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Restaurant> restaurants = Restaurant.all();

      if (restaurants == null) {
        restaurants = new ArrayList<Restaurant>();
        request.session().attribute("restaurants", restaurants);
      }

      model.put("restaurants", restaurants);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/restaurants/new", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Cuisine> cuisines = Cuisine.all();

      if (cuisines == null) {
        cuisines = new ArrayList<Cuisine>();
        request.session().attribute("cuisines", cuisines);
      }

      model.put("cuisines", cuisines);
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String restaurantName = request.queryParams("name");
      int cuisineId = Integer.parseInt(request.queryParams("cuisineId"));
      String description = request.queryParams("description");
      List<Restaurant> restaurants = Restaurant.all();

      Restaurant newRestaurant;
      if (description.length() < 1) {
        newRestaurant = new Restaurant(restaurantName, cuisineId);
      } else {
          newRestaurant = new Restaurant(restaurantName, cuisineId, description);
      }

      newRestaurant.save();
      restaurants = Restaurant.all();
      request.session().attribute("restaurants", restaurants);

      model.put("restaurants", restaurants);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/restaurants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      String description = "";
      if(restaurant.getDescription() != null) {
         description = restaurant.getDescription();
      }

      model.put("description", description);
      model.put("restaurant", restaurant);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/restaurants/:id/description", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      // Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.queryParams("restaurantId")));
      String newDescription = request.queryParams("description");
      restaurant.updateDescription(newDescription);
      List<Restaurant> restaurants = Restaurant.all();
      request.session().attribute("restaurants", restaurants);

      model.put("restaurant", restaurant);
      model.put("description", newDescription);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/restaurants/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.queryParams("deleteId")));

      restaurant.delete();
      List<Restaurant> restaurants = Restaurant.all();
      request.session().attribute("restaurants", restaurants);

      model.put("restaurants", restaurants);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String cuisineType = request.queryParams("cuisineType");
      Cuisine newCuisine = new Cuisine(cuisineType);

      newCuisine.save();
      List<Cuisine> cuisines = Cuisine.all();
      request.session().attribute("cuisines", cuisines);

      model.put("cuisines", cuisines);
      model.put("template", "templates/restaurants-cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /******************************************************
    STUDENTS:
    TODO: Create page to display information about the selected restaurant
    TODO: Create page to display restaurants by cuisine type
    *******************************************************/

  get("/cuisines/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));

    List<Restaurant> restaurants = Restaurant.findByCuisineId(Integer.parseInt(request.params(":id")));

    model.put("cuisine", cuisine);
    model.put("restaurants", restaurants);
    model.put("template", "templates/cuisine.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  } // END OF MAIN
} // END OF APP CLASS
