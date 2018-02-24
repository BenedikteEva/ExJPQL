/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ejer
 */
@Entity
@Table(name = "semester")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semester.findAll", query = "SELECT s FROM Semester s")
    , @NamedQuery(name = "Semester.findById", query = "SELECT s FROM Semester s WHERE s.id = :id")
    , @NamedQuery(name = "Semester.findByDescription", query = "SELECT s FROM Semester s WHERE s.description = :description")
    , @NamedQuery(name = "Semester.findByName", query = "SELECT s FROM Semester s WHERE s.semestername = :name")})
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SEMESTERNAME")
    private String semestername;
    @ManyToMany(mappedBy = "semesterCollection")
    private Collection<Teacher> teacherCollection;
    @OneToMany(mappedBy = "currentsemesterId")
    private Collection<Student> studentCollection;

    public Semester() {
    }

    public Semester(Long id, String description, String semestername) {
        this.id = id;
        this.description = description;
        this.semestername = semestername;
    }
    

    public Semester(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemestername() {
        return semestername;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername;
    }

    @XmlTransient
    public Collection<Teacher> getTeacherCollection(Query q) {
        teacherCollection=q.getResultList();
        return teacherCollection;
    }

    public void setTeacherCollection(Collection<Teacher> teacherCollection) {
        this.teacherCollection = teacherCollection;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection(EntityManager em) {
    //Find all Students in the system
      studentCollection = em.createNamedQuery("Student.findAll").getResultList();
        
        return studentCollection;
    }

  public Collection<Student> getAllStudentsWithFirstName(EntityManager em, String firstName) {
        Query q1 = em.createNamedQuery("Student.findByFirstname");
        q1.setParameter("firstname", firstName);
        Collection<Student> allStudentsWithName = q1.getResultList();
        return allStudentsWithName;

    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semester)) {
            return false;
        }
        Semester other = (Semester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Semester[ id=" + id + " ]";
    }

}
