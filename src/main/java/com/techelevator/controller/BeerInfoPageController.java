package com.techelevator.controller;

import com.techelevator.model.dao.BeerDAO;
import com.techelevator.model.dao.BeerReviewDAO;
import com.techelevator.model.dto.Beer;
import com.techelevator.model.dto.BeerReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BeerInfoPageController {

    private BeerDAO beerDAO;
    private BeerReviewDAO beerReviewDAO;

    @Autowired
    public BeerInfoPageController(BeerDAO beerDAO, BeerReviewDAO beerReviewDAO) {
        this.beerDAO = beerDAO;
        this.beerReviewDAO = beerReviewDAO;
    }

    @RequestMapping("/beer/{beerId}")
    public String getBeerInfoPage(@PathVariable("beerId") int id, HttpServletRequest request) {

        Beer beer = beerDAO.getBeerById(id);
        request.setAttribute("beer", beer);

        List<BeerReview> reviewList = beerReviewDAO.getReviewsByBeerId(id);
        request.setAttribute("reviews", reviewList);

        return "beers/beerInfoPage";
    }

    @RequestMapping("/beer/{beerId}/reviews/new")
    public String displayNewBreweryForm(ModelMap modelHolder) {

        return "beers/reviews/newReview";
    }

//    @RequestMapping(path="/beer/{beerId}/reviews", method=RequestMethod.POST)
//    public String createReview(@PathVariable("beerId") int id,
//                               @Valid @ModelAttribute Beer beer,
//                               BindingResult result,
//                               RedirectAttributes flash) {
//
//    }

}
