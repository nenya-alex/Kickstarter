package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ua.nenya.dao.PaymentDao;
import ua.nenya.dao.ProjectDao;
import ua.nenya.dao.RewardDao;
import ua.nenya.domain.Payment;
import ua.nenya.domain.Project;

@RestController
public class PaymentRestController {

	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private RewardDao rewardDao;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentRestController.class);
	
	@RequestMapping(value = "/payment/add/{projectId}", method = RequestMethod.POST)
	public RedirectView addPayment(@PathVariable("projectId") Long projectId, 
			@RequestParam("amount") int amount,
			@RequestParam("cardholderName") String cardholderName,
			@RequestParam("cardNumber") String cardNumber) {
		
		RedirectView redirectView = new RedirectView();
		String url = "";
		
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
			url = "/error.html";
			redirectView.setUrl(url);
		    return redirectView;
		}
		Payment payment = new Payment();
		payment.setProject(projectDao.getProjectByProjectId(projectId));
		payment.setAmount(amount);
		payment.setCardholderName(cardholderName);
		payment.setCardNumber(cardNumber);
		paymentDao.writePaymentInProject(payment);
		url = "/project.html?projectId="+projectId;
	    redirectView.setUrl(url);
	    return redirectView;
	}
	
	@RequestMapping(value = "/payment/add/reward/{rewardId}", method = RequestMethod.POST)
	public RedirectView addPaymentAsReward(@PathVariable("rewardId") Long rewardId, 
			@RequestParam("amount") int amount,
			@RequestParam("cardholderName") String cardholderName,
			@RequestParam("cardNumber") String cardNumber) {
		
		RedirectView redirectView = new RedirectView();
		String url = "";
		if (!rewardDao.isRewardExist(rewardId)) {
			logger.error("Reward with id "+rewardId+" dosen't exist!");
			url = "/error.html";
			redirectView.setUrl(url);
		    return redirectView;
		}
		
		
		Payment payment = new Payment();
		Project project = rewardDao.getProjectByRewardId(rewardId);
		payment.setProject(project);
		payment.setAmount(amount);
		payment.setCardholderName(cardholderName);
		payment.setCardNumber(cardNumber);
		paymentDao.writePaymentInProject(payment);
		
		url = "/project.html?projectId="+project.getId();
	    redirectView.setUrl(url);
	    return redirectView;
	}
}
