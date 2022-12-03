package com.example.demo.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(max = 128, message = "The field author cannot be longer than 128 letters")
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(max = 1024, message = "The field author cannot be longer than 1024 letters")
    private String instruction;

    @JoinColumn(name = "cook_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Cook cook;

    public Recipe(){

    }


    public Recipe(String name, String instruction) {
        this.name = name;
        this.instruction = instruction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && Objects.equals(name, recipe.name) && Objects.equals(instruction, recipe.instruction) && Objects.equals(cook, recipe.cook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instruction, cook);
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instruction='" + instruction + '\'' +
                ", cook=" + cook +
                '}';
    }
}
