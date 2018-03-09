package beans;

public class Utente {

	//VARIABILI
	private int id;
	private String nickname;
	private String email;
	private String password;
	
	//COSTRUTTORE
	
	public Utente(int id, String nickname, String email, String password) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

	public Utente() {
		super();
	}
	
	//METODI

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nickname=" + nickname + ", email=" + email + ", password=" + password + "]";
	}

}
