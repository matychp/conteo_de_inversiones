/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conteo_de_inversiones_tsb;

/**
 *  Segunda version de una clase para representar un nodo para una lista simple generica.
 *  Se usa parametrizacion de clases (generics) para controlar automaticamente el contenido homog�neo.
 *  @author Ing. Valerio Frittelli
 *  @version Agosto de 2008
 */
public class Node < E extends Comparable>
{
   private E info;
   private Node <E> next;
   
   /**
    *  Constructor por defecto. 
    */
   public Node ( )
   {
   }
   
   /**
    *  Crea un nodo incializando todos los atributos en base a parametros 
    */
   public Node (E x, Node <E> p)
   {
     info = x;
     next = p;
   }
   
   /**
    *  Accede a la direccion del sucesor
    *  @return la direccion del nodo sucesor
    */
   public Node <E> getNext()
   {
     return next;
   }
   
   /**
    * Cambia la direccion del sucesor
    * @param p direccion del nuevo sucesor
    */
   public void setNext(Node <E> p)
   {
     next = p;
   }
   
   /**
    *  Accede al valor del info
    *  @return el valor del info
    */
   public E getInfo()
   {
     return info;
   }
   
   /**
    * Cambia el valor del info
    * @param p nuevo valor del info
    */
   public void setInfo(E p)
   {
     info = p;
   }

   /**
    * Convierte el contenido del nodo en String
    * @return el contenido del nodo convertido en String
    */
   public String toString()
   {
     return info.toString();   
   }
}


