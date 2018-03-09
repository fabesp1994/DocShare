package beans;

public class Documento {
	
	//VARIABILI
	private int id;
	private String nome;
	private String data;
	private String testo;
	private String proprietario;
	
	//COSTRUTTORI
	public Documento(int id, String nome, String data, String testo, String proprietario) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.testo = testo;
		this.proprietario = proprietario;
	}

	//METODI
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}
	
	@Override
	public String toString() {
		return "Documento [id=" + id + ", nome=" + nome + ", data=" + data + ", testo=" + testo + ", proprietario="
				+ proprietario + "]";
	}

	
	

}
