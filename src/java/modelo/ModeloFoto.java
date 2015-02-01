package modelo;

import hibernate.Foto;
import hibernate.Inmueble;
import hibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloFoto {
    public static List<Foto> get() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Inmueble";
        Query q = session.createQuery(hql);
        List<Foto> list = q.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    
    public static Foto get(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Foto o = (Foto) session.get(Foto.class, Integer.parseInt(id));

        session.getTransaction().commit();
        session.close();
        
        return o;
    }
    
    public static List<Foto> get(Inmueble inmueble) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "select f from Foto as f where f.inmueble=:inmueble";
        Query q = session.createQuery(hql).setParameter("inmueble", inmueble);
        List<Foto> list = q.list();

        session.getTransaction().commit();
        session.close();
        
        return list;
    }
    
    public static void delete(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Foto o = (Foto) session.load(Foto.class, Integer.parseInt(id));
        session.delete(o);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Foto i){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(i);
        
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Foto i){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.update(i);

        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
