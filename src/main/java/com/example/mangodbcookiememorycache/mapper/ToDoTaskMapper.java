package com.example.mangodbcookiememorycache.mapper;

import com.example.mangodbcookiememorycache.domain.data.ToDoTaskData;
import com.example.mangodbcookiememorycache.domain.dto.ToDoTaskDTO;
import com.example.mangodbcookiememorycache.domain.entity.ToDoTask;
import com.example.mangodbcookiememorycache.repository.ToDoRepository;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class ToDoTaskMapper {
    public ToDoTask dataToEntity(ToDoTaskData data){
        return ToDoTask
                .builder()
                .id(UUID.randomUUID()
                        .toString())
                .text(data.getText())
                .creationDateTime(LocalDateTime.now())
                .build();
    }
    public ToDoTaskDTO entityToDTO(ToDoTask data){
        return ToDoTaskDTO
                .builder()
                .text(data.getText())
                .id(data.getId())
                .creationDateTime(data.getCreationDateTime())
                .build();
    }
}
