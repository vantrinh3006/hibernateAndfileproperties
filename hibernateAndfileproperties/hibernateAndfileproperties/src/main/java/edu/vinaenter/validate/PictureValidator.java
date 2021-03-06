package edu.vinaenter.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component   // để biến PictureValidator thành bean trong spring
public class PictureValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		
	}

	public void validate(MultipartFile multipartFile, Errors errors) {
		if (multipartFile.getOriginalFilename().equals("")) {	 
			errors.rejectValue("avatar", null, "Vui lòng chọn file hình ảnh");
				// reject cho Error cũng như reject cho BindingResult rs vì  BindingResult extend với Error : dòng 31,35 valid form
		}
	}
	
}
