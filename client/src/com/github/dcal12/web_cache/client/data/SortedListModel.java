package com.github.dcal12.web_cache.client.data;

import javax.swing.*;
import java.util.*;

/**
 * Created by Douglas Callaway on 8/8/16.
 * Adapted from 'Dual JList with buttons in between' example by java2s at
 * http://www.java2s.com/Code/Java/Swing-JFC/DualJListwithbuttonsinbetween.htm
 * Retrieved 08/08/2016
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class SortedListModel extends AbstractListModel{

    SortedSet<Object> model;

    public SortedListModel() {
        model = new TreeSet<Object>();
    }

    public int getSize() {
        return model.size();
    }

    public Object getElementAt(int index) {
        return model.toArray()[index];
    }

    public void add(Object element) {
        if (model.add(element)) {
            fireContentsChanged(this, 0, getSize());
        }
    }

    public void addAll(Object elements[]) {
        Collection<Object> c = Arrays.asList(elements);
        model.addAll(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void clear() {
        model.clear();
        fireContentsChanged(this, 0, getSize());
    }

    public boolean contains(Object element) {
        return model.contains(element);
    }

    public Object firstElement() {
        return model.first();
    }

    public Iterator<Object> iterator() {
        return model.iterator();
    }

    public Object lastElement() {
        return model.last();
    }

    public boolean removeElement(Object element) {
        boolean removed = model.remove(element);
        if (removed) {
            fireContentsChanged(this, 0, getSize());
        }
        return removed;
    }
}
