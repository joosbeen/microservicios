package academy.digitallap.store.servicecustomer.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_regions")
public class Region implements Serializable {

	private static final long serialVersionUID = 4240147765677780429L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

}