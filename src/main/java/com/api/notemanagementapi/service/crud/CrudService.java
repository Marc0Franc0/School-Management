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
@Deprecated
public interface CrudService {
    List getAll();
    Optional getById(Long id);
    Object create(Object object);
    Optional updateById(Long id, Object object);
    void removeById (Long id);
}
