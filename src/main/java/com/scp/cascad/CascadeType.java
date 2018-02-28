
/**
 * @author Saurabhee
 * 
 * Delete orphan
 */

package com.scp.cascad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "Stud_info")
class Student {
	@Id
	// @GeneratedValue
	private int id;
	private String name;


	@OneToMany
  // @Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN,org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Cascade({ org.hibernate.annotations.CascadeType.DELETE, org.hibernate.annotations.CascadeType.SAVE_UPDATE })

	private List<Project> project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	public Student(int id, String name, List<Project> project) {
		super();
		this.id = id;
		this.name = name;
		this.project = project;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", project=" + project + "]";
	}

}

@Entity
@Table(name = "project_info")
class Project {
	@Id
	// @GeneratedValue

	private int projetid;

	private String pname;

	public int getProjetid() {
		return projetid;
	}

	public void setProjetid(int projetid) {
		this.projetid = projetid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Project [projetid=" + projetid + ", pname=" + pname + "]";
	}

	public Project(int projetid, String pname) {
		super();
		this.projetid = projetid;
		this.pname = pname;
	}

}

public class CascadeType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Project p1 = new Project(100, "spring1");
		Project p2 = new Project(200, "spring2");
		Project p3 = new Project(300, "spring3");
		Project p4 = new Project(400, "spring4");
		Project p5 = new Project(500, "spring5");
		Project p6 = new Project(600, "spring6");
		Project p7 = new Project(700, "spring7");
		Project p8 = new Project(800, "spring8");

		List<Project> list = new ArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		Student s1 = new Student(10, "sauru", list);
		System.out.println("Before Remove");
		
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		s1.setProject(list);

		/*
		 * System.out.println("After remove"); s1.getProject().remove(p1);
		 * s1.getProject().remove(p2); s1.getProject().remove(p3);
		 */

		
		session.save(s1);
		
		
		s1.getProject().remove(p1);
		s1.getProject().remove(p2);
		s1.getProject().remove(p3);
		session.flush();
		tr.commit();
		// session.flush();

		session.close();

	}

}
