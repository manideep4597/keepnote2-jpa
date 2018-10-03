package com.stackroute.keepnote.controller;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 
	 * 2. Add a new note which should contain the note id, title, content and status. 
	 * 3. Delete an existing note 
	 * 4. Update an existing note
	 * 
	 */
	@Autowired
	NoteDAO dao;
	public NoteController(NoteDAO dao) {
		this.dao = dao;
	}

	/*
	 * Autowiring should be implemented for the NoteDAO.
	 * Create a Note object.
	 * 
	 */
	@ModelAttribute("note")
	public Note setUpUserForm() {
		return new Note();
	}

	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */
	@RequestMapping(value = "/")
	public String display(ModelMap modelMap){
		List<Note> noteList=dao.getAllNotes();
		modelMap.addAttribute("notes",noteList);
		return "index";

	}

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */

	@RequestMapping(value = "/add" ,method ={ RequestMethod.POST , RequestMethod.GET})
	public String addNewNote(@ModelAttribute("note") Note note, BindingResult bindingResult, ModelMap modelMap){
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("result","Errors in validating");
			return "index";
		}

		List<Note> noteList=dao.getAllNotes();
		for(Note note1:noteList){
			if(note1.getNoteId()==note.getNoteId()) {
				modelMap.addAttribute("result", "ID Already exists. Try another one!");
				return "index";
			}
		}
		System.out.println("sasdf"+note.getNoteTitle()+note.getNoteContent());
		modelMap.addAttribute("note", note);
		if(dao.saveNote(note))
		{
			noteList.add(note);
			modelMap.addAttribute("notes",noteList);
			return "redirect:/";
		}
		return "index";
	}

	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	@RequestMapping(value = "/delete")
	public String deleteNote(@ModelAttribute("note") Note note){
			if(dao.deleteNote(note.getNoteId()))
				return "redirect:/";
			else
				return "index";

		//return "redirect:/";
	}

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	@RequestMapping(value = "/update",method = { RequestMethod.GET, RequestMethod.POST })
	public String updateNote(@Valid Note note, BindingResult bindingResult, ModelMap modelMap){
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("result","Errors in validating");
			return "index";
		}

		List<Note> noteList=dao.getAllNotes();
		dao.UpdateNote(note);
		modelMap.addAttribute("notes",noteList);
		return "redirect:/";
	}

}
