package com.api.notemanagementapi.service.crud;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

//Interface la cual es implementada en los service de cada entidad con los método crud básicos
@Component
public interface CrudService {
    public List getAll();
    public Optional getById(Long id);
    public Object create(Object object);
    public Optional updateById(Long id, Object object);
    public void removeById (Long id);
}
