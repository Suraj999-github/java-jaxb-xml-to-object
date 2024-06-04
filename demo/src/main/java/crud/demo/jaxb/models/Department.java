package crud.demo.jaxb.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "department")
public class Department implements Serializable { 
  /**
	 * 
	 */
	private static final long serialVersionUID = -712259930230361523L;
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private long id;
  Integer ids;
  String name;

  // constructors, getters, setters, toString()
}