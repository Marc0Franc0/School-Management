package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.StudentDto;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.repository.StudentRepository;
import com.api.notemanagementapi.repository.SubjectRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Student> getAll() {
       return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    @Override
    public Student createStudent(StudentDto student) {
        return studentRepository.save( Student
        .builder()
        .name(student.getName())
        .lastName(student.getLastName())
        .cell_phone(student.getCell_phone())
        .email(student.getEmail())
        .subjects(subjectRepository.findAllById(student.getId_subjects()))
        .build());
    }
    
    @Override
    public Optional<Object> updateStudentById(Long id, StudentDto student) {
    
        return studentRepository.findById(id)
        .map(stu -> {          
        stu  =  Student
          .builder()
          .id(id)
          .name(student.getName())
          .lastName(student.getLastName())
          .email(student.getEmail())
          .cell_phone(student.getCell_phone())
          .subjects(subjectRepository.findAllById(student.getId_subjects()))
          .build();
          return studentRepository.save(stu);
        });
    }

    @Override
    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Optional<List<NoteDto>> getNotesById(Long id) {
        //Se retorna una lista de NoteDto ,el cual contiene tres datos -> note,studentLastName y SubjectName
       return studentRepository.findById(id).map(stu->{
       List<NoteDto> notes = stu.getNotes()
               .stream()
               .map(note->{
                   return new NoteDto
                           (note.getNote(),note.getStudent().getLastName(),note.getSubject().getName());
       }).collect(Collectors.toList());
       return notes;
       });
    }

    @Override
    public Optional<List<NoteDto>> getNotesByLastname(String lastName) {
        //Se retorna una lista de NoteDto ,el cual contiene tres datos -> note,studentLastName y SubjectName
        return studentRepository.findByLastName(lastName)
                .map(stu->{
                    List<NoteDto> notes = stu.getNotes()
                            .stream()
                            .map(note->{
                                return new NoteDto
                                        (note.getNote(),note.getStudent().getLastName(),note.getSubject().getName());
                            }).collect(Collectors.toList());
                    return notes;
                });
    }

    @Override
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

    @Override
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
