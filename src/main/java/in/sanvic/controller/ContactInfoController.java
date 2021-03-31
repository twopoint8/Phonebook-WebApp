package in.sanvic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import in.sanvic.entity.Contact;
import in.sanvic.service.ContactService;

@Controller
public class ContactInfoController {

	ContactService service;
	
	
	
	public ContactInfoController(ContactService service) {
		this.service = service;
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/load-form")
	public String LoadForm(Model model) {
		Contact contactObj =  new Contact();
		
		//Sending data from controller to UI
		model.addAttribute("contact",contactObj);
		
		//returning logical view name
		return "Contact";
	}
	
	@PostMapping("/save-contact")
	public String handleSubmitButton(Contact contact, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(contact);
		
		Boolean isSaved = service.saveContact(contact);
		if(isSaved) {
			redirectAttributes.addFlashAttribute("succMsg", "Contact Saved..!!");
		}
		else {
			redirectAttributes.addFlashAttribute("failedMsg", "Failed to save contact..!!");
		}
		return "redirect:/successform";
	}
	
	 
	 @GetMapping("/successform")
	 public String userSuccessForm(Model model){
			 
		 model.addAttribute("contact", new Contact());
		 return "Contact";
	 }
	
	public String handleViewContactHyperlink(){
		
		return "Contact";
	}
}
