package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
@NoArgsConstructor
public class MovieController {
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll(){
       return  movieService.findAll();
    }
    @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id){
      return  movieService.findById(id);
    }
    @PostMapping
    public Movie save(@PathVariable MovieRequest movieRequest){
        Movie movie = movieRequest.getMovie();
        List<Actor> actors = movieRequest.getActors();
        for(Actor actor : actors){
            movie.addActor(actor);
        }
        Movie savedMovie = movieService.save(movie);
        return movie;

    }
    @PutMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie movie){
        Movie foundMovie = findById(id);
        movie.setActors(foundMovie.getActors());
        movie.setId(id);
        movieService.save(movie);
        return movie;
    }
    @DeleteMapping("/{id}")
    public Movie delete(@PathVariable Long id){
        Movie removed = movieService.findById(id);
        movieService.delete(removed);
        return removed;
    }

}
