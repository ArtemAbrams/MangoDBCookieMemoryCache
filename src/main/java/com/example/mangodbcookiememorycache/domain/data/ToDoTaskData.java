package com.example.mangodbcookiememorycache.domain.data;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoTaskData implements Serializable {
    private String text;
}
