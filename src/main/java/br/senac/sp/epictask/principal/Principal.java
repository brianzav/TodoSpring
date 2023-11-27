package br.senac.sp.epictask.principal;

import br.senac.sp.epictask.model.DadosEpisodio;
import br.senac.sp.epictask.model.DadosSerie;
import br.senac.sp.epictask.model.DadosTemporada;
import br.senac.sp.epictask.model.Episodio;
import br.senac.sp.epictask.services.ConsumoAPI;
import br.senac.sp.epictask.services.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=b7406d1d";

    public void exibeMenu(){
        System.out.println("Digite o nome da serie para busqueda: ");
        var nomeSerie = input.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
             json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season=" + i + API_KEY);
             DadosTemporada dadosTemporada = conversor.obterDados(json,DadosTemporada.class);
             temporadas.add(dadosTemporada);
        }
//        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.printf(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        /*System.out.println("Melhor episodio: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(1)
                .forEach(System.out::println);*/

        /*System.out.println("\n Top 5 - episodios: ");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(10)
                .forEach(System.out::println);*/

        System.out.println();
        System.out.println();

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                .map(d-> new Episodio(t.temporada(), d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);



















    }
}
