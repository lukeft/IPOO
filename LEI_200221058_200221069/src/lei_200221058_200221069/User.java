/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lei_200221058_200221069;

import java.util.UUID;
import java.lang.Object;
import java.time.LocalDate;
/**
 *
 * @author Lucas Freixieiro
 */

//Classe que tem como atributos as infos do user
public class User {
    //Identificação do utilizador
    //No caso dos alunos o seu número de aluno
    //No caso dos professores um número mecanográfico
    private String userID; 
    //Estado do utilizador (continuo, infetado ou em isolamento)
    private UserState userState;
    //Lista com os IDs gerados
    private Id[] transmitedIds;
    //Lista com os ID's recebidos
    private Id[] receivedIds;

    //Construtor
    public User(String userID, UserState userState) {
        this.userID = userID;
        this.userState = userState;
        transmitedIds = new Id[0];
        receivedIds = new Id[0];
    }
    //Método para obter o id do utilizador
    //@return String com o número do utilizador
    public String getUserID() {
        return userID;
    }

    //Método para atualizar o atributo do número de utilizador
    //Só atribui o novo número caso o parametro passado tenha o tamanho de 9 caracteres
    //@params setUserID
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    //Método para obter o estado do utilzador
    //@return UserState com o estado do utilziador
    public UserState getUserState() {
        return userState;
    }

    //Método para atualizar o estado do utilizador
    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    //Método para obter os Ids transmitidos
    //@return String[] com o array dos ids transmitidos
    public Id[] getTransmitedIds() {
        return transmitedIds;
    }
    
    //Método para obter os ids transmitidos (gerados) em forma de texto
    public void listTransmitedIds() {
        for(Id transmitedID : transmitedIds){
            System.out.println(transmitedID);
        }
    }
    
    //Método para atualizar o array que contém os ids transmitidos (gerados).
    public void setTransmitedIds() {
        
        
        
        //transmitedIds = ArrayUtils.add(transmitedIds,new Id(LocalDate.now(),UUID.randomUUID().toString()));
    }
    
    //Método para obter os Ids recebidos durante as aulas
    //@return String[]
    public Id[] getReceivedIDs() {
        return receivedIds;
    }
    
    //Método para listar os ids recebidos
    public void listReceivedIds() {
        for(Id receivedID : receivedIds){
            System.out.println(receivedID);
        }
    }

    //Método para atualizar o array dos ids recebidos
    //@params String[] ids
    public void setReceivedIDs(Id[] ids) {
        Id[] newIdArray = new Id[receivedIds.length+1];

        for(int i=0; i<receivedIds.length;i++){
            int j = i+ 1;
            receivedIds = new Id[j];     
            System.arraycopy(receivedIds, 0, newIdArray, 0, receivedIds.length);
            System.arraycopy(newIdArray, 0, receivedIds, 0, receivedIds.length);
            receivedIds[receivedIds.length-1] = ids[i];
        }
        //receivedIds = ArrayUtils.addAll(receivedIds, ids);
    }
    
}
