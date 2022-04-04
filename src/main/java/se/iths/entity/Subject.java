package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;


    @ManyToMany(mappedBy = "subjects",  cascade = CascadeType.PERSIST)
    private List<Teacher> teachers;
}
