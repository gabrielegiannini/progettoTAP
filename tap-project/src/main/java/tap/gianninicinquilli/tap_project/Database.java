package tap.gianninicinquilli.tap_project;

import java.util.List;

public interface Database {

	List<Dish> getMenuOf(Restaurant restaurant);

	List<Restaurant> getRestaurantsList();

	int addReview(UserProfile userProfile, Restaurant rest, String review);

	void updateReview(int reviewId, String newReview);

	List<String> getReviews(UserProfile userProfile);

	void addDishToMenu(Restaurant restaurant, Dish dish);

	void updatePriceToFood(Restaurant restaurant, Dish dish1, double price);

	void updateCategoryToFood(Restaurant restaurant, Dish dish1, String category);

	List<String> getReviews(Restaurant restaurant);

	List<Integer> getAgeBuyersMonth(RestaurateurController resControl);

	List<Double> getPricesOfOrdersMonth(RestaurateurController resControl);

	List<Dish> getFoodSoldMonth(RestaurateurController resControl);

}
