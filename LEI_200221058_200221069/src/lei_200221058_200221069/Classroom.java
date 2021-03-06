/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lei_200221058_200221069;

/**
 * Informação acerca de uma sala de aula
 * @author Lucas Freixieiro e Liliana Santos
 * @version 1.0.0
 */

//Classe para as salas de aula
/*
Requerimentos:
-> Capacidade (Lugares disponiveis na sala)
-> Nome (Nome da sala)
 */
public class Classroom {

    private int capacity;
    private String name;

    /**
     * Construtor
     * @param name Nome da sala de aula
     * @param capacity Capacidade da sala de aula
     */
    public Classroom(String name, int capacity) {
        if (capacity > 0 && name != null && !name.isEmpty()) {
            this.capacity = capacity;
            this.name = name;
        } else {
            System.out.println("Campos inválidos.\nPor favor insira novamente!");
        }

    }

    /**
     * 
     * @return Capacidade da sala de aula
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Altera a capacidade da sala de aula
     * @param capacity Capacidade da sala de aula
     */
    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Campo inválido.\nPor favor insira uma capacidade acima de 0.");
        }
    }

    /**
     * 
     * @return Nome da sala de aula
     */
    public String getName() {
        return name;
    }

    /**
     * Altera o nome da sala de aula
     * @param name Nome da sala de aula
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Campo inválido.\nPor favor insira um nome válido!");
        }
    }

}
