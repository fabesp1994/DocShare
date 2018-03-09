package beans;

public class Condivisione {
	
	//VARIABILI
	private int id;
	private String utenteCondivisione;
	private int documento;
	private String data;
	private String nomeU;
	private String emailU;
	
	//COSTRUTTORI
	public Condivisione(int id, String utenteCondivisione, int documento, String data) {
		super();
		this.id = id;
		this.utenteCondivisione = utenteCondivisione;
		this.documento = documento;
		this.data = data;
		this.nomeU=null;
		this.emailU=null;
	}


	//METODI
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUtenteCondivisione() {
		return utenteCondivisione;
	}

	public void setUtenteCondivisione(String utenteCondivisione) {
		this.utenteCondivisione = utenteCondivisione;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getNomeU() {
		return nomeU;
	}

	public void setNomeU(String nomeU) {
		this.nomeU = nomeU;
	}

	public String getEmailU() {
		return emailU;
	}

	public void setEmailU(String emailU) {
		this.emailU = emailU;
	}
	
	

}
