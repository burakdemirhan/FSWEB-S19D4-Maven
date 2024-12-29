package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.repository.ActorRepository;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.utill.Convertor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actor")
@AllArgsConstructor
@Validated
public class ActorController {
    private ActorService actorService;
    @GetMapping
    public List<ActorResponse> findAll(){
    List<Actor> actors  = actorService.findAll();
    return Convertor.actorResponseConvertor(actors);
    }
    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable("id") Long id){
      Actor actor = actorService.findById(id);
      return Convertor.actorResponseConvertor(actor);
    }
    @PostMapping
    public ActorResponse save(@RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
       List<Movie> movies = actorRequest.getMovies();
       for(Movie movie : movies){
           actor.addMovie(movie);
       }
        Actor savedActor = actorService.save(actor);
       return Convertor.actorCreateConvertor(savedActor);
    }
    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable Long id, @RequestBody Actor actor){
      Actor foundActor = actorService.findById(id);
     actor.setMovies(foundActor.getMovies());
     actor.setId(id);
     actorService.save(actor);
     return Convertor.actorResponseConvertor(actor);
    }
    @DeleteMapping("/{id}")
   public ActorResponse delete(@PathVariable Long id){
        Actor removed = actorService.findById(id);
        actorService.delete(removed.getId());
        return Convertor.actorResponseConvertor(removed);
    }



}
