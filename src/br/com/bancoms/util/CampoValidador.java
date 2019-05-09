package br.com.bancoms.util;

public class CampoValidador
{
    public static final int CAMPO_VAZIO = 1;
    public static final int CAMPO_NUMERAL_COM_PALAVRAS = 2;
    public static final int CAMPO_VALIDO = 3;

    public static int validarCampoNumero(String numeroCampo)
    {

	if (numeroCampo.isEmpty())
	{
	    return CAMPO_VAZIO;
	} else
	{
	    try
	    {
		int numero = Integer.parseInt(numeroCampo);
	    } catch (Exception e)
	    {
		return CAMPO_NUMERAL_COM_PALAVRAS;
	    }
	}

	return CAMPO_VALIDO;

    }

    public static int validarCampoTexto(String texto)
    {

	if (texto.isEmpty())
	{
	    return CAMPO_VAZIO;
	}

	return CAMPO_VALIDO;

    }
}
