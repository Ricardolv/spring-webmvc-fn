package com.richard.webmvcfn.config;

import com.richard.webmvcfn.config.handler.PersonHandler;
import com.richard.webmvcfn.services.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.*;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@Log4j2
@Component
public class RouterFunctionConfig {

    @Bean
    RouterFunction<ServerResponse> routes(PersonService ps, PersonHandler ph) {
        var root = "";
        return  route()
            .GET( root  + "/people", ph::handleGetAllPeople)
            .GET( root  + "/people/{id}", ph::handleGetPersonById)
            .POST(root  + "/people", ph::handlePostSavePerson)
            .filter((serverRequest, handlerFunction) -> {
                log.info("entering HandlerFilterFunction");
                try {
                    return handlerFunction.handle(serverRequest);
                } finally {
                    log.info("exiting HandlerFilterFunction");
                }
            })
            .build();
    }
}
