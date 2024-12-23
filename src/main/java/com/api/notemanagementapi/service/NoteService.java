package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.repository.NoteRepository;
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public NoteService(NoteRepository noteRepository,
                       @Lazy
                       StudentService studentService,
                       @Lazy
                       SubjectService subjectService){
        this.noteRepository=noteRepository;
        this.studentService=studentService;
        this.subjectService=subjectService;
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }


    public Note create(NoteDto note) {
        return noteRepository.save(Note
                .builder()
                .note(note.getNote())
                .student(studentService.getById(note.getStudentId()).get())
                .subject(subjectService.getById(note.getSubjectId()).get())
                //.student(studentRepository.findById(note.getStudentId()).get())
                //.subject(subjectRepository.findById(note.getSubjectId()).get())
                .build());
    }

    public Optional<Note> updateById(Long id, NoteDto note) {
        return noteRepository.findById(id)
                .map(not -> {
                    not = Note
                            .builder()
                            .id(id)
                            .note(not.getNote())
                            .student(studentService.getById(note.getStudentId()).get())
                            .subject(subjectService.getById(note.getSubjectId()).get())
                            /*.student(studentRepository.findById(note.getStudentId()).get())
                            .subject(subjectRepository.findById(note.getSubjectId()).get())*/
                            .build();
                            noteRepository.save(not);
                    return not;
                });
    }

    public void removeById(Long id) {
        noteRepository.deleteById(id);
    }
    public Optional<List<NoteDto>> getNotesByStudentId(Long id) {
        return studentService.getById(id).map(
                stu -> stu.getNotes()
                        .stream()
                        .map(note -> new NoteDto
                                (note.getNote(), note.getStudent().getId(), note.getSubject().getId())).collect(Collectors.toList()));
    }

     /*public Optional<List<NoteDto>> getNotesById(Long id) {
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
*/
}
