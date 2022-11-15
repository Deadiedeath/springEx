package net.ict.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//데이터베이스를 처리하는 TodoMapper와  DTO와 VO변환을 처리하는  modelMapper주입
//생성자 주입방식
//의존성 주입이 필요한 객체의 타입을 finla로 고정하고 생성자 RequiredArgsConstructor를 이용하여 생성자를 생성하는 방식
@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void regiser(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }

    @Override
    public List<TodoDTO> getAll() {
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo->modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;

        //List<TodoVO>를 List<TodoDTO>로 변환하는 작업을 stream을 이용하여 각 TodoVO는 map()을 통해서 TodoDTO로 바꾸고 collect()를 이용하여
        //List<TodoDTO>로 묶어준다.
    }

    @Override
    public void delete(int tno) {
        todoMapper.delete(tno);
    }

    @Override
    public TodoDTO selectOne(int tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void modify(TodoDTO dto) {
        TodoVO todoVO = modelMapper.map(dto, TodoVO.class);
        todoMapper.update(todoVO);
    }


}
