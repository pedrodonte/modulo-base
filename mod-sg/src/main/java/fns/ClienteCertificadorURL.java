package fns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ClienteCertificadorURL {
	private final String USER_AGENT = "Mozilla/5.0";
	
	private final boolean MOSTRAR_ORIGINAL = false;

	public static void main(String[] args) throws Exception {
		ClienteCertificadorURL http = new ClienteCertificadorURL();
		http.consultar(61607400,16184826,"3");//ISAPRE
		http.consultar(61607400,1689995,"2");//ACREDITADO
		http.consultar(61607400,8388146,"1");//ACREDITADO CON CARGAS
		http.consultar(61607400,10140245, "2");//FUNCIONARIO DE SALUD PRAIS
		http.consultar(61607400,71838966, "4"); //RUT NO VALIDO
		http.consultar(61607400,18685414, "4");//PRAIS
		http.consultar(6160740,18685414, "4");//PRAIS
		http.consultar(61607400,4182423, "9");//FALLECIDO
		http.consultar(61607400,14840478, "K");//BLOQUEADOS S/COTIZ. AL DIA
	}

	// HTTP GET request
	public Certificado consultar(int entidad, int rut, String dgv) throws Exception {

		String url = "http://200.51.172.210/trade_service/web_services/Certificado.asp?ID="+entidad+"&RUT="+rut+"&DGV="+dgv;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		// Header del Request
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("REQUEST\n*******\n" + url+"\n");

		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println("RESPONSE\n********");

			if (MOSTRAR_ORIGINAL) {
				System.out.println(response.toString());
			}
			try {
				Certificado certificado = (Certificado) XmlUtil.xmlToObject(response.toString().toLowerCase(), Certificado.class);
				return certificado;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		

	}
	
}
