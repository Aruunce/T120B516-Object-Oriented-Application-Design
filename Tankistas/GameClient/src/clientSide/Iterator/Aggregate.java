// src/clientSide/Iterator/Aggregate.java
package clientSide.Iterator;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}