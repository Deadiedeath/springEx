package net.ict.springex.service;


import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class serviceTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO =TodoDTO.builder()
                .title("wowo")
                .dueDate(LocalDate.now())
                .writer("you")
                .build();
        todoService.regiser(todoDTO);
    }

    @Test
    public void testlist(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);
        log.info(pageResponseDTO);
        pageResponseDTO.getDtoList().stream().forEach(dto -> log.info(dto));
    }
}
