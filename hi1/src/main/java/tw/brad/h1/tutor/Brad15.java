package tw.brad.h1.tutor;

import tw.brad.h1.dao.SCDao;
import tw.brad.h1.entity.Course;
import tw.brad.h1.entity.Student;

public class Brad15 {

	public static void main(String[] args) {
		SCDao dao = new SCDao();
		dao.save(new Course("Java"));
		dao.save(new Course("Tomcat"));
		dao.save(new Course("Hibernate"));
		dao.save(new Course("JDBC"));
		dao.save(new Course("Spring"));
	}

}
