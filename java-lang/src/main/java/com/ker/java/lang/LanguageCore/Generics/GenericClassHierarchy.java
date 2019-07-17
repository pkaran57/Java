package com.ker.java.lang.LanguageCore.Generics;

/*The key difference between generic and non-generic hierarchies is that in a generic hierarchy, any type arguments needed by a
generic superclass must be passed up the hierarchy by all subclasses. This is similar to the way that constructor arguments must
be passed up a hierarchy.*/
class GenericClassHierarchy<T> {

    private T obj;

    GenericClassHierarchy(T obj) {
        this.obj = obj;
    }

    T getObj() {
        return obj;
    }

    void setObj(T obj) {
        this.obj = obj;
    }
}

// This means that whatever type is passed to GenericSuperClass will also be passed to GenericClassHierarchy
class GenericSuperClass<T, V> extends GenericClassHierarchy<T>{

    private V objV;

    GenericSuperClass(T obj, V objV){
        super(obj);
        this.objV = objV;
    }

    V getObjV() {
        return objV;
    }

    void setObjV(V objV) {
        this.objV = objV;
    }
}
