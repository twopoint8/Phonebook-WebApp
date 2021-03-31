package in.sanvic.service;

import java.util.List;

import in.sanvic.entity.Contact;

public interface ContactService {

	// Used for save the contact
	public Boolean saveContact(Contact contact);
	
	// Used to get all the contacts
	public List<Contact> getAllContacts();
	
	// Used to retrieve the one contact by its Id
	public Contact getContactById(Integer contactId);
	
	// Used to delete one contact based on the given contact id
	public Boolean deleteContactById(Integer contactId);
	
		
	
}
