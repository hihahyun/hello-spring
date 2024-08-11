package hello.hello_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!");  // hello.html에서 key인 "data"가 value인 "hello!"로 치환이 됨
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name") String name, Model model){  //http://localhost:8080/hello-mvc?name=spring! 이라고하면 name에 spring!이 들어감
        model.addAttribute("name", name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {  //http://localhost:8080/hello-string?name=spirng!!! 라고 하면 hellospring!!!이 출력됨
        return "hello " + name;  //"hello {name}" 이 웹 화면에 출력됨. "<html>hello " + name + "/<html>";과 같음  //단순 문자이면 StringConverter가 동작함

    }



    //api 방식
    @GetMapping("hello-api")
    @ResponseBody
    // http://localhost:8080/hello-api?name=spring!!! 라고 하면 {"name":"spring!!!"} 이렇게 출력됨. (json 구조 {key:value})

    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;  //객체가 리턴되면 json방식으로 반환  // 객체이면 JsonConverter가 동작함

    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
