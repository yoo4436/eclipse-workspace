package tw.brad.h1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.brad.h1.entity.Member;
import tw.brad.h1.utils.HibernateUtil;

public class MemberDao {
	
	public void addMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public void delMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.remove(member);
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public void updateMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.merge(member); // update / add
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public Member findById(long id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(Member.class, id);
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return null;
	}
	
	public List<Member> findByAll(){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			//HQL : select * from Member(類別名稱)
			String hql = "from Member";
			Query<Member> query = session.createQuery(hql, Member.class);
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return null;
	}
	
	public List<Member> findByLike(String key){
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			//HQL : select * from Member(類別名稱) where email like ? or name like ?
			String hql = "from Member m where m.email like :key or name like :key ";
			Query<Member> query = session.createQuery(hql, Member.class);
			query.setParameter("key", "%" + key + "%");
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			
		}
		return null;
	}
}
