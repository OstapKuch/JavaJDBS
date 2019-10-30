package com.iot.transformer;

import com.iot.model.Annotation.*;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.*;

public class Transformer<T> {
    private final Class<T> clazz;

    public Transformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object fromResultSetToEntity(ResultSet rs)
            throws SQLException {
        //create new object
        Object entity = null;
        try {
            entity = clazz.getConstructor().newInstance();
            if (clazz.isAnnotationPresent(Table.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    // if field is annotated with @Column
                    if (field.isAnnotationPresent(Column.class)) {
                        Column column = (Column) field.getAnnotation(Column.class);
                        String name = column.name();
                        int length = column.length();
                        field.setAccessible(true);
                        Class fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(entity, rs.getString(name));
                        } else if (fieldType == Integer.class) {
                            field.set(entity, rs.getInt(name));
                        } else if (fieldType == Date.class) {
                            field.set(entity, rs.getDate(name));
                        }
                    }
                    // if field is annotated with @PrimaryKeyComposite
                    else if (field.isAnnotationPresent(PrimaryKeyComposite.class)) {
                        field.setAccessible(true);
                        Class fieldType = field.getType();
                        Object FK = fieldType.getConstructor().newInstance();
                        field.set(entity, FK);
                        Field[] fieldsInner = fieldType.getDeclaredFields();
                        for (Field fieldInner : fieldsInner) {
                            if (fieldInner.isAnnotationPresent(Column.class)) {
                                Column column = (Column) fieldInner.getAnnotation(Column.class);
                                String name = column.name();
                                int length = column.length();
                                fieldInner.setAccessible(true);
                                Class fieldInnerType = fieldInner.getType();
                                if (fieldInnerType == String.class) {
                                    fieldInner.set(FK, rs.getString(name));
                                } else if (fieldInnerType == Integer.class) {
                                    fieldInner.set(FK, rs.getInt(name));
                                } else if (fieldInnerType == Date.class) {
                                    fieldInner.set(FK, rs.getDate(name));
                                }
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }
        return entity;
    }
}
