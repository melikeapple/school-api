package com.schoolexample.exception;

import com.schoolexample.controller.SchoolController;
import com.schoolexample.controller.StudentController;
import com.schoolexample.controller.TeacherController;

import java.io.Serializable;

public class RecordNotFoundException extends RuntimeException {



    public RecordNotFoundException(Throwable e, Class<? extends TeacherController> aClass, String name) {

    }

    public RecordNotFoundException(Serializable e, Class<? extends SchoolController> aClass, String name) {

    }

    public RecordNotFoundException(Serializable e, Class<? extends StudentController> aClass, Object name) {
    }

}
