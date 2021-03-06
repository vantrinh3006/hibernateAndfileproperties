package edu.vinaenter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.vinaenter.model.User;
import edu.vinaenter.validate.PictureValidator;

@Controller
public class ValidFormController {

	@Autowired	//để liên kết 2 bean lại với nhau: pictureValidator và validFormController
	private PictureValidator pictureValidator;

	@GetMapping("valid")
	public String valid() {
		return "valid";
	}

	@PostMapping("valid")
	public String valid(@Valid @ModelAttribute("userError") User user, BindingResult rs,
			@RequestParam("picture") MultipartFile multipartFile, Model model , RedirectAttributes re) {
		
		model.addAttribute("user", user);
		pictureValidator.validate(multipartFile, rs);
		
		
		if (rs.hasErrors()) {
			return "valid";
		}
		System.out.println(user.getName());
		System.out.println(user.getAge());
		re.addFlashAttribute("success", "Không có lỗi khi nhập dữ liệu vào form");
		 return "redirect:/valid";
		// return "valid";  sai: ko lấy đc dữ liệu
	}

}
