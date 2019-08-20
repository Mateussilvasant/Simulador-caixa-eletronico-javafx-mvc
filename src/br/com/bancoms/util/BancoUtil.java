package br.com.bancoms.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BancoUtil {

    public static String getDataAtual() {
        Date hoje = new Date();
        String formato = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat f = new SimpleDateFormat(formato, Locale.getDefault());
        return f.format(hoje);
    }

}
