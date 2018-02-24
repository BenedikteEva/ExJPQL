/*
ORM er en programmerings teknik der bruges til at konvertere data mellem objektorienteret
programmeringsprog som java eller c# og relationelle databaser

Rationalet bag at bruge orm er blandt andet at man sparer tid. F.eks. kunne jeg køre et script i min database
og så få netbeans til at generere de forskellige klasser og en del metoder. Også den omvendte vej
kan man slippe for at lave databasen da man genere den udfra ens entitets og enumklasser. 

Derudover som altid når man bruger et kode framework som er lavet og testet og forfinet af
proffesionelle udviklere er det noget nemmmere at teste sine programmer fordi man allerede
har fået gjort tingene nemmere. 

Man bevæger sig stadig i det objectorienterede programmeringsparadigme som for mig er nemmere 
end f.eks. tråd programmering. 

Desværre kan det at bruge orm kan nogle gange give en dårligt designet database 
f.eks. når man bruger en single table annotation og man så giver køb på normalformerne. (men kun hvis man har 
performance issues). 

Det kan være svært at få nedarvning og polymorphisme udtrykt i orm og databasen 

 */
package test;

import entity.Semester;
import entity.Student;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ejer
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExJPQLPU");
        EntityManager em = emf.createEntityManager();
        Semester sem = new Semester();
        Student stud = new Student();

        try {
            em.getTransaction().begin();

            //1. Find all Students in the system
            Collection<Student> studs = sem.getStudentCollection(em);
            System.out.println("All Students in the system: " + studs);

            // 2. Find all Students in the System with the first name Anders
            Collection<Student> anders = sem.getAllStudentsWithFirstName(em, "Anders");
            System.out.println("Students in the System with the first name Anders: " + anders);

            //3. Insert a new Student into the system dynamic JPQL doesn't have insertstatements so i 
            //use a native in my case mysql statement instead. 
            Query q2 = em.createNativeQuery("insert into Student (firstname, lastname) values(?,?)");
            q2.setParameter(1, "Nora");
            q2.setParameter(2, "Malkeko");
            q2.executeUpdate();
            em.getTransaction().commit();
            // checking to see if it worked Could have used the same method as with Anders
            em.getTransaction().begin();
            Query q3 = em.createNamedQuery("Student.findByFirstname");
            q3.setParameter("firstname", "Nora");
            Collection<Student> nora = q3.getResultList();
            System.out.println("Students in the System with the first name Nora: " + nora);

            // 4. Assign a new student to a semester Hmmm det er vist ikke så nemt fordi det er et semester og ikke en int der ligger der
            // tror man er nødt til at gøre det ved nativemetoden. 
            Query q4 = em.createNativeQuery("UPDATE Student  SET currentsemester_Id=3 WHERE firstname= 'Nora'");

            q4.executeUpdate();
            em.getTransaction().commit();

            //Og den vil ikke opdatere Student listen med det nye semesterid hos Nora men det er opdateret i databasen
            em.getTransaction().begin();

            Query q4a = em.createNamedQuery("Student.findByFirstname");
            q4a.setParameter("firstname", "Nora");
            Collection<Student> noraAfter = q4a.getResultList();
            System.out.println("Students in the System with the first name Nora: " + noraAfter);
            em.getTransaction().commit();

            // 5. find students med lastname 'And*
            Query q5 = em.createNamedQuery("Student.findByLastname");
            q5.setParameter("lastname", "And");
            Collection<Student> and = q5.getResultList();
            System.out.println("Students in the System with last name And: " + and);

            //6. Find the total number of students, for a semester given the semester name as parameter.
            String name = "'CLdat-b14e'";
            Query q6 = em.createQuery("SELECT COUNT(stud) FROM Student stud JOIN stud.currentsemesterId sem WHERE sem.semestername =" + name);
            System.out.println("Number of students in CLdatb14e=" + q6.getSingleResult());
            
            // 7. Find the total number of students in all semesters.
            Query q7 = em.createQuery("SELECT COUNT(stud) FROM Student stud WHERE stud.currentsemesterId!=null");
            System.out.println("Number of students in all =" + q7.getSingleResult());
            
            // 8. Find the teacher who teaches the most semesters. Den burde have været piece of cake but wasn't 
            Query q8 = em.createQuery("Select teach FROM Teacher teach order by size(teach.semesterCollection)desc");
            System.out.println("Winning teacher is:  "+q8.getResultList().get(0));
        } finally {
            em.close();

        }
        System.out.println("The Rationale behind ORM ");

    }
}

