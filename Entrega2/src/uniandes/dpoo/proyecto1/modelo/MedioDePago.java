package uniandes.dpoo.proyecto1.modelo;

import java.time.LocalDate;

public class MedioDePago 
{
	private String tipo;
	private String numero;
	private LocalDate vencimiento;
	
	public MedioDePago(String tipo,String numero,LocalDate vencimineto)
	{
		this.tipo = tipo;
		this.numero = numero;
		this.vencimiento = vencimiento;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public String getNumero()
	{
		return numero;
	}
	public LocalDate getVencimiento()
	{
		return vencimiento;
	}
	
	public static void bloquearTarjeta()
	{
		System.out.println("Se va a realizar un bloqueo sobre su tarjeta de crédito que es desactivada cuando se entrega de nuevo en una sede de la empresa");

	}
}
