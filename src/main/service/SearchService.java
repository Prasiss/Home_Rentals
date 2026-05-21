package com.HomeRental.service;

import java.util.List;
import com.HomeRental.dao.HomeRentalDAO;
import com.HomeRental.model.HomeModel;

public class SearchService {

    HomeRentalDAO dao = new HomeRentalDAO();

    /**
     * Searches properties based on keyword.
     *
     * @param keyword Search keyword (location, title, etc.)
     * @return List of matching HomeModel objects
     * @throws Exception if DAO operation fails
     */
    public List<HomeModel> searchProperty(String keyword) throws Exception {
        return dao.searchProperty(keyword);
    }
}