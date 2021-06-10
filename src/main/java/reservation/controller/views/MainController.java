package kr.or.connect.reservation.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	// SecurityConfig 파일에서 /main은 누구나 접근할 수 있도록 했다.
	//  @ResponseBody 어노테이션이 붙어있을 경우엔 리턴하는 문자열을 화면에 직접 출력
    @RequestMapping("/main")
    @ResponseBody
    public String main(){
        return "main page";
    }

    @RequestMapping("/securepage")
    @ResponseBody
    public String securitypage(){
        return "secure page";
    }
}