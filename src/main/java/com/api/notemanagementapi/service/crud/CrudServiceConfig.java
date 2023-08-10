package com.api.notemanagementapi.service.crud;

import com.api.notemanagementapi.service.NoteService;
import com.api.notemanagementapi.service.StudentService;
import com.api.notemanagementapi.service.SubjectService;
import com.api.notemanagementapi.service.TeacherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudServiceConfig {
    @Bean(name="NoteService")
    public NoteService getNoteService(){
        return new NoteService();
    }
    @Bean(name="SubjectService")
    public SubjectService getSubjectService(){
        return new SubjectService();
    }
    @Bean(name="TeacherService")
    public TeacherService getTeacherService(){
        return new TeacherService();
    }

}
