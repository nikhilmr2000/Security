package com.nikhil.springsecuritybasic.controller;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.springsecuritybasic.Entitu.AccountTransactions;
import com.nikhil.springsecuritybasic.Entitu.Accounts;
import com.nikhil.springsecuritybasic.Entitu.Cards;
import com.nikhil.springsecuritybasic.Entitu.Contact;
import com.nikhil.springsecuritybasic.Entitu.Customer;
import com.nikhil.springsecuritybasic.Entitu.Loans;
import com.nikhil.springsecuritybasic.Entitu.Notice;
import com.nikhil.springsecuritybasic.Repository.AccountTransactionsRepository;
import com.nikhil.springsecuritybasic.Repository.AccountsRepository;
import com.nikhil.springsecuritybasic.Repository.CardsRepository;
import com.nikhil.springsecuritybasic.Repository.ContactRepository;
import com.nikhil.springsecuritybasic.Repository.CustomerRepository;
import com.nikhil.springsecuritybasic.Repository.LoansRepository;
import com.nikhil.springsecuritybasic.Repository.NoticeRepository;


@RestController
public class SpringBasicController {
	
	@Autowired
	public CustomerRepository repo;
	
	@Autowired
    private AccountsRepository accountsRepository;
	
	
	@Autowired
    private AccountTransactionsRepository accountTransactionsRepository;
	
	@Autowired
    private CardsRepository cardsRepository;
	
	 @Autowired
	 private ContactRepository contactRepository;
	 
	 @Autowired
	 private LoansRepository loanRepository;
	 
	 @Autowired
	 private NoticeRepository noticeRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	/*CONTACT_API_URL : "/contact",
    LOGIN_API_URL : "/user",
    ACCOUNT_API_URL : "/myAccount",
    BALANCE_API_URL : "/myBalance",
    LOANS_API_URL : "/myLoans",
    CARDS_API_URL : "/myCards",
    NOTICES_API_URL : "/notices"*/
	
	@GetMapping("/user")
	public Customer getCustomerDetails(Authentication authentication) {
		List<Customer> customer=repo.findByEmail(authentication.getName());
		if(customer.size()>0) {
			return customer.get(0);
		}
		else {
			return null;
		}
	}
	

	/*    @GetMapping("/myAccount")
	    public Accounts getAccountDetails(@RequestParam int id) {
	        Accounts accounts = accountsRepository.findByCustomerId(id);
	        if (accounts != null ) {
	            return accounts;
	        }else {
	            return null;
	        }
	    }
	    
	    
	    

	    @GetMapping("/myBalance")
	    public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
	        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
	                findByCustomerIdOrderByTransactionDtDesc(id);
	        if (accountTransactions != null ) {
	            return accountTransactions;
	        }else {
	            return null;
	        }
	    }
	    
	

	    @GetMapping("/myCards")
	    public List<Cards> getCardDetails(@RequestParam int id) {
	        List<Cards> cards = cardsRepository.findByCustomerId(id);
	        if (cards != null ) {
	            return cards;
	        }else {
	            return null;
	        }
	    }
	    
	 

	    @PostMapping("/contact")
	    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
	        contact.setCreateDt(new Date(System.currentTimeMillis()));
	        return contactRepository.save(contact);
	    }
	    

	    @GetMapping("/myLoans")
	    public List<Loans> getLoanDetails(@RequestParam int id) {
	        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
	        if (loans != null ) {
	            return loans;
	        }else {
	            return null;
	        }
	    }
	    */

	    @GetMapping("/notices")
	    public List<Notice> getNotices() {
	        List<Notice> notices = noticeRepository.findAllActiveNotices();
	        if (notices != null ) {
	            return notices;
	        }else {
	            return null;
	        }
	    }

	
	@PostMapping("/register")
	public Customer saveCustomerRepo(@RequestBody Customer customer) {
		String hashPass=passwordEncoder.encode(customer.getPwd());
		customer.setPwd(hashPass);
		customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
		Customer savedCustomer=repo.save(customer);
		return savedCustomer;
	}
}
