package in.sanvic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sanvic.constants.AppConstants;
import in.sanvic.entity.Contact;
import in.sanvic.properties.AppProperties;
import in.sanvic.service.ContactService;

@Controller
public class ContactInfoController {

	ContactService service;
	AppProperties props;

	
	public ContactInfoController(ContactService service, AppProperties props) {
		this.service = service;
		this.props = props;
		
		
	}

	@GetMapping("/load-form")
	public String loadForm(Model model) {
		Contact contactObj =  new Contact();
		
		model.addAttribute("contact",contactObj);
				
		return AppConstants.CONTACT;
	}
	
	@PostMapping("/save-contact")
	public String handleSubmitButton(Contact contact, Model model, RedirectAttributes redirectAttributes) {
		
		Map<String, String> messages = props.getMessages();
		Boolean isSaved = service.saveContact(contact);
		if(isSaved) {
			if(contact.getContactId()==null)
			redirectAttributes.addFlashAttribute("succMsg", messages.get("contactSavedSuccMsg"));
			else 
				redirectAttributes.addFlashAttribute("succMsg", messages.get("contactUpdatedSuccMsg"));
		}
		else {
			redirectAttributes.addFlashAttribute("failedMsg", messages.get("contactSaveFailMsg"));
		}
		return "redirect:/successform";
	}
	
	 
	 @GetMapping("/successform")
	 public String userSuccessForm(Model model){
			 
		 model.addAttribute("contact", new Contact());
		 return AppConstants.CONTACT;
	 }
	
	 @GetMapping("/view-contacts")
	public String handleViewContactHyperlink(Model model){
		List<Contact> allContacts = service.getAllContacts();
		model.addAttribute("contacts", allContacts);
		
		return "contacts-display";
	}
}
