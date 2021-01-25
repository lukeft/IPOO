/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lei_200221058_200221069;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Estatísticas Diárias
 *
 * @author Lucas Freixieiro e Liliana Santos
 * @version 1.0.0
 */
public class Statistics {

    private UserDB users;
    private int isolation;
    private int normal;
    private int infected;
    private LocalDate date;
    /**
     * Construtor das Estatisticas
     *
     * @param users Registo de utilizadores
     */
    public Statistics(UserDB users) {
        this.users = users;
        isolation = 0;
        normal = 0;
        infected = 0;
        date = LocalDate.now();
    }

    /**
     * Método que corre as estatisticas
     */
    public void run(ArrayList<Statistics> weeklyStatistics) {

        isolation = 0;
        normal = 0;
        infected = 0;

        ArrayList<Statistics> weekStats; 
        weekStats = weeklyStatistics;

        calc();

        if ((infected + isolation + normal > 0)) {
            int percentageOfInfected = infected * 100 / (infected + isolation + normal);
            int percentageOfIsolation = isolation * 100 / (infected + isolation + normal);
            int percentageOfNormal = normal * 100 / (infected + isolation + normal);

            System.out.println("Infectados: " + infected);
            System.out.println("Em isolamento: " + isolation);
            System.out.println("Em contínuo: " + normal);

            System.out.println("Infectados: ");

            for (int i = 0; i < percentageOfInfected; i++) {
                System.out.print("|");
            }
            System.out.print(" " + percentageOfInfected + "%");
            System.out.println("\nEm Isolamento:");

            for (int i = 0; i < percentageOfIsolation; i++) {
                System.out.print("|");
            }
            System.out.print(" " + percentageOfIsolation + "%");
            System.out.println("\nEm Contínuo:");

            for (int i = 0; i < percentageOfNormal; i++) {
                System.out.print("|");
            }
            System.out.print(" " + percentageOfNormal + "%");
            System.out.println("");
        } else {
            System.out.println("Não existem utilizadores");
        }

        if(weekStats.size() > 0){
            System.out.println("\n\nTendência dos últimos 7 dias");
            for(Statistics statistic : weekStats){
                System.out.println("Dia " + (weekStats.indexOf(statistic) + 1) + ": ");
                System.out.println("Infetados: " + statistic.getInfected());
                System.out.println("Isolamentos: " + statistic.getIsolation());
                System.out.println("Normais: " + statistic.getNormal());
                System.out.println("\n");
            }
    
            System.out.println("Diferenças entre o dia: " + weekStats.get(0).getDate() + " - " + weekStats.get(weekStats.size() - 1).getDate());
            System.out.println("Infetados: " + difference(weekStats.get(weekStats.size() - 1).getInfected(), weekStats.get(0).getInfected()));
            System.out.println("Isolamentos: " + difference(weekStats.get(weekStats.size() - 1).getIsolation(), weekStats.get(0).getIsolation()));
            System.out.println("Normais: " + difference(weekStats.get(weekStats.size() - 1).getNormal(), weekStats.get(0).getNormal()));
        }
        else{
            System.out.println("Não há estatísticas dos últimos 7 dias");
        }
        
    }

    public void calc(){
        for (User user : users.getUsers()) {
            if (null != user.getUserState()) {
                switch (user.getUserState()) {
                    case INFECTED:
                        infected++;
                        break;
                    case ISOLATION:
                        isolation++;
                        break;
                    case NORMAL:
                        normal++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public int getIsolation() {
        return isolation;
    }

    public void setIsolation(int isolation) {
        this.isolation = isolation;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getInfected() {
        return infected;
    }

    public void setInfected(int infected) {
        this.infected = infected;
    }

    public int difference(int firstValue, int lastValue){
        return firstValue - lastValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}