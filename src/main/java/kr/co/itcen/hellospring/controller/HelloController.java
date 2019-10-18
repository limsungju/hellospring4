package kr.co.itcen.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		
		return "hello";
	}
	
	@RequestMapping("/hello2")
	// ModelAndView에 값과, 뷰페이지를 담아주고 리턴해준다.
	public ModelAndView hello2() {
		String data = "Hello World2";
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", data);
		mav.setViewName("hello");
		
		
		return mav;
	}
	
	@RequestMapping("/hello3")
	// Model에 값만 담고 String 형태로 리턴해준다.
	public String hello3(Model model) {
		String data = "Hello World3";
		
		model.addAttribute("data", data);
		
		
		return "hello";
	}
	
	@RequestMapping("/hello4")
	// e로 넘어오는 파라미터를 email에 세팅한다.
	// required=false : 넘어오는값이 없으면 안받고, 있으면 받는다.
	// required=true : 넘어오는 값이 없으면 에러난다.
	// defaultValue : 넘어오는 값이 없으면 기본적으로 셋팅해주는 값이다.
	public String hello4(
			@RequestParam(value="email", required=true, defaultValue="") String email,
			@RequestParam(value="age", required=true, defaultValue="0") int age, 
			Model model) {
		
		model.addAttribute("email", email);
		model.addAttribute("age", age);
		
		
		return "hello";
	}
	
	@RequestMapping("/hello5")
	// @ModelAttribute를 넣어주면 model에 셋팅한 값을 jsp에서 사용할 수 있다.
	public String hello5(@ModelAttribute User user, Model model) {
		System.out.println(user);
		
		model.addAttribute("email", user.getEmail());
		model.addAttribute("age", user.getAge());
		
		
		return "hello";
	}
	
	/*
	 * 기술 침투 !!!!
	 * 비추천
	 * 다른 WAS에서 사용을 못함 ( 이식성이 떨어짐 )
	@RequestMapping("/hello6")
	public void hello6(HttpServletRequest request,HttpServletResponse response,
			Writer out, Model model) throws ServletException, IOException {
		
		//request.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(request, response);
		out.write("<h1>Hello World</h1>");
		
		return ;
	}
	*/
	
	@ResponseBody // 응답 바디라는 표시
	@RequestMapping("/hello7")
	public String hello7() {
		
		return "<h1>Hello World</h1>";
	}
	
}
