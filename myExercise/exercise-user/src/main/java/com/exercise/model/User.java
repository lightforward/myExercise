package com.exercise.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private  Long id;

    private  String userName;

    private String passWord;

    private String registeredTime;


}
