package in.sanvic.service;

import java.util.List;

import in.sanvic.entity.Contact;

public interface ContactService {

	
	public Boolean saveContact(Contact contact);
	
	
	public List<Contact> getAllContacts();
	
	
	public Contact getContactById(Integer contactId);
	
	
	public Boolean deleteContactById(Integer contactId);
	
		
	
}
