class Box<T> {
    private T item;

    public void put(T item) throws BoxNotEmptyException {
        if (this.item != null) {
            throw new BoxNotEmptyException("Коробка уже содержит объект");
        }
        this.item = item;
    }

    public T get() {
        T temp = this.item;
        this.item = null;
        return temp;
    }

    public boolean isEmpty() {
        return this.item == null;
    }
}

class BoxNotEmptyException extends Exception {
    public BoxNotEmptyException(String message) {
        super(message);
    }
}