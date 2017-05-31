package com.simarc86.limpiezasreig;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by marctamaritromero on 25/11/15.
 */
public class ProductList implements List<Product> {

    @Override
    public void add(int location, Product object) {

    }

    @Override
    public boolean add(Product object) {
        return false;
    }

    @Override
    public boolean addAll(int location, Collection<? extends Product> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Product> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public Product get(int location) {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public Iterator<Product> iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<Product> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<Product> listIterator(int location) {
        return null;
    }

    @Override
    public Product remove(int location) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public Product set(int location, Product object) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public List<Product> subList(int start, int end) {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }
}