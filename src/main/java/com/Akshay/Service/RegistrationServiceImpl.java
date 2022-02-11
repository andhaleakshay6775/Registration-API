package com.Akshay.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Akshay.Entities.CityEntity;
import com.Akshay.Entities.CountryEntity;
import com.Akshay.Entities.StateEntity;
import com.Akshay.Entities.UserEntity;
import com.Akshay.bindding.User;
import com.Akshay.constants.AppConstants;
import com.Akshay.mailUtils.EmailUtils;
import com.Akshay.repository.CityRepository;
import com.Akshay.repository.CountryRepository;
import com.Akshay.repository.StateRepository;
import com.Akshay.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	

	@Override
	public boolean uniqueEmail(String email) {

		
		 UserEntity userEntity = userRepo.findByUserEmail(email);
		
		if(userEntity != null){
			return false;
		}else {
			
		return true;
		}
	}

	@Override
	public Map<Integer, String> getCountries() {
		
		List<CountryEntity> findAll = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		
		for(CountryEntity entity : findAll) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		}
		return countryMap;
	}

	
	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		
	  List<StateEntity> statesList = stateRepo.findByCountryId(countryId);
	  
	  Map<Integer, String> statesMap = new HashMap<>();
	  
	  for(StateEntity entity : statesList) {
		  
		  statesMap.put(entity.getStateId(), entity.getStateName());
	  }
		
		
		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		List<CityEntity> citiesList = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap<>();
		
		for(CityEntity entity: citiesList) {
			
			cityMap.put(entity.getCityId(), entity.getCityName());
		}
		return cityMap;
	}

	@Override
	public boolean registerUser(User user) {
      
	    UserEntity entity = new UserEntity();
		  BeanUtils.copyProperties(user, entity);
		  user.setUserPWD(generatePaswd());
		  user.setUserAccStatus("LOCKED");
		    UserEntity savedUser = userRepo.save(entity);
		    
		    if(null != savedUser.getUserId()) {
		    	return sendRegistrationEmail(user);
		    }
	 
		return false;
	}
	
	private String generatePaswd() {
		String tempPwd = null;
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 6;
		
		Random random = new Random();
		
		tempPwd  = random.ints(leftLimit, rightLimit +1).filter(i -> (i <= 57 || i>= 65 ) && (i<= 90 || i >= 97))
				         .limit(targetStringLength)
				         .collect(StringBuilder :: new, StringBuilder :: appendCodePoint, StringBuilder :: append).toString();
		
		
		return tempPwd;
		
		
	}
	
	private boolean sendRegistrationEmail(User user) {
		
		boolean emailSent = false;
		String subject = "User registration Success";
		String fileName = "UNLOCK-EMAIL_BODY-TEMPLATE.txt";
		String body = readMailBody(fileName, user);
		String to = user.getUserEmail();
		 emailSent = emailUtils.sendEmail(subject, body, to);
		
		return emailSent;
		
	}
	
	public String readMailBody(String fileName, User user) {
		
		String mailBody = null;
		StringBuffer buffer = new StringBuffer();
		
		Path path = Paths.get(fileName);
		
		try (Stream<String> stream = Files.lines(path)) {
			
			stream.forEach(line -> {
				buffer.append(line);
			});
			
			mailBody = buffer.toString();
			
			mailBody = mailBody.replace("{FNAME}", user.getUserFirstName());
			mailBody = mailBody.replace("{EMAIL}", user.getUserEmail());
			mailBody = mailBody.replace("{TEMP-PWD}", user.getUserPWD());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return mailBody;
		
	}

}
