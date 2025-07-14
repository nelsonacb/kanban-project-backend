package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "The title must be between {min} and {max} characters.")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private KanbanColumn column;

    public enum KanbanColumn {
        @JsonProperty("backlog") BACKLOG,
        @JsonProperty("todo") TODO,
        @JsonProperty("in progress") IN_PROGRESS,
        @JsonProperty("doing") DOING,
        @JsonProperty("complete") COMPLETE,
        @JsonProperty("done") DONE
    }
}