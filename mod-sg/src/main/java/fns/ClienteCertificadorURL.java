package fns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ClienteCertificadorURL {
	private final String USER_AGENT = "Mozilla/5.0";
	
	private final boolean MOSTRAR_ORIGINAL = true;

	public static void main(String[] args) throws Exception {
		ClienteCertificadorURL http = new ClienteCertificadorURL();
//		http.consultar(16184826,"3");//ISAPRE
//		http.consultar(1689995,"2");//ACREDITADO
//		http.consultar(8388146,"1");//ACREDITADO CON CARGAS
//		http.consultar(10140245, "2");//FUNCIONARIO DE SALUD PRAIS
//		http.consultar(71838966, "4"); //RUT NO VALIDO
//		http.consultar(18685414, "4");//PRAIS
		http.consultar(18685414, "4");//PRAIS
		http.consultar(4182423, "9");//FALLECIDO
//		http.consultar(14840478, "K");//BLOQUEADOS S/COTIZ. AL DIA
	}

	// HTTP GET request
	public Certificado consultar(int rut, String dgv) throws Exception {

		String url = "http://200.51.172.210/trade_service/web_services/Certificado.asp?ID=61607400&RUT="+rut+"&DGV="+dgv;

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
				System.out.println(certificado);
				return certificado;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		

	}

	public Certificado consultar(String rut) throws Exception {
		
		int numero = 0;
		String dv = "";
		
		if (rut.split("-").length != 2) {
			throw new Exception(rut + "no tiene un formato válido 12345678-9");
		}
		
		numero = Integer.parseInt(rut.split("-")[0]);
		dv = rut.split("-")[1];

		return consultar(numero, dv);
	}
	
}
