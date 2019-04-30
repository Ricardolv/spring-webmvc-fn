package com.richard.webmvcfn.config.handler;

import com.richard.webmvcfn.model.Person;
import com.richard.webmvcfn.services.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.net.URI;

import static org.springframework.web.servlet.function.ServerResponse.ok;

@Component
public class PersonHandler {

    private final PersonService personService;

    public PersonHandler(PersonService personService) {
        this.personService = personService;
    }

    public ServerResponse handleGetAllPeople(ServerRequest r) {
        return ok().body(personService.all());
    }

    public ServerResponse handleGetPersonById(ServerRequest r) {
        return ok().body(personService.byId(Long.parseLong(r.pathVariable("id"))));
    }

    public ServerResponse handlePostSavePerson(ServerRequest r) throws ServletException, IOException {
        var result = personService.save(new Person(null, r.body(Person.class).getName()));
        var uri = URI.create("/people/" + result.getId());
        return ServerResponse.created(uri).body(result);
    }
}
