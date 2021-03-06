/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lei_200221058_200221069;


/**
 * Área do professor
 * @author Lucas Freixieiro e Liliana Santos
 * @version 1.0.0
 */
public class TeacherMenu {
    
    private LessonDB lessonDB;
    private UserDB userDB;
    private UserDB attendances;
    
    private InputReader reader;
    
    private Lesson lesson;
    private User user;
    private boolean flag;
    private int numberOfLessons;
    private int numberOfStudents;

    /**
     * Construtor
     * @param lessonDB Registo de lições
     * @param userDB Registo de utilizadores
     */
    public TeacherMenu(LessonDB lessonDB, UserDB userDB) {
        reader = new InputReader();
        this.lessonDB = lessonDB;
        this.userDB = userDB;
        numberOfLessons = 0;
        numberOfStudents = 0;
    }
    
    /**
     * Método que corre o menu e todas as funções relacionadas com o menu do professor
     * @param numberID número de utilizador
     * @param classroom Sala de aula
     */
    public void run(String numberID, Classroom classroom){
        user = userDB.getUser(numberID);
        if(user != null && classroom != null){
            int option;
            int capacity = classroom.getCapacity();
            attendances = new UserDB();

            showTeacherMenu();

            option = reader.getOption("");
            while(option != 0 || flag){
                switch(option){
                    case 1:{
                        setAttendances(capacity);
                        break;
                    }
                    case 2:{
                        if(!flag)
                            startLesson(classroom);
                        else
                            System.out.println("Aula já iniciada");
                        break;
                    }
                    case 3:{
                        endLesson();
                        break;
                    }
                    case 4:{
                        if(!flag)
                            listLessons();
                        else
                            System.out.println("Indisponivel");
                        break;
                    }
                    case 5:{
                        if(!flag)
                            listAttendances();
                        else
                            System.out.println("Indisponivel");
                        break;
                    }
                    default:{
                        System.out.println("Opção não reconhecida");
                    }
                }

                //De forma a que a informação não apareça de seguida
                //ao utilizador é pedido que ele insira um enter para continuar o programa
                reader.getText("Prima Enter para continuar");

                //Demonstração do menu e pedido de nova opção
                showTeacherMenu();
                option = reader.getOption("");
            }
        }
    }
    
    /**
     * Menu do Professor
     */
    public void showTeacherMenu(){
       System.out.println("1 - Registar Presenças");
       System.out.println("2 - Iniciar aula");
       System.out.println("3 - Terminar aula");
       System.out.println("4 - Registo de aulas");
       System.out.println("5 - Presenças por aula");
       System.out.println("0 - Sair");
   }
    
   /**
    * Regista as presenças não podendo ultrapassar a capacidade do parametro
    * @param capacity capacidade da sala de aula
    */
   public void setAttendances(int capacity){
       int option;
       
       showAttendanceMenu();
       option = reader.getOption("Opção");
       
       while(option!=0 && numberOfStudents<capacity){
           switch(option){
               case 1:{
                   User student = userDB.getUser(reader.getUserID("Número de Aluno"));
                   if(student != null && (verifyAttendances(student) == false)){
                       attendances.addUser(student);
                       numberOfStudents++;
                   } 
                   else
                       System.out.println("Aluno inexistente ou já inscrito");
               }
           }
           showAttendanceMenu();
           option = reader.getOption("Opção");
       }
   }
   
   /**
    * Menu para as presenças
    */
   public void showAttendanceMenu(){
       System.out.println("1 - Marcar presença");
       System.out.println("0 - Sair");
   }
   
   /**
    * Iniciar aula
    * @param classroom sala de aula
    */
   public void startLesson(Classroom classroom){
       lesson = new Lesson(numberOfLessons, user, classroom);
       lesson.startLesson(attendances);
       lessonDB.addLesson(lesson);
       flag = true;
       numberOfLessons++;
   }
   
   /**
    * Terminar aula
    */
   public void endLesson(){
       if(flag){
           lesson.endLesson(attendances);
           flag = false;
           numberOfStudents = 0;
       }
       else
           System.out.println("Aula sem inicio");
   }
   
   /**
    * Listar as presenças
    * Esta opção é feita através de um menu onde serão disponibilizadas as aulas com o seu respetivo número de aula
    */
   public void listAttendances(){
       System.out.println("1 - Ver Aulas");
       System.out.println("0 - Sair");
       
       int option = reader.getOption("Opção");
       
       while(option!=0){
           if(option == 1){
               listLessons();
               int lesson = reader.getOption("Opção");
               lessonDB.listAttendances(lesson);
           }  
           System.out.println("1 - Ver Aulas");
           System.out.println("0 - Sair");
           option = reader.getOption("Opção");
       }
   }
   
   /**
    * Lista as aulas já realizadas
    */
   public void listLessons(){
        lessonDB.listLessons();
   }
   
   /**
    * Verifica se o aluno que está ser inscrito já foi inscrito
    * @param student Utilizador
    * @return Verdadeiro se já, falso se não
    */
   public boolean verifyAttendances(User student){
       User[] users = attendances.getUsers();
       for(int i=0; i<numberOfStudents; i++){
           if(users[i].equals(user)){
               return true;
           }
       }
       return false;
   }
}
