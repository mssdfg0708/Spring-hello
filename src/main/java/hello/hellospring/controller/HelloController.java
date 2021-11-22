package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("spring")
    public String spring(Model model) {
        model.addAttribute("data", "Spring!!!");
        return "hello";
    }

    @GetMapping("java")
    public String java(Model model) {
        model.addAttribute("data", "Java!!!");
        return "hello";
    }

    @GetMapping("helloController")
    public String hello(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "helloTemplate";
    }

    @GetMapping("helloStringController")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return  "hello" + name;
    }

    @GetMapping("helloApiController")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
