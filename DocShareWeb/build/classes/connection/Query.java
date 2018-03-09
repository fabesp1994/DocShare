package connection;

import beans.Data;

public class Query {
	
	public static String cercaUtente(String username, String password) {
		return "SELECT * FROM Utente WHERE nickname=\""+username+"\" AND password =\""+password+"\"";
	}
	
	public static String cercaUtente2(String username) {
		return "SELECT * FROM Utente WHERE nickname=\""+username+"\"";
	}
	
	public static String cercaUtente3(String id) {
		return "SELECT * FROM Utente WHERE id="+id;
	}
	
	public static String cercaDocumento(String id) {
		return "SELECT * FROM Documento WHERE id=\""+id+"\"";
	}
	
	public static String cercaCondivisione(int id) {
		return "SELECT * FROM Condivisione WHERE id="+id;
	}
	
	public static String tuttiGliUtenti() {
		return "SELECT * FROM Utente";
	}
	
	public static String documentiUtente(int id) {
		return "SELECT * FROM Documento WHERE proprietario="+id;
	}
	
	public static String documentiUtenteCond(int id) {
		return "SELECT * FROM Documento INNER JOIN Condivisione ON Documento.id=Condivisione.documento AND Condivisione.utenteCondivisione="+id;
	}
	
	public static String inserisciUtente(String id, String nickname, String email, String password) {
		return "INSERT INTO Utente VALUES ("+id+",\""+nickname+"\",\""+email+"\",\""+password+"\")";
	}
	
	public static String inserisciDocumento(int id, String nome,Data data, String testo, int proprietario) {
		return "INSERT INTO `documento`(`id`, `nome`, `data`, `testo`, `proprietario`) VALUES ("+id+","+nome+","+data.toString()+","+testo+","+proprietario+")";
	}
	
	public static String inserisciCondivisione(int id,int utenteCondivisione,int documento,Data data) {
		return "INSERT INTO `condivisione`(`id`, `utenteCondivisione`, `documento`, `data`) VALUES ("+id+","+utenteCondivisione+","+documento+","+data.toString()+")";
	}
	
	public static String condivisioniDelProprietario(String id) {
		return "SELECT * FROM Condivisione WHERE documento="+id;
	}
	
	public static String condivisioneUtenteCondivisione(int id) {
		return "SELECT * FROM Condivisione WHERE utenteCondivisione="+id;
	}
	
	public static String eliminaCondivisione(String id,int utente) {
		return "DELETE FROM Condivisione WHERE documento="+id+" AND utenteCondivisione="+utente;
	}
	
	public static String eliminaDocumento(int id) {
		return "DELETE FROM `documento` WHERE id="+id;
	}
	
	public static String CondivisioniDatoDocumento(int id) {
		return "SELECT * FROM Condivisione WHERE documento="+id;
	}

}
