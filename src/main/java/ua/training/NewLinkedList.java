package ua.training;


import java.util.Iterator;

public class NewLinkedList<T> implements SimpleLinkedList<T> {

    private Node<T> firstNode = null;
    private Node<T> lastNode = null;
    private int size = 0;


    private static class Node<T> {

        T value;
        Node<T> nextNode = null;

        Node(T value) {
            this.value = value;
        }

    }

    @Override
    public boolean add(T element) {
        Node<T> newNode = new Node<>(element);
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            lastNode.nextNode = newNode;
        }
        lastNode = newNode;
        size++;
        return true;
    }

    @Override
    public T set(Integer position, T element) {
        if (position >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (position.equals(0)) {
            Node<T> newNode = new Node<>(element);
            newNode.nextNode = firstNode;
            firstNode = newNode;
        }
        Node<T> newNode = new Node<>(element);
        Node<T> node = getNodeByIndex(--position);
        newNode.nextNode = node.nextNode.nextNode;
        Node<T> oldNode = node.nextNode.nextNode;
        node.nextNode.nextNode = null;
        node.nextNode = newNode;
        return oldNode.value;
    }

    @Override
    public T get(Integer position) {
        if (position < size) {
            return getNodeByIndex(position).value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(T element) {
        Node<T> x = firstNode;
        for (int i = 0; i < size; i++) {
            if (x.value.equals(element)) {
                if (i == 0) {
                    firstNode = firstNode.nextNode;
                } else {
                    getNodeByIndex(i-1).nextNode = x.nextNode;
                    --size;
                }
                return true;
            }
            x = x.nextNode;
        }

        return false;
    }

    @Override
    public boolean contains(T element) {
        Node<T> x = firstNode;
        for (int i = 0; i < size; i++) {
            if (x.value.equals(element)) {
                return true;
            }
            x = x.nextNode;
        }

        return false;
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class NewListIterator<T> implements Iterator<T> {
        private Node<T> currentNode;
        int position = 0;

        public NewListIterator(Node<T> firstNode) {
            this.currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T result = currentNode.value;
            currentNode = currentNode.nextNode;
            position++;
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new NewListIterator<T>(firstNode);
    }

    Node<T> getNodeByIndex(int index) {
        Node<T> x = firstNode;
        for (int i = 0; i < index; i++) {
            x = x.nextNode;
        }
        return x;
    }


    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> x = firstNode;
        stringBuilder.append("[");
        for (int index = 0; index < size; index++) {
            stringBuilder.append(x.value);
            stringBuilder.append(", ");
            x = x.nextNode;
        }
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
        return stringBuilder.toString();
    }
}
