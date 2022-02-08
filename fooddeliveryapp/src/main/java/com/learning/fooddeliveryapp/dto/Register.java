package com.learning.fooddeliveryapp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learning.fooddeliveryapp.utils.CustomListSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="register")
public class Register 
{
	
	@Email // giving the email constraint 
	@NotNull
	private String email;
	
	@NotNull
	@Size(max=40)
	private String name;
	
	@Size(min=8)
	@NotNull
	private String password;
	
	@Size(max=100)
	@NotNull
	private String address;
	
	@Id  // making regId as primary key
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int regId;
	
	
	
//	  @JsonSerialize(using=CustomListSerializer.class)
	  @OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
	  private Login login;

}
