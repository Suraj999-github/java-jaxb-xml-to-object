package crud.demo.jaxb.controllers;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import crud.demo.jaxb.models.JaxbSample;
import crud.demo.jaxb.services.JaxbSampleService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@Controller
public class JaxbController {

	@Autowired
	JaxbSampleService jaxbSampleService;
	@GetMapping("/jaxb")
	public String showJabxForm(Model model) throws JsonProcessingException {
		 String xmlString = """
		 		   <jaxb_sample>
		 		   <sample>
			            <department>
			                <ids>101</ids>
			                <name>IT</name>
			            </department>
			            <location>India</location>
			            <firstName>Lokesh</firstName>
			            <ids>1</ids>
			            <lastName>Gupta</lastName>
			          </sample>
			          <sample>
			            <department>
			                <ids>101</ids>
			                <name>IT</name>
			            </department>
			            <location>India</location>
			            <firstName>Lokesh</firstName>
			            <ids>1</ids>
			            <lastName>Gupta</lastName>
			          </sample>
			          <sample>
			            <department>
			                <ids>101</ids>
			                <name>IT</name>
			            </department>
			            <location>India</location>
			            <firstName>Lokesh</firstName>
			            <ids>1</ids>
			            <lastName>Gupta</lastName>
			          </sample>
			          <sample>
			            <department>
			                <ids>101</ids>
			                <name>IT</name>
			            </department>
			            <location>India</location>
			            <firstName>Lokesh</firstName>
			            <ids>1</ids>
			            <lastName>Gupta</lastName>
			          </sample>
			          <sample>
			            <department>
			                <ids>101</ids>
			                <name>IT</name>
			            </department>
			            <location>India</location>
			            <firstName>Lokesh</firstName>
			            <ids>1</ids>
			            <lastName>Gupta</lastName>
			          </sample>
			        </jaxb_sample>
		 		""";

			    JAXBContext jaxbContext;
			    try {
			      jaxbContext = JAXBContext.newInstance(JaxbSample.class);
			      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			      JaxbSample st = (JaxbSample) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
			      jaxbSampleService.save(st);
			      System.out.println(st);
			      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			      String json = ow.writeValueAsString(st);
			      System.out.println(json);
			    } catch (JAXBException e) {
			      e.printStackTrace();
			    }
		// create model attribute to bind form data		
		return "views/jaxb/index";
	}
}
