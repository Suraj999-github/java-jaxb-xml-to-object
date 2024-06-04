package crud.demo.jaxb.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sample")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "sample")
public class Sample implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = -1739795985929882602L;

  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private long id;

  private Integer ids;
  private String firstName;
  private String lastName;
  private String location;
  @OneToOne(cascade = CascadeType.ALL)
  private Department department;
}