package com.stackroute.keepnote.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.stackroute.keepnote.model.Note;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
// * */
@Repository("NoteDAO")
@Transactional
@Component
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@PersistenceContext
	private EntityManager em;

	public NoteDAOImpl(){ }
	public NoteDAOImpl(EntityManager em) {
		this.em=em;
	}
	/*
	 * Save the note in the database(note) table.
	 */
	@Transactional
	public boolean saveNote(Note note) {
		//session=sessionFactory.getCurrentSession();
		em.persist(note);
		//session.getTransaction().commit();
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		//Query query= (Query) sessionFactory.getCurrentSession().createSQLQuery("delete from Note where noteId= :noteId");
		//((NativeQuery) query).setString("noteId", String.valueOf(noteId));
		//((NativeQuery) query).executeUpdate();
		//session=sessionFactory.getCurrentSession();
		em.remove(getNoteById(noteId));
		//session.delete(getNoteById(noteId));
		return true;
	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	@SuppressWarnings("unchecked")
	public List<Note> getAllNotes() {
		//CriteriaBuilder builder = session.getCriteriaBuilder();

// Create CriteriaQuery
		//CriteriaQuery<Note> criteria = builder.createQuery(Note.class);
		//Root<Note> root =criteria.from(Note.class);
		//criteria.select(root);
		//Query<Note> q = session.createQuery(criteria);
		//List<Note> list = q.getResultList();
		CriteriaQuery<Note> criteriaQuery = em.getCriteriaBuilder().createQuery(Note.class);
		criteriaQuery.from(Note.class);
//
		List<Note> list = em.createQuery(criteriaQuery).getResultList();
		//Criteria criteria=session.createCriteria(Note.class);
		System.out.println("list ofdd ");
		return list;

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		return em.getReference(Note.class,noteId);
	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		Note note1=getNoteById(note.getNoteId());
		if(note1!=null){
			note1.setNoteTitle(note.getNoteTitle());
			note1.setNoteContent(note.getNoteContent());
			note1.setNoteStatus(note.getNoteStatus());
			return true;
		}
		return false;
	}

}
