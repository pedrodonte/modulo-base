package modulo.integracion.ejb;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;

import util.HelperString;
import fns.Certificado;
import fns.ClienteCertificadorURL;
import modulo.base.vo.VoPersona;
import modulo.integracion.excepciones.ErrorIntegracionException;

@Stateless
public class IntegracionEJBImpl implements IntegracionEJB {

	@Override
	public VoPersona buscarPersona(String rut) throws ErrorIntegracionException {
		
		ClienteCertificadorURL basePersonas = new ClienteCertificadorURL();
		
		VoPersona persona = new VoPersona();
		
		try {
			Certificado data = basePersonas.consultar(rut);

			persona = new VoPersona();
			persona.setApellidos(HelperString.cambioCharsetToUTF8(data
					.getApell1() + " " + data.getApell2()));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			persona.setFechaNacimiento(formatter.parse(data.getFec_nac()));
			persona.setNombres(HelperString.cambioCharsetToUTF8(data.getNombres()));
			persona.setIdentificador(rut);
		} catch (Exception e) {
			throw new ErrorIntegracionException(e.getMessage(), e);
		}
		
		return persona;
	}

}
