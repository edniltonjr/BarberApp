package com.example.juniorsantos.barberapp;

import android.util.Log;

import javax.net.ssl.HttpsURLConnection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Junior Santos on 13/08/2017.
 */

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario){
        URL url;
        HttpURLConnection connection = null;

        try {

            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //tipo de comunicação

            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length)); //NUMERO DE BYTES ENVIADOS NA CONEXAO

            connection.setRequestProperty("Coneten-Language", "pt-BR");

            connection.setUseCaches(false); //DESATIVAR OS CACHES DO NAVEGADOR
            connection.setDoInput(true); //ATIVAR ENTRADA DE DADOS
            connection.setDoOutput(true); //ATIVAR SAIDA DE DADOS

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream()); // solicitacao /ARMAZENAR DADOS QUE FORAM SAIDOS
            dataOutputStream.writeBytes(parametrosUsuario); //ESCREVER OS DADOS
            dataOutputStream.flush();
            dataOutputStream.close();

            InputStream inputStream = connection.getInputStream();         //OBTER INFORMACAO
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")) ;//pegar os dados e por eles no buffer
            String linha ; //armazenar cada linha
            StringBuffer resposta  = new StringBuffer(); //juntar informacoes

            while((linha = bufferedReader.readLine()) != null){
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();

            return resposta.toString(); //retornar resposta como string

        } catch (Exception erro){

            Log.e("Conexao", "postDados: ",erro );
            return null;

        } finally {

            if(connection != null){
                connection.disconnect();
            }

        }

    }







}
