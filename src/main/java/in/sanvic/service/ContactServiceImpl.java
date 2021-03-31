package in.sanvic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sanvic.entity.Contact;
import in.sanvic.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactRepository contactRepositiry;

	@Autowired
	public ContactServiceImpl(ContactRepository contactRepositiry) {
		this.contactRepositiry = contactRepositiry;
	}

	@Override
	public Boolean saveContact(Contact contact) {
		// TODO Auto-generated method stub
		Contact savedObj = contactRepositiry.save(contact);

//		  if(savedObj.getContactId()!=null){
//			  return true; 
//			  } 
//		  else { 
//			  return false; 
//			  }

		/*--------------------- OR -----------------*/

		// return (savedObj.getContactId() != null) ? true : false;

		/*--------------------- OR -----------------*/

		return savedObj.getContactId() != null;

	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
//		List<Contact> contactList = contactRepositiry.findAll();
//		return contactList;

		/*--------------------- OR -----------------*/

		return contactRepositiry.findAll();
	}

	@Override
	public Contact getContactById(Integer contactId) {
		// TODO Auto-generated method stub
		Optional<Contact> contact = contactRepositiry.findById(contactId);
		if (contact.isPresent()) {
			return contact.get();
		}
		return null;
	}

	@Override
	public Boolean deleteContactById(Integer contactId) {
		// TODO Auto-generated method stub
		Boolean isDeleted = false;
		try {
			contactRepositiry.deleteById(contactId);
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

}
