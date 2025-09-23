package model;

/**
 * Clase que proporciona acceso a los datos de usuarios almacenados en archivos
 * Utiliza la clase ImplementsFIle para la implementación real
 * @author 2dami
 */
public class Factory_FILES {
     
    public DAO abrirImplementacion(){
        DAO dao = new ImplementsFile();
         
         return dao;
    }
}
