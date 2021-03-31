package in.sanvic.controller;

import java.util.List;

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
		String status = "";
		if(contact.getContactId()==null)
			status = "save";
		else
			status = "update";
		Boolean isSaved = service.saveContact(contact);
		if(isSaved) {
			if(status.equals("save"))
			redirectAttributes.addFlashAttribute("succMsg", "Contact Saved..!!");
			else if(status.equals("update"))
				redirectAttributes.addFlashAttribute("succMsg", "Contact Updated..!!");
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
	
	 @GetMapping("/view-contacts")
	public String handleViewContactHyperlink(Model model){
		List<Contact> allContacts = service.getAllContacts();
		model.addAttribute("contacts", allContacts);
		
		return "contacts-display";
	}
}
