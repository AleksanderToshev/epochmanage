package com.alex.epochmanage.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "ToDo")
@AllArgsConstructor
//Everything from the ToDo video

public class ToDo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer id;

    @Column(name = "TITLE")
    @NonNull
    private String title;

    @Column(name = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private Date date;
    @NonNull
    @Column(name = "STATUS")
    private String status;

    public ToDo() {
    }
}
