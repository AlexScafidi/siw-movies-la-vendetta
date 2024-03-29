package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "surname" }))
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotBlank
	private String name;
	@Column(nullable = false)
	@NotBlank
	private String surname;
	@Column(nullable = false)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@PastOrPresent
	private LocalDate dateOfBirth;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@PastOrPresent
	private LocalDate dateOfDeath;
	private boolean isAlive;

	@ManyToMany(mappedBy = "actors")
	private List<Movie> starredMovies;

	@OneToMany(mappedBy = "director")
	private List<Movie> directedMovies;

	public Artist() {
		this.starredMovies = new LinkedList<>();
		this.directedMovies = new LinkedList<>();
		this.isAlive = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public List<Movie> getStarredMovies() {
		return starredMovies;
	}

	public void setStarredMovies(List<Movie> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public List<Movie> getDirectedMovies() {
		return directedMovies;
	}

	public void setDirectedMovies(List<Movie> directedMovies) {
		this.directedMovies = directedMovies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}

	/**
	 * metodo per controllare se un artista sia ancora vivo
	 * 
	 * @return
	 */
	public boolean isStillAlive() {
		return this.isAlive && this.dateOfDeath == null;
	}

}
