package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(Model model){
        log.info("todo list...............");

        model.addAttribute("dtoList",todoService.getAll());
        //model에는 doList이름으로 목록 데이터가 담겨있다.
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("todo register.............");
    }
    // todo//register 를 post방식으로 처리하는 메소드 TODO를 파라미터로 적용용

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("Post todo register");

        if(bindingResult.hasErrors()){
            log.info("error error");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        log.info(todoDTO);
        todoService.regiser(todoDTO);
        return "redirect:/todo/list";

    }

    @GetMapping(value = {"/read", "/modify"})
    public void read(int tno, Model model){

        TodoDTO todoDTO = todoService.selectOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO );

    }
//
//    @GetMapping(value = "/modify")
//    public void modify(int tno, Model model){
//        TodoDTO todoDTO = todoService.selectOne(tno);
//        log.info(todoDTO);
//        model.addAttribute("dto",todoDTO );
//    }

    @PostMapping(value = "/remove")
    public String remove(int tno, RedirectAttributes redirectAttributes){
        todoService.delete(tno);
        log.info(tno + "delete complete");
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("error modify");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno",todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
    }

}
