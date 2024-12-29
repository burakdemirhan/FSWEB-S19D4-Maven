package com.workintech.s19d1.utill;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class Convertor {
    public static List<ActorResponse> actorResponseConvertor(List<Actor> actors) {
       List<ActorResponse> actorResponse = new ArrayList<>();
       for(Actor actor : actors){
           actorResponse.add( new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), actor.getMovies()));
       }
       return actorResponse;
    }

    public static ActorResponse actorResponseConvertor(Actor actor) {
        ActorResponse actorResponse = new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), actor.getMovies());
        return actorResponse;
    }

    public static ActorResponse actorCreateConvertor(Actor savedActor) {
        ActorResponse actorResponse = new ActorResponse(savedActor.getId(), savedActor.getFirstName(), savedActor.getLastName(), savedActor.getBirthDate(),savedActor.getMovies());
        return actorResponse;
    }
}
