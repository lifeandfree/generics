package ru.innopolis.service.provider;

import org.apache.ibatis.jdbc.SQL;
import ru.innopolis.model.Identified;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CrudTemplateProvider<T extends Identified<PK>, PK extends Serializable>
        extends SqlProviderSupport {

    public transient static final String UPDATEABLE = "updateable";
    public transient static final String ALL = "all";
    public transient static final String INSERTABLE = "insertable";

    /**
     * Запросить объект
     *
     * @param clazz
     * @return
     */
    public String select(Class<? extends Identified> clazz) {
        SQL sql = commonPartSelect(clazz);
        sql.WHERE(getWhereSql(getIdColumnMap(clazz, ALL)));

        return sql.toString();
    }

    /**
     * Запросить объект по UUID
     *
     * @param clazz
     * @return
     */
    public String selectByUUID(Class<? extends Identified> clazz) {
        SQL sql = commonPartSelect(clazz);
        sql.WHERE(" uuid=#{uuid}");

        return sql.toString();
    }

    /**
     * Запросить все объекты
     *
     * @param clazz
     * @return
     */
    public String selectAll(Class<? extends Identified> clazz) {
        SQL sql = commonPartSelect(clazz);
        return sql.toString();
    }

    public SQL commonPartSelect(Class<? extends Identified> clazz) {
        SQL sql = new SQL();
        sql.SELECT(getSQLColumn(clazz));
        sql.FROM(getTableName(clazz));
        return sql;
    }

    /**
     * Сохраняет обект в БД
     *
     * @param entity
     *
     * @return
     */
    public String insert(T entity) {
        LinkedHashMap<String, String> columnMap = getColumnMap(entity.getClass(), INSERTABLE);
        columnMap.putAll(getJoinColumnMap(entity.getClass(), INSERTABLE));

        SQL sql = new SQL();
        sql.INSERT_INTO(getTableName(entity.getClass()));
        addInsertSql(columnMap, sql);

        return sql.toString();
    }

    /**
     * Обновляет обект в БД
     *
     * @param entityClass
     *
     * @return
     */
    public String update(Class<? extends Identified> entityClass) {
        LinkedHashMap<String, String> idMap = getIdColumnMap(entityClass, UPDATEABLE);
        LinkedHashMap<String, String> columnMap = getColumnMap(entityClass, UPDATEABLE);
        columnMap.putAll(getJoinColumnMap(entityClass, UPDATEABLE));

        SQL sql = new SQL();
        sql.UPDATE(getTableName(entityClass));
        sql.SET(getSetSql(idMap, columnMap));
        sql.WHERE(getWhereSql(idMap));
        return sql.toString();
    }

    /**
     * Удаляет объект из БД
     *
     * @param entityClass
     *
     * @return
     */
    public String delete(Class<? extends Identified> entityClass) {
        SQL sql = new SQL();
        sql.DELETE_FROM(getTableName(entityClass));
        sql.WHERE(getWhereSql(getIdColumnMap(entityClass, INSERTABLE)));

        return sql.toString();
    }

    public String getSQLColumn(Class<? extends Identified> clazz) {
        LinkedHashMap<String, String> columnMap = getColumnMap(clazz, ALL);
        columnMap.putAll(getJoinColumnMap(clazz, ALL));

        StringBuilder dbColumn = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : columnMap.entrySet()) {
            if (i++ != 0) {
                dbColumn.append(',');
            }
            dbColumn.append(entry.getValue());
        }
        return dbColumn.toString();
    }

    public void addInsertSql(LinkedHashMap<String, String> columnMap, SQL sql) {
        StringBuilder dbColumn = new StringBuilder();
        StringBuilder propertyColumn = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, String> entry : columnMap.entrySet()) {
            if (i++ != 0) {
                dbColumn.append(',');
                propertyColumn.append(',');
            }
            propertyColumn.
                    append("#{").append(entry.getKey()).append("}");
            dbColumn.append(entry.getValue());
        }
        sql.VALUES(dbColumn.toString(), String.valueOf(propertyColumn));
    }

    public String getSetSql(LinkedHashMap<String, String> idMap, LinkedHashMap<String, String> columnMap) {
        StringBuilder setSql = new StringBuilder();
        if (columnMap != null && !columnMap.isEmpty()) {
            int i = 0;
            for (Map.Entry<String, String> entry : columnMap.entrySet()) {
                String key = entry.getKey();
                if (idMap.get(key) != null) {
                    continue;
                }
                if (i++ != 0) {
                    setSql.append(',');
                }
                setSql.append(entry.getValue()).append("=#{").append(key).append('}');
            }
        }
        return setSql.toString();
    }

    public String getWhereSql(LinkedHashMap<String, String> idMap) {
        StringBuilder whereSql = new StringBuilder();
        if (idMap != null && !idMap.isEmpty()) {
            int j = 0;
            for (Map.Entry<String, String> entry : idMap.entrySet()) {
                String key = entry.getKey();
                if (j++ != 0) {
                    whereSql.append(" AND ");
                }
                whereSql.append(entry.getValue()).append("=#{").append(key).append('}');
            }
        }
        return whereSql.toString();
    }

    public LinkedHashMap<String, String> getIdColumnMap(Class<? extends Identified> entityClass, String insertOrUpdate) {
        LinkedHashMap<String, String> idMap = new LinkedHashMap<>();
        Set<Field> fields = getAnnotationFieldLst(entityClass, Column.class);
        if (fields != null && !fields.isEmpty()) {
            for (Field field : fields) {
                Method method = null;
                try {
                    method = entityClass.getDeclaredMethod("get" +
                            Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1),
                            new Class[0]);
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                }
                Column column = method.getAnnotation(Column.class);
                switch (insertOrUpdate) {
                    case INSERTABLE: {
                        if (isPKAndInsert(method, column)) {
                            idMap.put(field.getName(), column.name());
                        }
                        break;
                    }
                    case UPDATEABLE: {
                        if (isPKAndUpdate(method, column)) {
                            idMap.put(field.getName(), column.name());
                        }
                        break;
                    }
                    default: {
                        if (isPK(method))
                            idMap.put(field.getName(), column.name());
                    }
                }
            }
        }
        return idMap;
    }

    public LinkedHashMap<String, String> getColumnMap(Class<? extends Identified> entityClass, String insertOrUpdate) {
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        Set<Field> fields = getAnnotationFieldLst(entityClass, Column.class);
        if (fields != null && !fields.isEmpty()) {
            for (Field field : fields) {
                Method method = null;
                try {
                    method = entityClass.getDeclaredMethod("get" +
                            Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1),
                            new Class[0]);
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                }
                Column column = method.getAnnotation(Column.class);
                switch (insertOrUpdate) {
                    case INSERTABLE: {
                        if (isInsert(method, column) && !isPK(method)) {
                            columnMap.put(field.getName(), column.name());
                        }
                        break;
                    }
                    case UPDATEABLE: {
                        if (isUpdate(method, column)) {
                            columnMap.put(field.getName(), column.name());
                        }
                        break;
                    }
                    default: {
                        columnMap.put(field.getName(), column.name());
                    }
                }
            }
        }
        return columnMap;
    }

    public LinkedHashMap<String, String> getJoinColumnMap(Class<? extends Identified> entityClass, String insertOrUpdate) {
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        Set<Field> fields = getAnnotationFieldLst(entityClass, JoinColumn.class);
        if (fields != null && !fields.isEmpty()) {
            for (Field field : fields) {
                Method method = null;
                try {
                    method = entityClass.getDeclaredMethod("get" +
                                    Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1),
                            new Class[0]);
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                }
                JoinColumn column = method.getAnnotation(JoinColumn.class);
                switch (insertOrUpdate) {
                    case INSERTABLE: {
                        if (isInsert(method, column) && !isPK(method)) {
                            columnMap.put(field.getName() + ".id", column.name());
                        }
                        break;
                    }
                    case UPDATEABLE: {
                        if (isUpdate(method, column)) {
                            columnMap.put(field.getName() + ".id", column.name());
                        }
                        break;
                    }
                    default: {
                        columnMap.put(field.getName() + ".id", column.name());
                    }
                }
            }
        }
        return columnMap;
    }

    private boolean isPKAndUpdate(Method method, Column column) {
        return isUpdate(method, column) && isPK(method);
    }

    private boolean isPK(Method method) {
        return method.isAnnotationPresent(Id.class);
    }

    private boolean isPKAndInsert(Method method, Column column) {
        return isInsert(method, column) && isPK(method);
    }

    private boolean isUpdate(Method method, Column column) {
        return method.isAnnotationPresent(Column.class) && column.updatable();
    }

    private boolean isUpdate(Method method, JoinColumn column) {
        return method.isAnnotationPresent(JoinColumn.class) && column.updatable();
    }

    private boolean isInsert(Method method, Column column) {
        return method.isAnnotationPresent(Column.class) && column.insertable();
    }

    private boolean isInsert(Method method, JoinColumn column) {
        return method.isAnnotationPresent(JoinColumn.class) && column.insertable();
    }

    public Set<Field> getAnnotationFieldLst(Class<? extends Identified> entityClass, Class<?> annotationClass) {
        Set<Field> list = new HashSet<>();
        Class<?> superClass = entityClass.getSuperclass();
        while (true) {
            if (superClass != null) {
                getFiledFromClass(list, superClass, annotationClass);
                superClass = superClass.getSuperclass();
            } else {
                break;
            }
        }
        getFiledFromClass(list, entityClass, annotationClass);
        return list;
    }

    public Set<Field> getFiledFromClass(Set<Field> list, Class<?> superClass, Class<?> annotationClass) {
        Method[] superMethods = superClass.getDeclaredMethods();
        if (superMethods != null && superMethods.length > 0) {
            for (Method method : superMethods) {
                if (method.isAnnotationPresent((Class<? extends Annotation>) annotationClass)) {
                    Field field = null;
                    try {
                        field = superClass.getDeclaredField(Character.toLowerCase(method.getName().charAt(3)) +
                                method.getName().substring(4));
                    } catch (NoSuchFieldException e) {
                        throw new IllegalArgumentException(e);
                    }
                    if (field != null)
                        list.add(field);
                }
            }
        }
        return list;
    }

}
