package com.stackroute.keepnote.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
@Table(name="NOTE")
public class Note {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable = false)
	private int noteId;
	@Column(name = "NOTETITLE",nullable = false)
	private String noteTitle;
	@Column(name = "NOTECONTENT",nullable = false)
	private String noteContent;
	@Column(name = "NOTESTATUS",nullable = false)
	private String noteStatus;
	@Column(name = "DT",nullable = false)
	private LocalDateTime dateTime;
	public Note() {
		this.dateTime=LocalDateTime.now();

	}

	public Note(int i, String string, String string2, String string3, LocalDateTime localDate) {
		this.noteId=i;
		this.noteTitle=string;
		this.noteContent=string2;
		this.noteStatus=string3;
		this.dateTime=localDate;
		System.out.println("in const"+noteId+noteTitle+noteContent+noteStatus+dateTime);
	}

	public int getNoteId() {

		return noteId;
	}

	public String getNoteTitle() {

		return noteTitle;
	}

	public String getNoteContent() {

		return noteContent;
	}

	public String getNoteStatus() {

		return noteStatus;
	}

	public void setNoteId(int parseInt) {
		this.noteId=parseInt;
	}

	public void setNoteTitle(String parameter) {
		this.noteTitle=parameter;
	}

	public void setNoteContent(String parameter) {
		this.noteContent=parameter;
	}

	public void setNoteStatus(String parameter) {
		this.noteStatus=parameter;
	}

	public void setCreatedAt(LocalDateTime now) {
		this.dateTime=now;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		if (noteId != other.noteId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + noteId + ", name=" + noteTitle + ", joiningDate="
				+ noteContent + ", salary=" + noteStatus + "]";
	}

}
