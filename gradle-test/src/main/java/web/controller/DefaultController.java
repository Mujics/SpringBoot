package web.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.model.Email;
import web.model.OrangeUser;
import web.repository.UserRepository;
import web.service.EmailService;

@Controller
public class DefaultController {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender sender;

	
    @GetMapping("/")
    public String home1() {
        return "/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String userPage(Model model) {
    	OrangeUser user = userRepository.findOne(1);
    	model.addAttribute("user", user );
    	model.addAttribute("email", new Email() );
        return "/user";
    }
    
    @PostMapping("/user")
    public String greetingSubmit(@ModelAttribute OrangeUser user) {
        return "OrangeUserresult";
    }
    
    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
    @GetMapping("/unlockPassword")
    public String unlockPassword() {
        return "/unlockPassword";
    }
    
    @GetMapping("/clearPassword")
    public String clearPassword() {
        return "/clearPassword";
    }
    
    @GetMapping("/modifySecondEmail")
    public String modifySecondEmail() {
        return "/modifySecondEmail";
    }
    
    @GetMapping("/modifyPassword")
    public String modifyPassword(Model model) {
    	model.addAttribute("user", getCurrentUser() );
        return "/modifyPassword";
    }
    
    @PostMapping("/modifyPassword")
    public String modifyPasswordResult(@ModelAttribute OrangeUser user) {
        return "/modifyPasswordResult";
    }
    
   
    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute Email email) {
    	try {
			sendEmailMethod(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "/home";
    }
    
    private OrangeUser getCurrentUser() {
    	return userRepository.findOne(1);
    }
    
	@RequestMapping("/simpleemail")
	@ResponseBody
	String home() {
		try {
			sendEmailMethod(new Email());
			return "Email Sent!";
		} catch (Exception ex) {
			return "Error in sending email: " + ex;
		}
	}

	public void sendEmailMethod(Email email) throws Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(email.getTo().get(0));
		helper.setText("How are you?");
		helper.setSubject("Hi");
		sender.send(message);
	}
}