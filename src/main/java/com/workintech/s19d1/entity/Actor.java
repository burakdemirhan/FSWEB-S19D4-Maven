package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "actor", schema = "fsweb")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    @Column(name = "first_name")
    @NotNull
    @NotBlank
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @NotBlank
    private String lastName;
    @Column(name = "birthDate")
    @NotNull
    @NotBlank
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    @NotNull
    @NotBlank
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor", schema = "fsweb", inverseJoinColumns = @JoinColumn(name = "movie_id") , joinColumns = @JoinColumn(name = "actor_id"))
   private List<Movie> movies;

public void addMovie(Movie movie ){
    if(movies == null){
        movies = new ArrayList<>();
    }
    movies.add(movie);
}




}
