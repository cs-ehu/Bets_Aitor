package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Result implements Serializable{
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer resultNumber;
	private String result;
	

	public Result() {
		super();
	}
	
	public Result(Integer resultNumber, String result) {
		this.resultNumber=resultNumber;
		this.result=result;
	}

	public Integer getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(Integer resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}


