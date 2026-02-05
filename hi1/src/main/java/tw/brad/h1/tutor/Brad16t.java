package tw.brad.h1.tutor;

import java.util.List;
import java.util.Scanner;

import tw.brad.h1.dao.SCDao;
import tw.brad.h1.entity.Course;
import tw.brad.h1.entity.Student;

public class Brad16t {

	public static void main(String[] args) {
		SCDao dao = new SCDao();
		Student s1;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Student ID:");
		long sid = scanner.nextLong();
		
		s1 = dao.getStudentById(sid);
		System.out.printf("Welcome, %s", s1.getSname());
		
		List<Course> courses = dao.getAllCourse();
		
		long cid,tmp;
		boolean i = true;
		
		do {
			for (Course course : courses) {
				System.out.printf("%d. %s\n", course.getId(), course.getCname());
			}
			System.out.println("-----");
			System.out.println("Which? ");
			
//			String inputStr = scanner.nextLine();
//			cid = Long.parseLong(inputStr);
			cid = scanner.nextLong(); 
			scanner.nextLine();
			s1.addCourse(dao.getCourseById(cid));
			dao.update(s1);
			courses.remove((int)cid-1);
			System.out.println("======");
			System.out.println("Continued? Y / N");
			if (scanner.nextLine().equalsIgnoreCase("N")) i = false;
		} while (i);
		
		
	}

}
