package in.sanvic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sanvic.entity.Contact;
import in.sanvic.service.ContactService;

@Controller
public class ContactOperationController {

	ContactService service;
	
	public ContactOperationController(ContactService service) {
		this.service = service;
	}
	
	@GetMapping("/edit")
	public String editContact(@RequestParam("cid") Integer contactId, Model model) {
		
		Contact contactObj = service.getContactById(contactId);
		model.addAttribute("contact", contactObj);
		return "Contact";
	}
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("cid") Integer contactId, Model model, RedirectAttributes redirectAttributes) {
		
		Boolean isDeleted = service.deleteContactById(contactId);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("succMsg", "Contact Deleted Successfully..!!");
		}
		else {
			redirectAttributes.addFlashAttribute("failMsg", "Failed To Delete Contact..!!");
		}
		return "redirect:view-contacts";
	}
}
