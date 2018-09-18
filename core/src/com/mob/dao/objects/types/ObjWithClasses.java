package com.mob.dao.objects.types;

import com.mob.shared.interfaces.CharClass;

import java.util.Set;

public abstract class ObjWithClasses extends Obj {

    private Set<CharClass> allowedClasses;

    public ObjWithClasses(String name, int grhIndex) {
        super(name, grhIndex);
    }

    public void addClass(CharClass charClass){
        allowedClasses.add(charClass);
    }

    public Set<CharClass> getAllowedClasses() {
        return this.allowedClasses;
    }
}
