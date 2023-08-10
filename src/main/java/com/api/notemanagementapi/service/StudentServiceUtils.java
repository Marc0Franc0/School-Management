package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Clase con método ademas de los básicos para los estudiantes
@Service
public class StudentServiceUtils {
    @Autowired
    StudentRepository studentRepository;

    public Optional<List<NoteDto>> getNotesById(Long id) {
        //Se retorna una lista de NoteDto ,el cual contiene tres datos -> note,studentLastName y SubjectName
       return studentRepository.findById(id).map(stu->{
       List<NoteDto> notes = stu.getNotes()
               .stream()
               .map(note->{
                   return new NoteDto
                           (note.getNote(),note.getStudent().getId(),note.getSubject().getId());
       }).collect(Collectors.toList());
       return notes;
       });
    }

    public Optional<List<NoteDto>> getNotesByLastname(String lastName) {
        //Se retorna una lista de NoteDto ,el cual contiene tres datos -> note,studentLastName y SubjectName
        return studentRepository.findByLastName(lastName)
                .map(stu->{
                    List<NoteDto> notes = stu.getNotes()
                            .stream()
                            .map(note->{
                                return new NoteDto
                                        (note.getNote(),note.getStudent().getId(),note.getSubject().getId());
                            }).collect(Collectors.toList());
                    return notes;
                });
    }


    public Optional<List<String>> getSubjectsByLastName(String lastName) {
        //Se retorna una lista de string la cual contiene los nombres de las materias
        return studentRepository.findByLastName(lastName)
                .map(stu->{
                    List<String> subjects = stu.getSubjects().stream().map(subject->{
                        return subject.getName();
                    }).collect(Collectors.toList());
                    return subjects;
                });
    }

    public Optional<List<String>> getSubjectsById(Long id) {
        //Se retorna una lista de string la cual contiene los nombres de las materias
        return studentRepository.findById(id)
                .map(stu->{
                    List<String> subjects = stu.getSubjects().stream().map(subject->{
                        return subject.getName();
                    }).collect(Collectors.toList());
                    return subjects;
                });
    }


}
