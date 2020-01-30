package com.anita.doordashdemo.presenters;

import com.anita.doordashdemo.data.model.Restaurant;
import com.anita.doordashdemo.data.provider.DataAccessor;
import com.anita.doordashdemo.data.provider.DataAccessorProvider;
import com.anita.doordashdemo.home.DiscoverPresenter;
import com.anita.doordashdemo.home.DiscoverPresenterImpl;
import com.anita.doordashdemo.home.DiscoverView;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposables;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class DiscoverPresenterTest {

    @Mock
    private DiscoverView discoverFragment;
    @Mock
    private DataAccessorProvider dataAccessorProvider;

    private DiscoverPresenter presenter;

    private List<Restaurant> mockRestaurantList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkListFetch() {
        RestaurantsMockAccessor mockDataAccessor = new RestaurantsMockAccessor();
        when(dataAccessorProvider.getDataAccessor()).thenReturn(mockDataAccessor);
        presenter = new DiscoverPresenterImpl(discoverFragment, dataAccessorProvider);
        presenter.fetchItems();
        verify(discoverFragment, times(1)).showProgress();
        verify(discoverFragment, times(1)).onFetchedAllItems(mockRestaurantList);
        verify(discoverFragment, times(1)).hideProgress();
    }

    private class RestaurantsMockAccessor implements DataAccessor {
        private Random random = new Random();

        @Override
        public void getRestaurantList(@NotNull Observer<List<Restaurant>> callback) {
            callback.onSubscribe(Disposables.empty());
            mockRestaurantList = createListOfRestaurants(10);
            callback.onNext(mockRestaurantList);
        }

        private Restaurant createRestaurant() {
            int id = random.nextInt();
            Restaurant res = new Restaurant(id,"Mock Restaurant Obj with id: " + id, "This is a mock description for restaurant " + id, "30 mins");
            return res;
        }

        private List<Restaurant> createListOfRestaurants(int count) {
            List<Restaurant> restaurants = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                restaurants.add(createRestaurant());
            }
            return restaurants;
        }
    }

}
