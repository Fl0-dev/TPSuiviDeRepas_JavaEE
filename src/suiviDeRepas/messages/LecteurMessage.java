package suiviDeRepas.messages;

import java.util.ResourceBundle;

public abstract class LecteurMessage {

	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("suiviDeRepas.messages.messages_erreur");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageErreur(int codeErreur) {
		String message = "";
		try {
			if(rb != null) {
				message = rb.getString(String.valueOf(codeErreur));
			} else {
				message = "Problème à la lecture du fichier contenant les messages";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Erreur inconnue";
		}
		return message;
	}
}
