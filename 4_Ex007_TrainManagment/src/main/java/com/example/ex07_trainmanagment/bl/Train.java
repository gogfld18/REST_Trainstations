package com.example.ex07_trainmanagment.bl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Train {
    private int id;
    private ArrayList<String> stations;
    private String type;
}
