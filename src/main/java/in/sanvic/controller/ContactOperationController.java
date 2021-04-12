package in.sanvic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sanvic.constants.AppConstants;
import in.sanvic.entity.Contact;
import in.sanvic.properties.AppProperties;
import in.sanvic.service.ContactService;

@Controller
public class ContactOperationController {

	ContactService service;
	AppProperties props;
	
	@Autowired
	public ContactOperationController(ContactService service, AppProperties props) {
		this.service = service;
		this.props = props;
	}
	
	@GetMapping("/edit")
	public String editContact(@RequestParam("cid") Integer contactId, Model model) {
		
		Contact contactObj = service.getContactById(contactId);
		model.addAttribute("contact", contactObj);
		return AppConstants.CONTACT;
	}
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("cid") Integer contactId, Model model, RedirectAttributes redirectAttributes) {
		
		Map<String, String> messages = props.getMessages();
		Boolean isDeleted = service.deleteContactById(contactId);
		
		if(isDeleted) {
			redirectAttributes.addFlashAttribute("succMsg", messages.get("contactDeleteSuccMsg"));
		}
		else {
			redirectAttributes.addFlashAttribute("failMsg", messages.get("contactFailSuccMsg"));
		}
		return "redirect:view-contacts";
	}
}
