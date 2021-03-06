/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lei_200221058_200221069;

/**
 * Área de administração
 * @author Lucas Freixieiro e Liliana Santos
 * @version 1.0.0
 */
public class AdministrationMenu {

    //Atributos
    InputReader reader;
    private ClassroomDB classrooms; //Será que isto devia de ficar aqui?
    private UserDB users;

    /**
     * Construtor
     * @param users Registo de utilizadores
     * @param classrooms Registo de salas de aula
     */
    public AdministrationMenu(UserDB users, ClassroomDB classrooms) {
        reader = new InputReader();
        this.users = users;
        this.classrooms = classrooms;
    }

    /**
     * Carrega o menu do administrador e executa a opção escolhida
     */
    public void run() {
        int option; //Variável auxiliar para guardar temporáriamente a escolha do administrador
        showAdministrationMenu(); //Demonstra o menu ao administrador
        option = reader.getOption("");

        //Enquanto a opção for diferente de 0 o programa continuará a correr
        while (option != 0) {

            //Ponte para os métodos correspondentes às opções
            switch (option) {
                case 1:
                    newUser();
                    break;
                case 2:
                    removeUser();
                    break;
                case 3:
                    showUsers();
                    break;
                case 4:
                    newClassroom();
                    break;
                case 5:
                    removeClassroom();
                    break;
                case 6:
                    showClassrooms();
                    break;
                default:
                    System.out.println("Opção não reconhecida");
                    break;
            }

            //De forma a que a informação não apareça de seguida
            //ao utilizador é pedido que ele insira um enter para continuar o programa
            reader.getText("Prima Enter para continuar");

            //Demonstração do menu e pedido de nova opção
            showAdministrationMenu();
            option = reader.getOption("");
        }
    }

    /**
     * Menu do administrador
     */
    public void showAdministrationMenu() {
        System.out.println("\tSistema de rastreio de contactos em sala de aula");
        System.out.println("\t\tÁrea de administração");
        System.out.println("Insira a sua opção:");
        System.out.println("1 - Criar utilizador");
        System.out.println("2 - Eliminar utilizador");
        System.out.println("3 - Mostrar lista de utilizadores");
        System.out.println("4 - Criar sala de aula");
        System.out.println("5 - Eliminar sala de aula");
        System.out.println("6 - Mostar lista de salas");
        System.out.println("0 - Sair");
    }

    /**
     * Adicionar um novo utilizador ao objeto users
     */
    public void newUser() {
        String numberID;
        UserState status;

        numberID = reader.getUserID("Número de Utilizador");
        if (users.verifyUser(numberID) == false) {
            status = reader.getUserState("Estado do Utilizador");

            User user = new User(numberID, status);
            users.addUser(user);
            System.out.println("Utilizador adicionado");
        } else {
            System.out.println("Utilizador já existente");
        }
    }

    /**
     * Remove um utilizador guardado
     */
    public void removeUser() {
        String numberID;
        numberID = reader.getUserID("Número de Utilizador");
        User user = users.getUser(numberID);
        if (user != null) {
            users.removeUser(user);
            System.out.println("Utilizador removido");
        }
    }

    /**
     * Mostra todos os utilizadores registados
     */
    public void showUsers() {
        for (User user : users.getUsers()) {
            System.out.println(user.getUserID());
        }
    }

    /**
     * Cria uma nova sala de aula e guarda-a no objeto classrooms
     */
    public void newClassroom() {
        String name;
        int capacity;

        name = reader.getText("Nome da sala");
        if (classrooms.verifyClassroom(name) == false) {
            capacity = reader.getOption("Capacidade da sala"); //Provavelmente mudar o nome do método será melhor

            Classroom classroom = new Classroom(name, capacity);
            classrooms.addClassrooms(classroom);
            System.out.println("Sala criada");
        } else {
            System.out.println("Sala de aula existente");
        }

    }

    /**
     * Remove uma sala de aula existente
     */
    public void removeClassroom() {
        String name;
        name = reader.getText("Nome da sala");
        Classroom classroom = classrooms.getClassroom(name);
        if (classroom != null) {
            classrooms.removeClassroom(classroom);
            System.out.println("Sala removida");
        }
    }

    /**
     * Mostra as salas de aula
     */
    public void showClassrooms() {
        for (Classroom classroom : classrooms.getClassrooms()) {
            System.out.println(classroom.getName());
        }
    }
}
