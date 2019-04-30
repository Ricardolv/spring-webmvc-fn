package com.richard.webmvcfn.services;

import com.richard.webmvcfn.model.Person;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    private final Set<Person> people = new HashSet<>(Set.of(Person.builder() .id(counter.incrementAndGet()).name("Richard").build(),
                                              Person.builder() .id(counter.incrementAndGet()).name("Liliane").build(),
                                              Person.builder() .id(counter.incrementAndGet()).name("Bernardo").build(),
                                              Person.builder() .id(counter.incrementAndGet()).name("Maressa").build()));

    public Set<Person> all() {
        return this.people;
    }

    public Person byId(Long id) {
        return this.people.stream()
                          .filter(p -> p.getId().equals(id))
                          .findFirst()
                          .orElseThrow(() -> new IllegalArgumentException("no " + Person.class.getName() + " with that ID found!"));
    }

    public Person save(Person p) {
        var person = Person.builder()
                           .id(counter.incrementAndGet())
                           .name(p.getName())
                           .build();
        this.people.add(person);
        return person;
    }

}
