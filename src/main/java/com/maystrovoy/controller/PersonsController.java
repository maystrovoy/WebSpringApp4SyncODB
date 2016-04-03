package com.maystrovoy.controller;

import com.maystrovoy.model.Person;
import com.maystrovoy.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonsController {

    @Inject
    private PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    private ModelAndView showActiveQueue(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("persons");
        mav.addObject("allPersonsList", personService.getAllPersonsData());
        return mav;
    }

    @RequestMapping(value = "persons", method = RequestMethod.POST)
    public String updatePersonsRightForm(HttpServletRequest request, Person editedPerson) {
//        String edited = request.getParameter("asd");
//        int editedRight = (Integer.parseInt(edited));
//        editedPerson.setRights(editedRight);
        HttpSession httpSession = request.getSession();
        Person person = (Person) httpSession.getAttribute("person");
        String login = person.getLoginName();
        personService.updatePersonsRights(editedPerson);
        return "redirect:/persons";
    }
}
