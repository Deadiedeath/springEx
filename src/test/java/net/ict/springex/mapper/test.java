package net.ict.springex.mapper;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class test {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void timetest(){
    log.info(todoMapper.getTime());
    }

    @Test
    public void inserttest(){
        TodoVO vo = TodoVO.builder()
                .title("wow")
                .dueDate(LocalDate.now())
                .writer("me")
                .build();
        todoMapper.insert(vo);
    }
    @Test
    public void listtest(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void deletetest(){
        todoMapper.delete(1);
    }

//    @Test
//    public void selecttest(){
//        log.info(todoMapper.selectOne(2));
//    }

    @Test
    public void sellisttest(){
        PageRequestDTO pageRequestDTO= PageRequestDTO.builder()
                        .page(1)
                                .size(10)
                                        .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelect(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[] {"t","w"})
                .keyword("aaa")
                .finished(false)
                .from(LocalDate.of(2022,11,15))
                .to(LocalDate.of(2022,11,16))
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo ->log.info(vo));
    }

}
