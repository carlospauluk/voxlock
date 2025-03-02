package br.com.voxlock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String document;
	private String password;
	private int accessCode;
	private String securityPhrase;
	private String email;

	@ManyToMany
	@JoinTable(
		name = "user_integration",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "integration_id")
	)
	private Set<Integration> integrations;
}
