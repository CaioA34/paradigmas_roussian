package org.paradigmas;
/*
Uma loja precisa analisar as vendas realizadas em uma semana. O sistema
possui uma coleção de vendas, e cada venda contém:
    nome do produto;
    categoria;
    quantidade vendida;
    preço unitário;
    nome do vendedor;
A loja precisa gerar algumas informações para entender melhor o desempenho
das vendas.

A loja precisa das seguintes informações:
1. Listar todas as vendas da categoria "Roupas".
2. Calcular o faturamento total da semana.
3. Listar os produtos com total de venda maior que R$ 200,00.
4. Gerar uma lista apenas com os nomes dos produtos vendidos.
5. Calcular o total vendido por vendedor.
6. Encontrar a venda de maior valor total.
7. Ordenar as vendas do maior para o menor valor total.

Parte-1: Solução Imperativa
Implemente todos os itens usando apenas:
for;
if;
variáveis acumuladoras;
listas auxiliares;
mapas auxiliares;
Collections.sort ou sort com Comparator.
Deve-se criar métodos separados para cada operação.


    //---------Solução não funcional
    de métodos (uma sugestão aqui):


*/

import java.util.*;
import java.util.stream.Collectors;

import Classes.Venda;

public class Main {
    public static void main(String[] args) {
        List<Venda> vendas = List.of(
                new Venda("Camiseta Infantil", "Roupas", 10, 29.90, "Ana"),
                new Venda("Short Infantil", "Roupas", 5, 34.90, "Bruno"),
                new Venda("Tênis Infantil", "Calçados", 3, 119.90, "Ana"),
                new Venda("Sandália Infantil", "Calçados", 4, 79.90, "Carlos"),
                new Venda("Boneca", "Brinquedos", 7, 49.90, "Bruno"),
                new Venda("Carrinho", "Brinquedos", 6, 39.90, "Ana"),
                new Venda("Mochila Escolar", "Acessórios", 2, 89.90, "Carlos"),
                new Venda("Boné Infantil", "Acessórios", 8, 24.90, "Ana"),
                new Venda("Vestido Infantil", "Roupas", 4, 69.90, "Bruno"),
                new Venda("Chinelo Infantil", "Calçados", 9, 29.90, "Carlos"));
        System.out.println("totalPorVendedorImperativo: ");
        System.out.println(totalPorVendedorImperativo(vendas));
        System.out.println("\nmaiorVendaImperativo: ");
        System.out.println(maiorVendaImperativo(vendas));
        System.out.println("\nordenarPorTotalImperativo: ");
        List<Venda> result = ordenarPorTotalImperativo(vendas);
        for(Venda venda : result){
            System.out.println(venda);
        }
        System.out.println("\nVenda com categoria roupas Imperativo: ");
        List<Venda> byCategory = filtrarRoupasImperativo(vendas);
        for(Venda venda : byCategory){
            System.out.println(venda);
        }
        System.out.println("\nFaturamento total Imperativo: ");
        System.out.println(calcularFaturamentoImperativo(vendas));

        System.out.println("\nVenda maior que 200 Imperativo: ");
        List<Venda> maiorQue = vendasAcimaDe200Imperativo(vendas);
        for(Venda venda : maiorQue) {
            System.out.println(venda);
        }

        System.out.println("\nNome produtos Imperativo: ");
        List<String> produtos = nomesProdutosImperativo(vendas);
        for(String nome : produtos){
            System.out.println(nome);
        }

        System.out.println("\nfiltrarRoupasFuncional: ");
        System.out.println(filtrarRoupasFuncional(vendas));

        System.out.println("\ncalcularFaturamentoFuncional");
        System.out.println(calcularFaturamentoFuncional(vendas));

        System.out.println("vendasAcimaDe200Funcional");
        System.out.println(vendasAcimaDe200Funcional(vendas));

        System.out.println("nomesProdutosFuncional");
        System.out.println(nomesProdutosFuncional(vendas));

        System.out.println("totalPorVendedorFuncional: ");
        System.out.println(totalPorVendedorFuncional(vendas));
        System.out.println("\nmaiorVendaFuncional: ");
        System.out.println(maiorVendaFuncional(vendas));
        System.out.println("\nordenarPorTotalFuncional: ");
        List<Venda> result2 = ordenarPorTotalFuncional(vendas);
        for(Venda venda : result2){
            System.out.println(venda);
        }
    }

    //solução imperativa
    public static List<Venda> filtrarRoupasImperativo(List<Venda> vendas) {
        List<Venda> result = new ArrayList<>();
        for(Venda venda : vendas){
            if(venda.getCategoria().equals("Roupas")){
                result.add(venda);
            }
        }
        return result;
    }

    public static double calcularFaturamentoImperativo(List<Venda> vendas) {
        double acum = 0;
        for(Venda venda : vendas){
            acum += venda.getTotal();
        }
        return acum;
    }

    public static List<Venda> vendasAcimaDe200Imperativo(List<Venda> vendas) {
        List<Venda> result = new ArrayList<>();
        for(Venda venda : vendas) {
            if (venda.getTotal() > 200) {
                result.add(venda);
            }
        }
        return result;
    }

    public static List<String> nomesProdutosImperativo(List<Venda> vendas) {
        List<String> result = new ArrayList<>();
        for(Venda venda : vendas) {
            result.add(venda.getProduto());
        }
        return result;
    }

    public static Map<String, Double> totalPorVendedorImperativo(List<Venda> vendas) {
        Map<String, Double> map= new HashMap<>();
        for(Venda venda : vendas){
            if(map.containsKey(venda.getVendedor())){
                double anterior = map.get(venda.getVendedor());
                map.put(venda.getVendedor(), anterior + venda.getTotal());
            }
            else{
                map.put(venda.getVendedor(), venda.getTotal());
            }
        }
        return map;
    }

    public static Venda maiorVendaImperativo(List<Venda> vendas) {
        Venda maior = vendas.get(0);
        for(Venda venda : vendas){
            if(maior.getTotal() < venda.getTotal()){
                maior = venda;
            }
        }
        return maior;
    }

    public static List<Venda> ordenarPorTotalImperativo(List<Venda> vendas) {
        List<Venda> result = new ArrayList<>(vendas);
        result.sort(Comparator.comparing(Venda::getTotal).reversed());
        return result;
    }

    //solucao funcional
    public static List<Venda> filtrarRoupasFuncional(List<Venda> vendas) {
        return vendas.stream().filter(venda -> venda.getCategoria().equals("Roupas")).toList();
    }

    public static double calcularFaturamentoFuncional(List<Venda> vendas) {
        return vendas.stream().mapToDouble(Venda::getTotal).sum();
    }

    public static List<Venda> vendasAcimaDe200Funcional(List<Venda> vendas) {
        return vendas.stream().filter(venda -> venda.getTotal() > 200).toList();
    }

    public static List<String> nomesProdutosFuncional(List<Venda> vendas) {
        return vendas.stream().map(Venda::getProduto).toList();
    }
    public static Map<String, Double> totalPorVendedorFuncional(List<Venda> vendas) {
        return vendas.stream().collect(Collectors.groupingBy(
                Venda::getVendedor,
                Collectors.summingDouble(Venda::getTotal)
        ));
    }
    public static Venda maiorVendaFuncional(List<Venda> vendas){
        return vendas.stream().max(Comparator.comparing(Venda::getTotal)).orElse(null);
    }
    public static List<Venda> ordenarPorTotalFuncional(List<Venda> vendas){
        return vendas.stream().sorted(Comparator.comparing(Venda::getTotal)).toList();
    }
}