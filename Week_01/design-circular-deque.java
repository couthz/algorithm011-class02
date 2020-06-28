class MyCircularDeque {
    int [] deque;
    int front = 0;
    int rear = 0;
    int capacity=0;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        deque = new int[k+1];
        capacity = k+1;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (!this.isFull()) {
            front = front == 0 ? capacity - 1 : front - 1;
            deque[front] = value;
            return true;
        }
        return false;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (!this.isFull()) {
            deque[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }
        return false;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(!this.isEmpty()) {
            front = (front + 1) % capacity;
            return true;
        }
        return false;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(!this.isEmpty()) {
            rear = rear == 0 ? capacity - 1:rear - 1;
            return true;
        }
        return false;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (this.isEmpty())
            return -1;
        return deque[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (this.isEmpty())
            return -1;
        return deque[rear == 0 ? capacity - 1:rear - 1];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        if(front == rear)
            return true;
        return false;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        if (front == (rear + 1) % capacity)
            return true;
        return false;
    }
}