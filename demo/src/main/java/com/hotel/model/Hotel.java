package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties(value={"name"})


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "All details about Hotels")
@Entity
public class Hotel {

	@Id
	@Size(min = 2)
	@NotEmpty(message = "Name is required")
	@NotNull
	@NotBlank

	public String name;

	@ApiModelProperty(notes = "Description")
	@Size(min = 2)
	public String description;

	@ApiModelProperty(notes = "Hotel Location")
	@Size(min = 2)	
	public String city;

	// @FutureOrPresent
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ApiModelProperty(notes = "Hotel Rating")
	public int rating;

	

}
