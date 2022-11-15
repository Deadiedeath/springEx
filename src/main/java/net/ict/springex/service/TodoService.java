package net.ict.springex.service;

import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void regiser(TodoDTO todoDTO);
    List<TodoDTO> getAll();
    void delete(int tno);
    TodoDTO selectOne(int tno);
    void modify(TodoDTO Dto);

}
