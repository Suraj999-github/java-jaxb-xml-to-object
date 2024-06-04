package crud.demo.jaxb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crud.demo.jaxb.models.JaxbSample;
import crud.demo.jaxb.repositories.JaxbSampleRepository;

@Service
public class JaxbSampleServiceImpl implements JaxbSampleService{
	
	@Autowired
	JaxbSampleRepository jaxbSampleRepository;

	@Override
	public JaxbSample save(JaxbSample sample) {
		try {
			sample=jaxbSampleRepository.saveAndFlush(sample);	
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sample;
			
	}

}
