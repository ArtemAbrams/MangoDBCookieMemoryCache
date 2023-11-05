package com.example.mangodbcookiememorycache.controller.MvcController;

import com.example.mangodbcookiememorycache.domain.data.ToDoTaskData;
import com.example.mangodbcookiememorycache.mapper.ToDoTaskMapper;
import com.example.mangodbcookiememorycache.repository.ToDoRepository;
import com.example.mangodbcookiememorycache.service.impl.ToDoListCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoListCacheService toDoListCacheService;
    private final ToDoRepository toDoRepository;
    private final static String CACHE_NAME = "to_do_list";
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ToDoTaskData toDoTask){
        try {
          var task = ToDoTaskMapper.dataToEntity(toDoTask);
          toDoRepository.save(task);
          var taskDTO = ToDoTaskMapper.entityToDTO(task);
          toDoListCacheService.createCache(CACHE_NAME,
                  taskDTO,
                  taskDTO.getId());
          return ResponseEntity.status(HttpStatus.OK)
                  .body(taskDTO);
        }
        catch (Exception exception){
          log.error(exception.getMessage());
          return ResponseEntity.status(HttpStatus
                          .INTERNAL_SERVER_ERROR)
                  .body(exception.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            toDoListCacheService.deleteCache(CACHE_NAME, id);
            toDoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus
                            .INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ToDoTaskData toDoTaskData) {
        try {
                var task = toDoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Task with id" + id + "was not found"));
                task.setText(toDoTaskData.getText());
                var taskDTO = ToDoTaskMapper.entityToDTO(task);
                toDoListCacheService.updateCache(CACHE_NAME, taskDTO, taskDTO.getId());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(taskDTO);

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            var tasks = toDoListCacheService.getAll(CACHE_NAME);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tasks);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
