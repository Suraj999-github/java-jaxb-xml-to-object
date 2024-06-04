package crud.demo.jaxb.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "jaxb_sample")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "jaxb_sample")
public class JaxbSample implements Serializable {

  /**
	 * 
	 */
	private static final long serialVersionUID = -4608759126456755235L;
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private long id;
  @OneToMany(cascade = CascadeType.ALL)
  private List<Sample> sample;
  
}