package uniandes.dpoo.proyecto1.modelo;

public class LicenciaConduccion
{
	private String numeroLicencia;
	private String paisExpedicion;
	private String fechaVencimiento;
	
	public LicenciaConduccion(String numeroLicencia,String paisExpedicion,
			String fechaVencimiento)
	{
		this.numeroLicencia = numeroLicencia;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimiento;
	}
	
	public String getNumero() {
		return numeroLicencia;
	}
	
	public String getPais() {
		return paisExpedicion;
	}
	
	public String getFecha() {
		return fechaVencimiento;
	}
}