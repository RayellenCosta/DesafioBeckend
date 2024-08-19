// Projeto Maven

package com.des;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        String url = "https://pt.wikipedia.org/wiki/Intelig%C3%AAncia_artificial";
        System.out.println("A URL de entrada é: " + url);

        String frase = "de inteligência artificial";
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String conteudo = response.body();

            int contagemFrase = contarOcorrencias(conteudo, frase);
            System.out.println("A frase \"" + frase + "\" aparece " + contagemFrase + " vezes no conteúdo da página.");

            contarOcorrenciasPalavras(conteudo, frase);

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocorreu um erro ao tentar acessar o URL: " + e.getMessage());
        }
    }

    public static int contarOcorrencias(String texto, String frase) {
        int contagem = 0;
        int index = 0;
        while ((index = texto.indexOf(frase, index)) != -1) {
            contagem++;
            index += frase.length();
        }
        return contagem;
    }

    public static void contarOcorrenciasPalavras(String texto, String frase) {
        String[] palavras = frase.split("\\s+");
        
        Map<String, Integer> contagemPalavras = new HashMap<>();

        for (String palavra : palavras) {
            int contagem = 0;
            int index = 0;
            while ((index = texto.indexOf(palavra, index)) != -1) {
                contagem++;
                index += palavra.length();
            }
            contagemPalavras.put(palavra, contagem);
        }

        for (Map.Entry<String, Integer> entry : contagemPalavras.entrySet()) {
            System.out.println("A palavra \"" + entry.getKey() + "\" aparece " + entry.getValue() + " vezes no conteúdo da página.");
        }
    }
}
